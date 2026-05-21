// package Java.Caddy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utility {
    // private static int counter = 0;
    private static final ArrayList<Loop> list = new ArrayList<>();
    
    public static int totalMoney() {
        int total = 0;
        for (Loop l : list) {
            total += l.getMoney();
        }
        return total;
    }

    public static double summerAverage() {
        int total = totalMoney();
        return (double) total / list.size();
    }

    public static void addLoop() {
        Scanner scanner = new Scanner(System.in);
        Scanner text = new Scanner(System.in);

        System.out.print("Enter Date (yyyy-mm-dd):\t");
        String loopDate = text.nextLine();
        String[] temp = loopDate.split("-");
        Integer year = Integer.valueOf(temp[0]);
        int y = year;
        Integer month = Integer.valueOf(temp[1]);
        int m = month;
        Integer day = Integer.valueOf(temp[2]);
        int d =day;
        Date date = new Date(y,m,d);

        System.out.print("Enter money earned:\t");
        int money = scanner.nextInt();
        System.out.print("Enter name:\t");
        String name = text.nextLine();

        Loop loop = new Loop(date, money, name);
        list.add(loop);

        // write to file
        writeFile(loop); 

        // scanner.close();
        // text.close();
    }

    public static void printList(){
        if (list.isEmpty() || list == null) {
            readFile();
        }
        
        for (Loop l : list) {
            System.out.println(l);
        }
    }

    // Helper method to display the menu
    public static void displayMenu() {
        System.out.println("================================");
        System.out.println(" Welcome to the Caddy Tracker!");
        System.out.println("================================");
        System.out.println("Please select an option:");
        System.out.println("0) Quit");
        System.out.println("1) Enter loops");
        System.out.println("2) Print List");
        System.out.println("3) Money made so far");
        System.out.println("4) Average money per loop");
        System.out.println("================================");
    }

    public static void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("loops.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] temp = line.split("-");
                String name = temp[0];
                Integer year = Integer.valueOf(temp[1]);
                Integer month = Integer.valueOf(temp[2]);
                Integer day = Integer.valueOf(temp[3]);
                Date date = new Date(year, month, day);
                Integer money = Integer.valueOf(temp[4]);
                Loop loop = new Loop(date, money, name);
                list.add(loop);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static void writeFile(Loop l) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("loops.txt", true));
            String loop = l.output();
            writer.write(loop);
            writer.newLine();  
            writer.flush();     
            writer.close();
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


}

