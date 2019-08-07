import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {
    String name;
    String phone;

    static Scanner scanner = new Scanner(System.in);
    static String num;
    static Path contactPath = Paths.get("src", "contacts.txt");

    public static void main(String[] args) {
//        Path datafile = Paths.get(".", "data", "data.txt");
//        System.out.println(datafile);
        startup();
    }

    public static void startup() {
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

    public static void viewCon() {
//        System.out.println("viewCon works!");
        //TODO: Print out each line in an existing file, along with the line number skeleton method - 1

        List<String> contactList = new ArrayList<>();
        try {
            contactList = Files.readAllLines(contactPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fin = "";
        int countThis = 1;
        for (int i = 0; i < contactList.size(); i += 1) {
            if (countThis % 2 == 0) {
                fin += contactList.get(i);
                System.out.println(fin);
                countThis -= 1;
            } else {
                countThis++;
                fin = contactList.get(i) + " | ";
            }

        }
        startup();

    }

    public static void addCon() {
        System.out.println("Add a contact's name: ");
        //TODO: Add a line to an existing file skeleton method - 2
        String userIn = scanner.nextLine();
        try {
            Files.write(
                    Paths.get("src", "contacts.txt"),
                    Arrays.asList(userIn), // list with one item
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Add a contact's number: ");
        //TODO: Add a line to an existing file skeleton method - 2
        userIn = scanner.nextLine();
        try {
            Files.write(
                    Paths.get("src", "contacts.txt"),
                    Arrays.asList(userIn), // list with one item
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        startup();


    }

    public static void searchCon() {
        System.out.println("searchCon works!");
        startup();
        //TODO: if exists skeleton method - 3
//    String directory = "data";
//    String filename = "info.txt";
//
//    Path dataDirectory = Paths.get(directory);
//    Path datafile = Paths.get(directory, filename);
////            try {
//        if (Files.notExists(dataDirectory)) {
//        Files.createDirectories(dataDirectory);
//    } else if (!Files.exists(datafile)){
//                Files.createFile(dataFile);
//            }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//

    }

    public static void editCon() {
        System.out.println("editCon works!");
        startup();
//TODO: Replace "milk" with "cream" - Optional, would be cool to have an edit feature
//    List<String> lines = Files.readAllLines(Paths.get("data", "groceries.txt"));
//    List<String> newList = new ArrayList<>();
//
//for (String line : lines) {
//        if (line.equals("milk")) {
//            newList.add("cream");
//            continue;
//        }
//        newList.add(line);
//    }
//
//Files.write(Paths.get("data", "groceries.txt"), newList);

    }

    public static void deleteCon() {
        System.out.println("deleteCon works!");
        startup();
        //TODO: list and write to file skeleton method - 4 but replace with nothing.
//    List<String> groceryList = Arrays.asList("coffee", "milk", "sugar");
//    Path filepath = Paths.get("data", "groceries.txt");
//Files.write(filepath, groceryList);

    }


}
