import java.util.*;
import java.io.*;
public class Login{
    public static Scanner sc = new Scanner(System.in);
    public static String user, pwd; //Strings
    public static void login() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("usernames.dat", true));
        PrintWriter ofile = new PrintWriter(bw);
        ofile.close();
        System.out.println("\fHi. Would you like to LOGIN or CREATE(an account)");
        outerloop:
        do{
            String choice = sc.nextLine().toUpperCase();
            switch(choice){
                case "LOGIN": signin(); break outerloop;
                case "CREATE": create(); break outerloop;
                default: System.out.println("Error! Pls type LOGIN or CREATE");
            }
        }while(true);
    }
    public static void signin() throws IOException{
        outerloop:
        do{    
            System.out.print("Username: ");
            user = sc.nextLine();
            System.out.print("Password: ");
            pwd = sc.nextLine();
            FileReader file=new FileReader("usernames.dat");
            BufferedReader br = new BufferedReader(file);
            int x = 0;
            while(true){
                x++;
                String str = br.readLine();
                if(str == null){
                    System.out.println("No such user. Pls try again");
                    br.close();
                    break;
                }
                if(str.equals(user)){
                    FileReader file1=new FileReader("passwords.dat");
                    BufferedReader b1 = new BufferedReader(file1);
                    String checkPass = null;
                    for(int i = 0; i < x; i++){
                        checkPass = b1.readLine();
                    }
                    if(checkPass.equals(pwd)){
                        System.out.println("Login Successful");
                        break outerloop;
                    }
                    System.out.println("Incorrect password! Try Again!");
                    break;
                }
            }
        }while(true);
    }
    public static void create() throws IOException{
        do{    
            System.out.print("Hi. To create a new account. Pls enter a username: ");
            user = sc.nextLine();
            System.out.print("Pls enter password (Make sure the password has at least 7 and a maximum of 15 characters): ");
            pwd = sc.nextLine();
            System.out.print("Pls re-enter password: ");
            String checkPwd = sc.nextLine();
            FileReader file=new FileReader("usernames.dat");
            BufferedReader br = new BufferedReader(file);
            int x = 0;
            while(true){
                String str = br.readLine();
                if(str == null){
                    br.close();
                    break;
                }
                if(str.equals(user)){
                    System.out.println("Username already taken. Pls try again.");
                    x = 1;
                    br.close();
                    break;
                }
            }
            if(!pwd.equals(checkPwd)){
                System.out.println("Passwords do not match. Pls try again ");
            }
            else if(pwd.length() < 7 || pwd.length() > 15){
                System.out.println("Password does not match criteria. Pls try again ");
            }
            else if(x != 1){
                break;
            }
        }while(true);
        BufferedWriter bw = new BufferedWriter(new FileWriter("usernames.dat", true));
        PrintWriter ofile = new PrintWriter(bw);
        ofile.println(user);
        ofile.close();
        BufferedWriter bw1 = new BufferedWriter(new FileWriter("passwords.dat", true));
        PrintWriter ofile1 = new PrintWriter(bw1);
        ofile1.println(pwd);
        ofile1.close();
        try{
            File newDir = new File(user);
            newDir.mkdir();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}