package bankproject;

import java.text.NumberFormat;

public class Customer {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    
    private int accountNumber;
    private double balanceAmount;

    Customer() {
        this(" ", "", " ", " ", 0, 100);

    }

    Customer(Customer obj) {
        firstName = obj.firstName;
        lastName = obj.lastName;
        phoneNumber = obj.phoneNumber;
        address = obj.address;
        
        accountNumber = obj.accountNumber;
        balanceAmount = obj.balanceAmount;

    }

    Customer(String firstName, String lastName, String phoneNumber, String address,  int accountNumber, double balanceAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
       
        this.accountNumber = accountNumber;
        this.balanceAmount = balanceAmount;    //interestRate=calculateInterestrate();

    }

    public String getFirstName() {
        return firstName;
    }

 

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    public String getAcNumber(){
        String acNum=Integer.toString(accountNumber);
    return acNum;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
    public double getBalance(){
    return balanceAmount;
    }

    public String getBalanceAmount() {
        return formatBalanceAmt(balanceAmount);
    }

    private String formatBalanceAmt(double balanceAmount) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        currency.setMinimumFractionDigits(2);
        return currency.format(balanceAmount);
        // return Double.parseDouble(currency.format());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
