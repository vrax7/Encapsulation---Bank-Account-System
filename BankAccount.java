public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String pinCode;

    public BankAccount(String accountNumber, String accountHolder, double balance, String pinCode){
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.pinCode = pinCode;
    }
    public void withdraw(double amount, String pin){
        if(amount <= balance && pin.equals(pinCode)){
            balance = balance - amount;
            System.out.println("Withdrawal successful! New balance: " + balance);
        }
        else if(amount > balance){
            System.err.println("Error: Insufficient funds!");
        }
        else if(amount < 0){
            System.err.println("Error: Deposit amount must be positive!");
        }
        else if(!pin.equals(pinCode)){
            System.err.println("Error: Incorrect PIN!");
        }
        else{
            System.err.println("Invalid Input");
        } 
    }
    public void deposit(double amount, String pin){
        if(amount <= balance && pin.equals(pinCode)){
            balance = balance + amount;
            System.out.println("Deposit successful! New balance: " + balance);
        }
        else if(amount < 0){
            System.err.println("Error: Deposit amount must be positive!");
        }
        else if(!pin.equals(pinCode)){
            System.err.println("Error: Incorrect PIN!");
        }
        else{
            System.err.println("Invalid Input");
        } 
    }
    public String getAccHold(){
        return accountHolder;
    }
    public String getAccNum(){
        return accountNumber;
    }
    public String getPinCode(){
        return pinCode;
    }
    public double getBalance(){
        return balance;
    }
    public void setPin(String code, String pin){
        if(code.equals(pinCode)){
            pinCode = code;
            System.out.println("PIN changed successfully!");
        }
        else{
            System.err.println("Error: Incorrect PIN!");
        }
    }
    public void setName(String code, String name){
        if(code.equals(pinCode)){
            accountHolder = name; 
            System.out.println("Name changed successfully.");
        }
        else{
            System.err.println("Error: Incorrect PIN!");
        }
    }
}
