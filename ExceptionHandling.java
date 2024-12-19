import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class ExceptionHandling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            // Show the menu so the user can pick an exception to test
            System.out.println("\n=== Exception Handling Menu ===");
            System.out.println("1. IOException");
            System.out.println("2. FileNotFoundException");
            System.out.println("3. EOFException");
            System.out.println("4. SQLException");
            System.out.println("5. ClassNotFoundException");
            System.out.println("6. ArithmeticException");
            System.out.println("7. NullPointerException");
            System.out.println("8. ArrayIndexOutOfBoundsException");
            System.out.println("9. ClassCastException");
            System.out.println("10. IllegalArgumentException");
            System.out.println("11. NumberFormatException");
            System.out.println("0. Cancel/Exit");
            System.out.print("Enter your choice (0-11): ");

            // Make sure the user enters a valid number
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number between 0 and 11.");
                System.out.print("Enter your choice: ");
                scanner.next();
            }

            choice = scanner.nextInt();

            // Exit if the user chooses 0
            if (choice == 0) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }

            // Handle invalid menu choices
            if (choice < 0 || choice > 11) {
                System.out.println("Invalid choice. Try again!");
                continue;
            }

            // Run the corresponding method based on the user's choice
            switch (choice) {
                case 1 -> handleIOException(scanner);
                case 2 -> handleFileNotFoundException(scanner);
                case 3 -> handleEOFException(scanner);
                case 4 -> handleSQLException(scanner);
                case 5 -> handleClassNotFoundException(scanner);
                case 6 -> handleArithmeticException(scanner);
                case 7 -> handleNullPointerException(scanner);
                case 8 -> handleArrayIndexOutOfBoundsException(scanner);
                case 9 -> handleClassCastException(scanner);
                case 10 -> handleIllegalArgumentException(scanner);
                case 11 -> handleNumberFormatException(scanner);
            }
        }

        scanner.close(); // Done with input, so close the scanner
    }

    // This method tries to read from a file and shows an error if it fails
    public static void handleIOException(Scanner scanner) {
        System.out.print("Enter the filename to read: ");
        String filename = scanner.next();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            System.out.println("File content: " + reader.readLine());
        } catch (IOException e) {
            System.out.println("Error: File not found or cannot be read. Please try again.");
        }
    }

    // This method tries to open a file and handles cases where the file doesn't exist
    public static void handleFileNotFoundException(Scanner scanner) {
        System.out.print("Enter the filename to open: ");
        String filename = scanner.next();
        try {
            FileInputStream file = new FileInputStream(filename);
            System.out.println("File opened successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: The specified file does not exist. Try again.");
        }
    }

    // This method simulates reaching the end of a file while reading it
    public static void handleEOFException(Scanner scanner) {
        System.out.print("Enter the file name to read: ");
        String filename = scanner.next();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            while (true) {
                System.out.println(dis.readUTF());
            }
        } catch (EOFException e) {
            System.out.println("Reached the end of the file.");
        } catch (IOException e) {
            System.out.println("Error: Unable to read the file. Try again.");
        }
    }

    // This method tries to connect to a database and handles errors
    public static void handleSQLException(Scanner scanner) {
        System.out.print("Enter database URL: ");
        String url = scanner.next();
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println("Error: Failed to connect to the database. Check your credentials.");
        }
    }

    // This method simulates loading a class that doesn't exist
    public static void handleClassNotFoundException(Scanner scanner) {
        System.out.print("Enter class name to load: ");
        String className = scanner.next();
        try {
            Class.forName(className);
            System.out.println("Class loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Class not found. Check the name and try again.");
        }
    }

    // This method performs division and handles division by zero
    public static void handleArithmeticException(Scanner scanner) {
        System.out.print("Enter numerator: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Enter a valid number.");
            System.out.print("Enter numerator: ");
            scanner.next();
        }
        int numerator = scanner.nextInt();

        System.out.print("Enter denominator: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Enter a valid number.");
            System.out.print("Enter denominator: ");
            scanner.next();
        }
        int denominator = scanner.nextInt();

        try {
            int result = numerator / denominator;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        }
    }

    // This method tries to access a string and simulates null input
    public static void handleNullPointerException(Scanner scanner) {
        System.out.print("Enter a string (or type 'null' to simulate null): ");
        String input = scanner.next();
        try {
            if ("null".equals(input)) input = null;
            System.out.println("String length: " + input.length());
        } catch (NullPointerException e) {
            System.out.println("Error: You tried to access a null object.");
        }
    }

    // This method accesses an array and handles invalid index errors
    public static void handleArrayIndexOutOfBoundsException(Scanner scanner) {
        int[] array = {10, 20, 30, 40, 50};
        System.out.print("Enter array index to access (0-4): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Enter a number between 0 and 4.");
            System.out.print("Enter array index: ");
            scanner.next();
        }
        int index = scanner.nextInt();

        try {
            System.out.println("Value at index " + index + ": " + array[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Index out of range. Try again.");
        }
    }

    // This method tries an invalid type cast
    public static void handleClassCastException(Scanner scanner) {
        try {
            Object obj = "Test String";
            System.out.println("Attempting an invalid cast...");
            Integer num = (Integer) obj; // This will fail
        } catch (ClassCastException e) {
            System.out.println("Error: You cannot cast that object to the desired type.");
        }
    }

    // This method tries to sleep for a user-given duration and handles bad values
    public static void handleIllegalArgumentException(Scanner scanner) {
        System.out.print("Enter sleep duration in milliseconds: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Enter a valid number.");
            System.out.print("Enter sleep duration: ");
            scanner.next();
        }
        int duration = scanner.nextInt();

        try {
            Thread.sleep(duration);
            System.out.println("Slept for " + duration + " milliseconds.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Negative sleep durations are not allowed.");
        } catch (InterruptedException e) {
            System.out.println("Sleep was interrupted.");
        }
    }

    // This method tries to parse a string into a number
    public static void handleNumberFormatException(Scanner scanner) {
        System.out.print("Enter a number to parse: ");
        String input = scanner.next();
        try {
            int num = Integer.parseInt(input);
            System.out.println("Parsed number: " + num);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. Enter digits only.");
        }
    }
}