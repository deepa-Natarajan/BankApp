package bankproject;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BankProjectApp {

    public static CustomerDB customerDB = new CustomerDB();
    //public static List<Customer> search;
    private JTextField firstNameField, lastNameField,
            addressField, phoneNumberField, accountNumField,
            balanceField, withdrawField, depositeField, infoField,
            monthField, interestField, rateField;

    public BankProjectApp() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
    }

    private void initComponents() {
        JFrame jfrm = new JFrame("Bank Application-Deepa Natarajan ");
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setLayout(new BorderLayout());
        jfrm.setSize(400, 430);
        jfrm.setLocationByPlatform(true);
        JPanel mPanel = new JPanel(new FlowLayout());

        infoField = new JTextField(27);
        infoField.setText("Enter  First and Last Name of the Customer");
        infoField.setEditable(false);
        mPanel.add(infoField);

        mPanel.setBorder(BorderFactory.createTitledBorder("GENERAL INFORMATION"));
        mPanel.setBackground(Color.WHITE);

        //**************************************************//
        JPanel sPanel = new JPanel(new GridBagLayout());
        sPanel.setBorder(BorderFactory.createTitledBorder("CUSTOMER RECORDS"));

        Dimension dim = new Dimension(130, 20);
        JLabel fName = new JLabel("Customer FirstName:");
        JLabel lName = new JLabel("Customer LastName:");
        JLabel address = new JLabel("Address:");
        JLabel phoneNumber = new JLabel("Phone Number:");
        JLabel accountNumber = new JLabel("Account Number:");
        JLabel Balance = new JLabel("Balance Amount:");
        JLabel withdraw = new JLabel("Withdraw Amount:");
        JLabel Deposite = new JLabel("Deposite Amount:");

        firstNameField = new JTextField();
        lastNameField = new JTextField();
        addressField = new JTextField();
        phoneNumberField = new JTextField();
        accountNumField = new JTextField();
        balanceField = new JTextField();
        withdrawField = new JTextField();
        depositeField = new JTextField();

        firstNameField.setPreferredSize(dim);
        lastNameField.setPreferredSize(dim);
        addressField.setPreferredSize(dim);
        phoneNumberField.setPreferredSize(dim);
        accountNumField.setPreferredSize(dim);
        balanceField.setPreferredSize(dim);
        withdrawField.setPreferredSize(dim);
        depositeField.setPreferredSize(dim);

        firstNameField.setMinimumSize(dim);
        lastNameField.setMinimumSize(dim);
        addressField.setMinimumSize(dim);
        phoneNumberField.setMinimumSize(dim);
        accountNumField.setMinimumSize(dim);
        balanceField.setMinimumSize(dim);
        withdrawField.setMinimumSize(dim);
        depositeField.setMinimumSize(dim);

        balanceField.setEditable(false);

        accountNumField.setEditable(false);
        //depositeField.setText("Enter Value");
        //withdrawField.setText("Enter Value");

        sPanel.add(fName, getConstraints(0, 0));
        sPanel.add(firstNameField, getConstraints(1, 0));
        sPanel.add(lName, getConstraints(0, 1));
        sPanel.add(lastNameField, getConstraints(1, 1));
        sPanel.add(address, getConstraints(0, 2));
        sPanel.add(addressField, getConstraints(1, 2));
        sPanel.add(phoneNumber, getConstraints(0, 3));
        sPanel.add(phoneNumberField, getConstraints(1, 3));
        sPanel.add(accountNumber, getConstraints(0, 4));
        sPanel.add(accountNumField, getConstraints(1, 4));
        sPanel.add(Balance, getConstraints(0, 5));
        sPanel.add(balanceField, getConstraints(1, 5));
        sPanel.add(withdraw, getConstraints(0, 7));
        sPanel.add(withdrawField, getConstraints(1, 7));
        sPanel.add(Deposite, getConstraints(0, 6));
        sPanel.add(depositeField, getConstraints(1, 6));
        JButton nextCus = new JButton("NextCustomer");
        JButton searchCustomer = new JButton("SearchCustomer");

        searchCustomer.addActionListener(new ActionListener() {

            Customer c;

            @Override
            public void actionPerformed(ActionEvent e) {

                String first = firstNameField.getText();
                String last = lastNameField.getText();

                List<Customer> search = customerDB.searchCustomer(first, last);
                c = search.get(0);
                addressField.setText(c.getAddress());
                phoneNumberField.setText(c.getPhoneNumber());
                accountNumField.setText(c.getAcNumber());
                balanceField.setText(c.getBalanceAmount());
                infoField.setText("Customer Information ");

            }
        });

        nextCus.addActionListener(new ActionListener() {

            int i = 1;
            int total = 0;

            @Override

            public void actionPerformed(ActionEvent e) {
                total += i;
                nextcustomerSelected(total);

            }

            private void nextcustomerSelected(int total) {
                String first = firstNameField.getText();
                String last = lastNameField.getText();

                JList customerList;
                DefaultListModel<Customer> customerModel;
                List<Customer> search = customerDB.searchCustomer(first, last);
                customerModel = new DefaultListModel<>();
                for (Customer c : search) {
                    customerModel.addElement(c);
                }
                customerList = new JList();
                customerList.setModel(customerModel);

                Customer c = customerModel.getElementAt(total);
                {
                    phoneNumberField.setText(c.getPhoneNumber());
                    addressField.setText(c.getAddress());
                    accountNumField.setText(c.getAcNumber());
                    balanceField.setText(c.getBalanceAmount());
                };
            }
        });

        JButton openAccount = new JButton("OpenAccount");
        openAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer cus = new Customer();

                cus.setFirstName(firstNameField.getText());
                cus.setLastName(lastNameField.getText());
                cus.setAddress(addressField.getText());
                cus.setPhoneNumber(phoneNumberField.getText());
                boolean Exist = customerDB.check(cus);
                if (Exist) {
                    String message = "Information already exist";
                    String title = "Existing Customer!!";
                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
                } else {
                    customerDB.add(cus);
                    String message = "Information Added!";
                    String title = "New Customer!!";
                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        JButton preCus = new JButton("PreviousCustomer");

        preCus.addActionListener(new ActionListener() {
            int i = 1;
            int total = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                total += i;
                previouscustomerSelected(total);

            }

            private void previouscustomerSelected(int total) {

                String first = firstNameField.getText();
                String last = lastNameField.getText();
                List<Customer> search = customerDB.searchCustomer(first, last);

                JList customerList;
                DefaultListModel<Customer> customerModel;
                customerModel = new DefaultListModel<>();
                for (Customer c : search) {
                    customerModel.addElement(c);
                }
                customerList = new JList();
                customerList.setModel(customerModel);
                Customer c;
                int i = customerModel.size();

                c = customerModel.getElementAt((i - total));
                {
                    phoneNumberField.setText(c.getPhoneNumber());
                    addressField.setText(c.getAddress());
                    accountNumField.setText(c.getAcNumber());
                    balanceField.setText(c.getBalanceAmount());
                }
            }

        }
        );

        JButton dep = new JButton("Deposite");

        dep.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                infoField.setText("Balance After Deposite !");
                String first = firstNameField.getText();
                String last = lastNameField.getText();
                String ac = accountNumField.getText();

                Customer c = customerDB.searchCustomer(first, last, ac);

                double totalBal = c.getBalance() + Double.parseDouble(depositeField.getText());
                c.setBalanceAmount(totalBal);
                balanceField.setText(c.getBalanceAmount());

                customerDB.updateDbBal(c);

            }
        }
        );

        JButton with = new JButton("WithDraw");

        with.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                infoField.setText("Balance After Withdraw ! ");
                String first = firstNameField.getText();
                String last = lastNameField.getText();
                String ac = accountNumField.getText();
                Customer c = customerDB.searchCustomer(first, last, ac);
                //Customer c = search.get(0);
                double withDraw = Double.parseDouble(withdrawField.getText());
                double currentBal = c.getBalance();
                double totalBal = currentBal - withDraw;
                c.setBalanceAmount(totalBal);
                balanceField.setText(c.getBalanceAmount());
                balanceField.setEditable(false);
                customerDB.updateDbBal(c);

            }

        }
        );
        JButton update = new JButton("UpdateRecord");

        update.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                infoField.setText("Updated Information ! ");
                String first = firstNameField.getText();
                String ac = accountNumField.getText();
                String last = lastNameField.getText();
                Customer c = customerDB.searchCustomer(first, last, ac);
                //= search.get(0);

                String phone = phoneNumberField.getText();
                String add = addressField.getText();
                c.setLastName(last);
                c.setPhoneNumber(phone);
                c.setAddress(add);
                lastNameField.setText(c.getLastName());
                phoneNumberField.setText(c.getPhoneNumber());
                addressField.setText(c.getAddress());
                customerDB.updateRecord(c);
                firstNameField.setEditable(false);

            }

        }
        );

        JButton delete = new JButton("DeleteRecord");

        delete.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                infoField.setText("Updated Information ! ");
                String first = firstNameField.getText();
                String last = lastNameField.getText();
                String ac = accountNumField.getText();
                Customer c = customerDB.searchCustomer(first, last, ac);
                //Customer c = search.get(0);

                customerDB.delete(c);
                infoField.setText("Records Deleted!");

            }

        }
        );
        sPanel.add(delete, getConstraints(2, 7));
        sPanel.add(update, getConstraints(2, 6));
        sPanel.add(with, getConstraints(2, 5));
        sPanel.add(dep, getConstraints(2, 4));
        sPanel.add(openAccount, getConstraints(2, 3));
        sPanel.add(preCus, getConstraints(2, 2));
        sPanel.add(nextCus, getConstraints(2, 1));
        sPanel.add(searchCustomer, getConstraints(2, 0));
        //********************************************************************************//
        JPanel cPanel = new JPanel(new GridBagLayout());

        cPanel.setBorder(BorderFactory.createTitledBorder("INTEREST CALCULATOR"));
        Dimension boxSize = new Dimension(60, 20);

        JLabel monthLabel = new JLabel("EnterCurrentMonth:");
        monthField = new JTextField();
        monthField.setText("jan");
        monthField.setPreferredSize(boxSize);
        monthField.setMinimumSize(boxSize);

        JButton interestLabel = new JButton("InterestEarned:");
        interestField = new JTextField();
        interestField.setMinimumSize(boxSize);
        interestField.setPreferredSize(boxSize);
        interestField.setEditable(false);

        JLabel rateLabel = new JLabel("Rate/Year:");
        rateField = new JTextField();
        rateField.setMinimumSize(boxSize);
        rateField.setPreferredSize(boxSize);
        rateField.setEditable(false);
        rateField.setText("6 %");

        interestLabel.addActionListener((ActionEvent e) -> {
            calculateInterest();
        });

        JButton exit = new JButton("Exit");

        exit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        cPanel.add(monthLabel, getConstraints(0, 0));
        cPanel.add(monthField, getConstraints(1, 0));
        cPanel.add(interestLabel, getConstraints(2, 0));
        cPanel.add(interestField, getConstraints(3, 0));
        cPanel.add(rateLabel, getConstraints(0, 1));
        cPanel.add(rateField, getConstraints(1, 1));
        cPanel.add(exit, getConstraints(3, 1));

        jfrm.add(mPanel, BorderLayout.NORTH);

        jfrm.add(sPanel, BorderLayout.CENTER);

        jfrm.add(cPanel, BorderLayout.SOUTH);

        jfrm.setVisible(
                true);

    }

    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 5);
        c.gridx = x;
        c.gridy = y;
        return c;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BankProjectApp();

            }
        }
        );

    }

    public void calculateInterest() {       
        

        double rate = 0.12;
        double it =0;
        String time = monthField.getText();
        switch (time) {
            case "jan":
                it = (rate) * (1 / 12);
                break;
            case "feb":
                it = (rate) * (2 / 12);
                break;
            case "mar":
                it = (rate) * (3 / 12);
                break;
            case "apr":
                it = (rate) * (4 / 12);
                break;
            case "may":
                it = (rate) * (5 / 12);
                break;
            case "jun":
                it = (.12) * (6 / 12);
                break;
            case "jul":
                it = (rate) * (7 / 12);
                break;
            case "aug":
                it = (rate) * (8 / 12);
                break;
            case "sep":
                it = (rate) * (9 / 12);
                break;
            case "oct":
                it = (rate) * (10 / 12);
                break;
            case "nov":
                it = (rate) * (11 / 12);
                break;
            case "dec":
                it = (rate) * (12 / 12);
                break;

        }
        
        String first = firstNameField.getText();
        String ac = accountNumField.getText();
        String last = lastNameField.getText();

        Customer c = customerDB.searchCustomer(first,last, ac);

        double InterestEarned;
        InterestEarned = (c.getBalance())*it;
        NumberFormat currency=NumberFormat.getCurrencyInstance();
        currency.setMaximumIntegerDigits(3);
        
        String IE =currency.format(InterestEarned);

        interestField.setText( IE);

    }
;
}
