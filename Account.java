import java.util.Scanner;
import java.util.ArrayList;
public class Account{
    
    static Scanner in = new Scanner(System.in);
    static ArrayList <BankAccount> listAccounts = new ArrayList<>();

    static String idNumber = "AA000";
    static int index = -1;
    
    public static void main(String[] args) {

        int choice;

            do{
            System.out.println();
            System.out.println("==============================");
            System.out.println();

            System.out.println("Menu List:");
            System.out.println("1. Log In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit System");
            System.out.println("4. Display All");
            System.out.println("------------------------------");
            System.out.print("Enter your choice [1 to 3]: ");
            choice = in.nextInt();

            switch(choice){
                case 1: logIn(); break;
                case 2: signUp(); break;
                case 3: exit(); break;
                case 4: display(); break;
                default: System.err.println("Invalid input. Please try again.");
            }
        }while(choice != 3);
    }
    public static void display(){
        for(int i = 0; i < listAccounts.size(); i++){
            System.out.println((i + 1) + ". " + listAccounts.get(i).getAccNum());
        }
    }
    
    public static void exit(){
        System.out.println("Exiting System..");
        System.exit(0);
    }
    
    public static void logIn(){
        boolean check = true;
        String idNumber;

        if(listAccounts.isEmpty()){
            System.out.println("Bank accounts is currently empty.");
            main(null);
        }

        in.nextLine();

        do{
        System.out.print("Enter ID Number: ");
        idNumber = in.nextLine();

        for(int i = 0; i < listAccounts.size(); i++){
            if(listAccounts.get(i).getAccNum().equals(idNumber)){
                check = false;
                index = i;
                break;
            }
            else if(!listAccounts.get(i).getAccNum().equals(idNumber)){
                System.out.println("Account number does not exist, please try again.");
                check = true;
            }
        }
    }while(check);

        System.out.print("Enter PIN: ");
        String pin = in.nextLine();

        if(pin.equals(listAccounts.get(index).getPinCode())){
            System.out.println("Login Successful!");
            menu();
        }
    }
    
    public static void signUp(){

        in.nextLine();
        String name = nameCheck();
        String pin = pinCheck();
        double deposit = depositCheck();

        BankAccount bankAccount = new BankAccount(idNumber, name, deposit, pin);
        listAccounts.add(bankAccount);
        System.out.println("================================================================");
        System.out.println("");
        System.out.println("    Bank account successfully created.");
        System.out.println("    Bank account's information:");
        System.out.println("    Name:       " + name);
        System.out.println("    Id Number:  " + idNumber);
        System.out.println("    PIN:        " + pin);
        System.out.println("    Balance:    P" + deposit);

        idNumber = incrementId(idNumber);
    }
    
    public static String incrementId(String id){
        String letters = id.replaceAll("[0-9]", "");
        String numbers = id.replaceAll("[^0-9]", "");

        numbers = incrementNumbers(numbers);

        if(numbers.equals("99")){
            incrementLetters(letters);
        }
        
        String newId =  letters + numbers;

        return newId;
    }
    
    public static String incrementLetters(String letters){
        char [] c = letters.toCharArray();
        int size = letters.length() - 1;

        for(int i = size; i >= 0 ; i--){
            if(c[i] != 'Z'){
                c[i]++;

                return new String(c);
            }
            else{
                c[i] = 'A';
            }
        }
        return 'A' + new String(c);
    }
    
    public static String incrementNumbers(String numbers){
        char [] c = numbers.toCharArray();
        int size = numbers.length() - 1;

        for(int i = size; i >= 0 ; i--){
            if(c[i] != '9'){
                c[i]++;

                return new String(c);
            }
            else{
                c[i] = '0';
            }
        }
        return new String(c);
    }

    public static String nameCheck(){
        String name;
        boolean result = true;
        do{
            System.out.print("Enter first name: ");
            name = in.nextLine();
                if(!listAccounts.isEmpty()){
                    for(int i = 0; i < listAccounts.size(); i++){
                        if(listAccounts.get(i).getAccHold().equalsIgnoreCase(name)){
                            System.err.println("Error: Account already exists with this name.");
                            result = true;
                        }
                        else{
                            System.out.println(name +" successfully added.");
                            result = false;
                        }
                    }
                }
                else if(listAccounts.isEmpty()){
                    System.out.println(name +" successfully added.");
                    result = false;
                    break;
                }
        }while(result);
        return name;
    }

    public static String pinCheck(){
        boolean result = true;
        String pin;
        
        do{
        System.out.print("Enter PIN [6 digits]: ");
        pin = in.nextLine();

        if(pin.length() > 6){
            System.err.println("Error: Entered pin exceed maximum length.");
            result = false;
        }
        else if(pin.length() < 6){
            System.err.println("Error: Entered pin insufficient length.");
            result = false;
        }
        else{
            int check = pinCompare(pin);

            if(check == 0){
                System.err.println("Error: pin too easy XD.");
                result = false;
            }
            else if(check == 1){
            result = true;
            System.out.println("PIN successfully entered!");
            }
        }
        }while(!result);
        return pin;
    }
    
    public static int pinCompare(String pin){
        String[] compare = { "012345", "123456", "234567", "345678", "456789"};
        for(String easyPin : compare){ 
                if (easyPin.equals(pin)){
                    return 0;
            }
        }
        return 1;
    }

    public static double depositCheck(){
        boolean check = true;
        double deposit;
        do{
        System.out.print("Enter deposit: "); 
        deposit = in.nextDouble(); 
        if(deposit < 50.00){
            System.out.println("Error: Insuffiecient Deposit.");
            check = true;
        }
        else{
            System.out.println("Deposit Successfully added!");
            check = false;
        }
    }while(check);
        return deposit;
    }

    public static void menu(){
        System.out.println("Welcome to the PitCoin user " + listAccounts.get(index).getAccHold() + "!");

        int choice;
        do{
        System.out.println("User List: ");
        System.out.println("[1] Deposit");
        System.out.println("[2] Withdraw");
        System.out.println("[3] Edit PIN Code");
        System.out.println("[4] Edit Name");
        System.out.println("[5] Log out");
        System.out.println("[6] View account information");
        System.out.println("[7] Delete Account");
        System.out.print("Enter choice: ");
        choice = in.nextInt();
        
        switch(choice){
            case 1: deposit(); break;
            case 2: withdraw(); break;
            case 3: editPin(); break;
            case 4: editName(); break;
            case 5: logOut(); break;
            case 6: displayAccountInformation(); break;
            case 7: deleteAccount(); break;
            default: System.err.println("Invalid input. Please try again.");
            }
        }while(choice != 5 || choice != 7);
    }
    public static void deposit(){
        System.out.print("Enter amount to deposit: ");
        int amount = in.nextInt();

        in.nextLine();
        System.out.print("Enter PIN: ");
        String pin = in.nextLine();

        listAccounts.get(index).deposit(amount, pin);
    }
    public static void withdraw(){
        System.out.print("Enter amount to withdraw: ");
        int amount = in.nextInt();

        in.nextLine();
        System.out.print("Enter PIN: ");
        String pin = in.nextLine();

        listAccounts.get(index).withdraw(amount, pin);
    }
    public static void editPin(){
        System.out.print("Enter PIN: ");
        String code = in.nextLine();

        System.out.print("Enter new PIN: ");
        String pin = in.nextLine();

        listAccounts.get(index).setPin(code, pin);
    }
    public static void editName(){

        System.out.print("Enter PIN: ");
        String code = in.nextLine();

        System.out.print("Enter new PIN: ");
        String name = in.nextLine();

        listAccounts.get(index).setName(code, name);
    }
    public static void logOut(){
        main(null);
    }
    public static void displayAccountInformation(){
        String Name = listAccounts.get(index).getAccHold();
        String id = listAccounts.get(index).getAccNum();
        String pin = listAccounts.get(index).getPinCode();
        double balance = listAccounts.get(index).getBalance();

        System.out.println("Account Information:");
        System.out.println("Name:           " + Name);
        System.out.println("Account Number: " + id);
        System.out.println("PIN:            " + pin);
        System.out.println("Balance:        P" + balance);
    }
    public static void deleteAccount(){
        listAccounts.remove(index);
        main(null);
    }
}


/*
    TO DO : 

    ✅[1] Fix Increment Letters Functionality
            The current implementation of the "Increment Letters" feature is not functioning as expected.

    ✅[2] Implement LogIn Feature
            Develop a new user registration (signup) feature to allow users to create accounts. 

    ✅[3] Add Edit Details Feature
            Ensure changes are securely saved and validated.

    ✅[4] Develop Show Details Feature
            Implement a feature to display user account details in a clear and organized manner. 

    ✅[5] Implement Remove Account Feature
            Add functionality to allow users to delete their accounts permanently. 


     Bugs to fix:
     [1] Error account number does not exist (LogIn section).
     [2] Infinite Loop in logIn() Function
     [3] Improper Recursive Call in main()
     [4] Incorrect Deposit/withdraw operation  
     [5] Logical Error in Menu Loop Condition
     [6] Non-Updated idNumber in signUp() Method
     [7] Incorrect PIN Length Check in pinCheck()

 */