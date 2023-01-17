package menu;

import java.util.Scanner;

public class Menu {

    static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        System.out.println("Pick a role to continue: ");
        System.out.println("1. Client");
        System.out.println("2. Admin");

        System.out.println("-----------------");
        System.out.println("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                clientMenu();
                break;
            case 2:
                adminMenu();
                break;
            default:
                System.out.println("Invalid option!");
                mainMenu();
                break;
        }
    }


    public static void clientMenu() {
        System.out.println("Pick a submenu to continue: ");
        System.out.println("1. Check out");
        System.out.println("2. Return");

        System.out.println("-----------------");
        System.out.println("Select an option or enter 0 to go back to change the role: ");
        int option = scanner.nextInt();

        switch (option) {
            case 0:
                mainMenu();
                break;
            case 1:
                //    Item.getAllItems();
                clientMenu();
                break;
            case 2:
                //    Item.createNewItem();
                clientMenu();
                break;
            default:
                System.out.println("Invalid option!");
                clientMenu();
                break;
        }
    }

    public static void adminMenu() {
        System.out.println("Pick a submenu to continue: ");
        System.out.println("1. Add a book");
        System.out.println("2. Update a book");
        System.out.println("3. Delete a book");
        System.out.println("4. Manage user accounts");

        System.out.println("-----------------");
        System.out.println("Select an option or enter 0 to go back to change the role: ");
        int option = scanner.nextInt();

        switch (option) {
            case 0:
                mainMenu();
                break;
            case 1:
                //    Item.getAllItems();
                adminMenu();
                break;
            case 2:
                //    Item.createNewItem();
                adminMenu();
                break;
            case 3:
                //    Item.createNewItem();
                adminMenu();
                break;
            case 4:
                //    Item.createNewItem();
                adminMenu();
                break;
            default:
                System.out.println("Invalid option!");
                adminMenu();
                break;
        }
    }

}