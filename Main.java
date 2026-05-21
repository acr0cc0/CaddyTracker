import java.util.Scanner;

public class Main {
    // private static final ArrayList<Loop> list = new ArrayList<>();
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner text = new Scanner(System.in);
        boolean running = true;

        // Display the main menu
        Utility.displayMenu();

        // Process user input
        while (running) {
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            
            switch (choice) {
                case 0: // Check for the quit option
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                case 1: // add loops to list
                    System.out.println("Adding loops...\n");
                    boolean cont = true;
                    while (cont) {
                        Utility.addLoop();
                        // Utility.writeFile(l);
                        System.out.print("\nLoop added!\n\nAdd another? y/n\t");
                        String x = text.nextLine();
                        if (x.equals("n")) { 
                            cont = false;
                            break;
                        }
                    }
                    // displayMenu();
                    break;
                case 2: // read file + print loops
                    System.out.println("Printing loops...\n");
                    // read file 
                    Utility.printList();
                    // displayMenu();
                    break;
                case 3: // display total money made
                    System.out.println("Total money");
                    System.out.println("\nYou made $" + Utility.totalMoney() + " so far this summer");
                    // displayMenu();
                    break;
                case 4: // display avg per loop
                    System.out.println("Average money per loop");
                    System.out.println("\nYou are averaging $" + Utility.summerAverage() + " so far this summer");
                    // displayMenu();
                    break;
                default: // handle misinput
                    System.out.println("Invalid choice.");
                    // displayMenu();
                    break;
            }

            // Clear input buffer after reading integers
            scanner.nextLine();
            // text.nextLine();
        }

        scanner.close();
        // text.close();
    } // end main method

    
}
