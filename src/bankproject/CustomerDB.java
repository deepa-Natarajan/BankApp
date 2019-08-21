package bankproject;

import java.util.List;

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDB {

    static List<Customer> Customers = new ArrayList<>(50);
    static List<Customer> search = new ArrayList<>(25);
    private Connection connection;

    public CustomerDB() {

        try {
            String dbUrl = "jdbc:sqlite:customer.sqlite";
            connection = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public List searchCustomer(String f, String L) {
        //Scanner sc = new Scanner(System.in);
        // System.out.println("Enter first name:");
        String firstName = f;
        // System.out.println("Enter Last Name:");
        String lastName = L;

        Customer c;
        String sql = "SELECT *from customer Where FirstName=? AND  LastName=? ";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    String fName = rs.getString("FirstName");
                    String lName = rs.getString("LastName");
                    String pNumber = rs.getString("PhoneNumber");
                    String add = rs.getString("Address");
                    int acNum = rs.getInt("AccountNumber");
                    double balAmt = rs.getDouble("BalanceAmount");
                    c = new Customer(fName, lName, pNumber, add, acNum, balAmt);

                    search.add(c);

                }
                //rs.close();
            }
            ps.close();
            return search;

        } catch (SQLException e) {

            System.err.println(e);
            return null;
        }

    }

    public Customer searchCustomer(String f, String L, String ac) {

        String firstName = f;
        String lastName = L;
        int accountNum = Integer.parseInt(ac);

        Customer c;
        String sql = "SELECT *from customer Where FirstName=? AND  LastName=? and accountNumber=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, accountNum);

            try (ResultSet rs = ps.executeQuery()) {

                String fName = rs.getString("FirstName");
                String lName = rs.getString("LastName");
                String pNumber = rs.getString("PhoneNumber");
                String add = rs.getString("Address");

                int acNum = rs.getInt("AccountNumber");
                double balAmt = rs.getDouble("BalanceAmount");
                c = new Customer(fName, lName, pNumber, add, acNum, balAmt);

               // search.add(c);

                rs.close();
            }
            ps.close();
            return c;

        } catch (SQLException e) {

            System.err.println(e);
            return null;
        }

    }

    public boolean check(Customer c) {

        String sql = "SELECT * FROM Customer WHERE FirstName=? and LastName=? and PhoneNumber=? and Address=?";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getPhoneNumber());
            ps.setString(4, c.getAddress());

            try (ResultSet qcustomer = ps.executeQuery()) {
                if (qcustomer.next()) {
                    return true;
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

        return false;
    }

    public boolean add(Customer c) {

        String sql = "INSERT into Customer (FirstName, LastName, PhoneNumber, Address) Values(?,?,?,?)";
        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getPhoneNumber());
            ps.setString(4, c.getAddress());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

        return true;

    }

    public boolean delete(Customer c) {
        String sql = "DELETE FROM Customer WHERE FirstName =? and LastName=? and PhoneNumber=? and Address=?  ";
        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getPhoneNumber());
            ps.setString(4, c.getAddress());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

    }

    public void updateDbBal(Customer c) {

        String sql = "Update customer SET BalanceAmount=?  where AccountNumber=?";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, c.getBalance());
            ps.setInt(2, c.getAccountNumber());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex);

        }

    }

    void updateRecord(Customer c) {
        String sql = "Update customer SET lastName=? , phoneNumber=? , Address=? where AccountNumber=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, c.getLastName());
            ps.setString(2, c.getPhoneNumber());
            ps.setString(3, c.getAddress());
            ps.setInt(4, c.getAccountNumber());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
