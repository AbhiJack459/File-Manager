import java.util.*;
import java.io.*;
public class FileManager extends Login{
    public static Scanner sc = new Scanner(System.in);
    public static Login l = new Login(); 
    public static String user, userFiles;
    public static void main(String [] args) throws IOException{
        l.login();
        user = l.user;
        menu();
        userFiles = user + "/files.dat";
        BufferedWriter bw = new BufferedWriter(new FileWriter(userFiles, true));
        PrintWriter ofile = new PrintWriter(bw);
        ofile.close();
        do{
            System.out.print("Pls enter a command: ");
            String command = sc.nextLine();
            switch(command){
                case "ls": ls(); break;
                case "make": make(); break;
                case "disp": disp(); break;
                case "write": write(); break;
                case "clear": menu(); break;
                case "QUIT" : System.out.println("Thanks for using this FileManager"); System.exit(0);
                default: System.out.println("Wrong command! Try again!");
            }
        }while(true);
    }
    public static void menu(){
        System.out.println("\fHello "+user+"!\nCommands Explained\nls- lists all files created by you\nmake - makes a new file\ndisp - displays a file of your choice\nwrite - overwrite or write in a a file\nclear - clears screen\nQUIT - quits program");
    }
        public static void ls() throws IOException{
        FileReader file=new FileReader(userFiles);
        BufferedReader br = new BufferedReader(file);
        while(true){    
            String fileName = br.readLine();
            if(fileName == null){
                System.out.println("---End Of File---");
                break;
            }
            System.out.println(fileName);
        }
        br.close();
    }
    public static void make() throws IOException{
        boolean fileExists = false;
        String newFile;
        do{
            fileExists = false;
            System.out.print("Enter name of file you would like to make(without extension): ");
            newFile = sc.nextLine();
            newFile = newFile + ".dat";
            FileReader file = new FileReader(userFiles);
            BufferedReader br = new BufferedReader(file);
            while(true){    
                String fileName = br.readLine();
                if(fileName == null){
                    break;
                }
                if(fileName.equals(newFile)){
                    System.out.println("File Already Exists. Pls Try Again.");
                    fileExists = true;
                    break;
                }
            }
            br.close();
        }while(fileExists);
        BufferedWriter bw = new BufferedWriter(new FileWriter(userFiles, true));
        PrintWriter ofile = new PrintWriter(bw);
        ofile.println(newFile);
        ofile.close();
        newFile = user + "/" + newFile;
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(newFile, true));
        PrintWriter ofile1 = new PrintWriter(bw1);
        ofile1.close();
    }
    public static void disp() throws IOException{
        boolean fileDoesntExist = false;
        String FileToBeRead;
        do{
            fileDoesntExist = true;
            System.out.print("Name file you would like to read(with extension '.dat'): ");
            FileToBeRead = sc.nextLine();
            FileReader file=new FileReader(userFiles);
            BufferedReader br = new BufferedReader(file);
            while(true){    
                String fileName = br.readLine();
                if(fileName == null){
                    System.out.println("Sorry. File doesn't exist. Pls try again. Make sure you have used the '.dat' extension");
                    break;
                }
                if(fileName.equals(FileToBeRead)){
                    fileDoesntExist = false;
                    break;
                }
            }
            br.close();
        }while(fileDoesntExist);
        FileToBeRead = user + "/" + FileToBeRead;
        FileReader file=new FileReader(FileToBeRead);
        BufferedReader br = new BufferedReader(file);
        while(true){    
            String str = br.readLine();
            if(str == null){
                System.out.println("---End Of File---");
                break;
            }
            System.out.println(str);
        }
        br.close();
    }
    public static void write() throws IOException{
        boolean fileDoesntExist = false;
        String FileToWriteOn;
        String opt;
        do{
            fileDoesntExist = true;
            System.out.print("Name file you would like to write on(with extension '.dat'): ");
            FileToWriteOn = sc.nextLine();
            FileReader file=new FileReader(userFiles);
            BufferedReader br = new BufferedReader(file);
            while(true){    
                String fileName = br.readLine();
                if(fileName == null){
                    System.out.println("Sorry. File doesn't exist. Pls try again or create the file first. Make sure you have used the '.dat' extension");
                    break;
                }
                if(fileName.equals(FileToWriteOn)){
                    fileDoesntExist = false;
                    break;
                }
            }
            br.close();
        }while(fileDoesntExist);
        BufferedWriter bw;
        do{
            System.out.println("Would you like to overwrite this file or add to it(O/A)");
            opt = sc.next().toUpperCase();
        }while(!(opt.equals("O") || opt.equals("A")));
        FileToWriteOn = user + "/" + FileToWriteOn;
        if(opt.equals("O")){
            bw = new BufferedWriter(new FileWriter(FileToWriteOn));
        } 
        else{
            bw = new BufferedWriter(new FileWriter(FileToWriteOn,true));
        } 
        PrintWriter ofile = new PrintWriter(bw);
        System.out.println("Hi start writing. Press enter for new line. Write '/CLOSE/' to stop writing");
        do{
            String str = sc.nextLine();
            if(str.equals("/CLOSE/")){
                ofile.close();
                break;
            }
            ofile.print(str + "\n");
        }while(true);
    }
}