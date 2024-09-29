import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContactManagementSystem {
    private Map<String, Contact> contacts;

    public ContactManagementSystem() {
        this.contacts = new HashMap<>();
    }

    public void addContact(String name, String phoneNumber, String email) {
        contacts.put(name, new Contact(phoneNumber, email));
        System.out.println("Contact '" + name + "' added successfully!");
    }

    public void viewContacts() {
        if (!contacts.isEmpty()) {
            System.out.println("Contacts:");
            for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
                String name = entry.getKey();
                Contact info = entry.getValue();
                System.out.println("Name: " + name + ", Phone Number: " + info.getPhoneNumber() + ", Email: " + info.getEmail());
            }
        } else {
            System.out.println("No contacts found!");
        }
    }

    public void searchContact(String name) {
        if (contacts.containsKey(name)) {
            Contact info = contacts.get(name);
            System.out.println("Name: " + name + ", Phone Number: " + info.getPhoneNumber() + ", Email: " + info.getEmail());
        } else {
            System.out.println("Contact '" + name + "' not found!");
        }
    }

    public void editContact(String name) {
        if (contacts.containsKey(name)) {
            Scanner scanner = new Scanner(System.in);
            Contact info = contacts.get(name);
            System.out.println("Editing contact '" + name + "':");

            System.out.print("Enter new phone number (leave empty to keep current): ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter new email (leave empty to keep current): ");
            String email = scanner.nextLine();

            if (!phoneNumber.isEmpty()) {
                info.setPhoneNumber(phoneNumber);
            }
            if (!email.isEmpty()) {
                info.setEmail(email);
            }

            System.out.println("Contact updated successfully!");
        } else {
            System.out.println("Contact '" + name + "' not found!");
        }
    }

    public void deleteContact(String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("Contact '" + name + "' deleted successfully!");
        } else {
            System.out.println("Contact '" + name + "' not found!");
        }
    }

    public void saveContacts(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
                String name = entry.getKey();
                Contact info = entry.getValue();
                writer.write(name + "," + info.getPhoneNumber() + "," + info.getEmail());
                writer.newLine();
            }
            System.out.println("Contacts saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ContactManagementSystem contactManager = new ContactManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Contact Management System");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Edit Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Save Contacts");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    contactManager.addContact(name, phoneNumber, email);
                    break;
                case "2":
                    contactManager.viewContacts();
                    break;
                case "3":
                    System.out.print("Enter name to search: ");
                    name = scanner.nextLine();
                    contactManager.searchContact(name);
                    break;
                case "4":
                    System.out.print("Enter name to edit: ");
                    name = scanner.nextLine();
                    contactManager.editContact(name);
                    break;
                case "5":
                    System.out.print("Enter name to delete: ");
                    name = scanner.nextLine();
                    contactManager.deleteContact(name);
                    break;
                case "6":
                    System.out.print("Enter filename to save contacts: ");
                    String filename = scanner.nextLine();
                    contactManager.saveContacts(filename);
                    break;
                case "7":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

class Contact {
    private String phoneNumber;
    private String email;

    public Contact(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
