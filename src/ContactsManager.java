import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

import static java.nio.file.Files.readAllLines;
import static javax.swing.UIManager.getString;

public class ContactsManager {
    static Scanner scanner = new Scanner(System.in);
    static String num;
    static Path contactPath = Paths.get("src", "contacts.txt");

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to contacts manager\n" +
                "\n" +
                "  ______                               _   _____             _             _       \n" +
                "  | ___ \\                             | | /  __ \\           | |           | |      \n" +
                "  | |_/ /__ _ __ ___  ___  _ __   __ _| | | /  \\/ ___  _ __ | |_ __ _  ___| |_ ___ \n" +
                "  |  __/ _ \\ '__/ __|/ _ \\| '_ \\ / _` | | | |    / _ \\| '_ \\| __/ _` |/ __| __/ __|\n" +
                "  | | |  __/ |  \\__ \\ (_) | | | | (_| | | | \\__/\\ (_) | | | | || (_| | (__| |_\\__ \\\n" +
                "  \\_|  \\___|_|  |___/\\___/|_| |_|\\__,_|_|  \\____/\\___/|_| |_|\\__\\__,_|\\___|\\__|___/\n"
        );

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

    //    format numbers
    private static String format(String phone) {
        if (phone.length() == 7) {
            String first = phone.substring(0, 3);
            String second = phone.substring(3, 7);
            return first + "-" + second;
        } else if (phone.length() == 10) {
            String first = phone.substring(0, 3);
            String second = phone.substring(3, 6);
            String third = phone.substring(6, 10);
            return first + "-" + second + "-" + third;
        } else if (phone.length() > 10) {
            return phone;
        } else {
            return "Invalid entry";
        }
    }

    public static void viewCon() throws IOException {
        List<String> contactList = new ArrayList<>();
        try {
            contactList = readAllLines(contactPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Name                    | Phone number \n" +
                "---------------------------------------");
        Collections.sort(contactList);
        for (String line : contactList) {
            String name = line.split("\\|")[0];
            String phone = line.split("\\|")[1];
            phone = format(phone);
            String pipe = "|";
            System.out.printf("%-15s%10s %7s%n", name, pipe, phone);
        }
        try {
            Thread.sleep(1000);
            startup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void addCon() throws IOException {
        System.out.println("Add a contact's name: ");
        String userIn1 = scanner.nextLine();

        System.out.println("Add a contact's number: ");
        String userIn2 = scanner.nextLine();
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
        try {
            List<String> results = Files.readAllLines(contactPath);
            for (int i = 0; i < results.size(); i += 1) {
                if (results.get(i).toLowerCase().contains(userIn)) {
                    String name = results.get(i).split("\\|")[0];
                    String phone = results.get(i).split("\\|")[1];
                    phone = format(phone);
                    System.out.println(name + " | " + phone);
                    try {
                        Thread.sleep(1000);
                        startup();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        startup();
    }

    public static void editCon() throws IOException {
        List<String> lines = Files.readAllLines(contactPath);
        List<String> newList = new ArrayList<>();
        System.out.println("What contact would you like to edit?");
        String userIn1 = scanner.nextLine().toLowerCase();
        String userIn2 = "dumb";
        String replaceThis = "stupid";
        String newCon = "Ah";


        for (int i = 0; i < lines.size(); i += 1) {
            if (lines.get(i).toLowerCase().contains(userIn1)) {
                System.out.println("Is this the contact you are looking for?");
                String name1 = lines.get(i).split("\\|")[0];
                String phone1 = lines.get(i).split("\\|")[1];
                phone1 = format(phone1);
                System.out.println(name1 + " | " + phone1);
                replaceThis = lines.get(i);
                String contactConfirm = scanner.nextLine();
                if (contactConfirm.toLowerCase().contains("y")) {
                    System.out.println("Please write a replacement name.");
                    userIn2 = scanner.nextLine();
                    String name = replaceThis.split("\\|")[0];
                    String phone = replaceThis.split("\\|")[1];
                    newCon = (userIn2 + "|" + phone);
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
        List<String> lines = Files.readAllLines(contactPath);
        List<String> newList = new ArrayList<>();
        System.out.println("What contact would you like to delete?");
        String userIn1 = scanner.nextLine().toLowerCase();
        String replaceThis;


        for (int i = 0; i < lines.size(); i += 1) {
            if (lines.get(i).toLowerCase().contains(userIn1)) {
                System.out.println("The selected contact is:  " + lines.get(i));
                replaceThis = lines.get(i);
                System.out.println("Are you sure you want to delete this contact?.");
                String yesorno = scanner.nextLine();
                if (yesorno.toLowerCase().contains("y")) {
                    for (String line : lines) {
                        if (line.contains(replaceThis)) {
                            continue;
                        }
                        newList.add(line);
                    }
                    Files.write((contactPath), newList);
                    startup();

                }
            }
        }
    }

}
