import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Utility {
    private static final ArrayList<Loop> list = new ArrayList<>();
    private static boolean loaded = false;

    public static int totalMoney() {
        int total = 0;
        for (Loop l : list) {
            total += l.getMoney();
        }
        return total;
    }

    public static double summerAverage() {
        if (list.isEmpty()) {
            return 0.0;
        }
        return (double) totalMoney() / list.size();
    }

    public static List<Loop> getLoops() {
        loadLoops();
        return Collections.unmodifiableList(list);
    }

    public static void loadLoops() {
        if (!loaded) {
            readFile();
            loaded = true;
        }
    }

    public static void addLoop(Loop loop) {
        list.add(loop);
        writeFile(loop);
    }

    @SuppressWarnings("resource")
    public static void addLoop() {
        Scanner scanner = new Scanner(System.in);
        Scanner text = new Scanner(System.in);

        System.out.print("Enter Date (yyyy-mm-dd):\t");
        String loopDate = text.nextLine();
        String[] temp = loopDate.split("-");
        int y = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int d = Integer.parseInt(temp[2]);
        Date date = new Date(y, m, d);

        System.out.print("Enter money earned:\t");
        int money = scanner.nextInt();

        System.out.print("Enter name:\t");
        String name = text.nextLine();

        Loop loop = new Loop(date, money, name);
        addLoop(loop);
    }

    public static void printList() {
        loadLoops();

        for (Loop l : list) {
            System.out.println(l);
        }
    }

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
        try (BufferedReader reader = new BufferedReader(new FileReader(AppData.getDataFilePath().toString()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] temp = line.split("-");
                if (temp.length != 5) {
                    continue;
                }

                String name = temp[0];
                int year = Integer.parseInt(temp[1]);
                int month = Integer.parseInt(temp[2]);
                int day = Integer.parseInt(temp[3]);
                int money = Integer.parseInt(temp[4]);

                Loop loop = new Loop(new Date(year, month, day), money, name);
                list.add(loop);
            }
        } catch (IOException e) {
            // No saved file yet. The program will create one when the user adds a loop.
        }
    }

    public static void writeFile(Loop l) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AppData.getDataFilePath().toString(), true))) {
            writer.write(l.output());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
