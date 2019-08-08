import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Files.readAllLines;
import static javax.swing.UIManager.getString;

public class ContactsManager {
    String name;
    String phone;

    static Scanner scanner = new Scanner(System.in);
    static String num;
    static Path contactPath = Paths.get("src", "contacts.txt");

    //    private static String contactFormat = " |  %n";
    public boolean yesNo() {
        String input = scanner.nextLine();
        return input.equals("yes") || input.equals("y");
    }

    public static void main(String[] args) throws IOException {
//        Path datafile = Paths.get(".", "data", "data.txt");
//        System.out.println(datafile);
        startup();
    }

    public static void startup() throws IOException {
        System.out.println("\n1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Edit existing contact.\n" +
                "5. Delete an existing contact.\n" +
                "6. Exit.\n" +
                "Enter an option (1, 2, 3, 4, 5, or 6):\n");
        num = scanner.nextLine();

        switch (num) {
            case "1":
                viewCon();
                break;

            case "2":
                addCon();
                break;

            case "3":
                searchCon();
                break;

            case "4":
                editCon();
                break;

            case "5":
                deleteCon();
                break;

            case "6":
                System.out.println("Thanks for using Personal Contacts, the leading faux-contact manager");
                System.exit(0);
                break;

            default:
                System.out.println("Hey, that wasn't an option. Please enter in your option again.");
                startup();
        }

    }

    //format numbers
//    private static String format(String phone) {
//        if (phone.length() == 7) {
//            String first = phone.substring(0, 3);
//            String second = phone.substring(3, 7);
//            return first + "-" + second;
//        } else if (num.length() == 10) {
//            String first = phone.substring(0, 3);
//            String second = phone.substring(3, 6);
//            String third = phone.substring(6, 10);
//            return first + "-" + second + "-" + third;
//        } else if (phone.length() > 10) {
//            return phone;
//        } else {
//            return "Invalid entry";
//        }
//    }

    public static void viewCon() throws IOException {
//        System.out.println("viewCon works!");
        //TODO: Print out each line in an existing file, along with the line number skeleton method - 1

        List<String> contactList = new ArrayList<>();
        try {
            contactList = readAllLines(contactPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fin = "";
        int countThis = 1;
        for (String line : contactList) {
            String name = line.split("\\|")[0];
            String phone = line.split("\\|")[1];
            System.out.printf("%s | %s%n", name, phone);
        }
        startup();

    }

    public static void addCon() throws IOException {
        System.out.println("Add a contact's name: ");
        //TODO: Add a line to an existing file skeleton method - 2
        String userIn1 = scanner.nextLine();

        System.out.println("Add a contact's number: ");
        //TODO: Add a line to an existing file skeleton method - 2
        String userIn2 = scanner.nextLine();
//        String addNumber = scanner.nextLine();
//        String formattedNumber = format(addNumber);
//        String addContact = userIn + "|" + formattedNumber;
        try {
            Files.write(
                    Paths.get("src", "contacts.txt"),
                    Arrays.asList(userIn1 + "|" + userIn2), // list with one item
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        startup();


    }

    public static void searchCon() throws IOException {
        System.out.println("Search by name: ");

        String userIn = scanner.nextLine().toLowerCase();
        //TODO: if exists skeleton method - 3
        try {
//                System.out.println("That contact exists!");
            List<String> results = Files.readAllLines(contactPath);
            for (int i = 0; i < results.size(); i += 1) {
                if (results.get(i).toLowerCase().contains(userIn)) {
                    System.out.println(results.get(i));
                    startup();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        startup();
    }

    public static void editCon() throws IOException {
//        System.out.println("editCon works!");
//TODO: Replace "milk" with "cream" - Optional, would be cool to have an edit feature
        List<String> lines = Files.readAllLines(contactPath);
        List<String> newList = new ArrayList<>();
        System.out.println("What contact would you like to edit?");
        String userIn1 = scanner.nextLine().toLowerCase();
        String userIn2 = "dumb";
        String replaceThis = "stupid";
        String newCon = "Ah";


        for (int i = 0; i < lines.size(); i += 1) {
            if (lines.get(i).toLowerCase().contains(userIn1)) {
                System.out.println("Is this the contact you are looking for? " + lines.get(i));
                replaceThis = lines.get(i);
                String contactConfirm = scanner.nextLine();
                if (contactConfirm.toLowerCase().contains("y")) {
                    System.out.println("Please write a replacement name.");
                    userIn2 = scanner.nextLine();
                        String name = replaceThis.split("\\|")[0];
                        String phone = replaceThis.split("\\|")[1];
                        newCon = (userIn2 +"|" + phone);
                    for (String line : lines) {
                        if (line.contains(replaceThis)) {
                            newList.add(newCon);
                            continue;
                        }
                        newList.add(line);
                    }
                    Files.write((contactPath), newList);
                    startup();
                }

            } else if (i == lines.size() - 1) {
                System.out.println("The contact you are looking for does not exist.");
                startup();
            }
        }
    }

    public static void deleteCon() throws IOException {
//        System.out.println("deleteCon works!");
        startup();
        //TODO: list and write to file skeleton method - 4 but replace with nothing.
        String deleteContact = scanner.nextLine();
        List<String> contactList = new ArrayList<>();

//    Path filepath = Paths.get("data", "groceries.txt");
//Files.write(filepath, groceryList);


    }


}
