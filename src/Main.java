import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<User> users = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            User user = new User();
            ArrayList<Item> inventory = new ArrayList<>();

            //// Check if user is known, and create if user is unknown (user.name == "undefined") with default name value.
            boolean userUnknown = true;
            while (userUnknown){
                System.out.println("Welcome! Please enter your name.");
                String name = Validation.validateString();
                user = User.searchUser(name);

                if (user.getName() == "unlisted") {
                    user = User.createUser(name);
                    users.add(user);
                }
                else {
                    userUnknown = false;
                }
            }
            //// Get user's custom inventory by authenticating their password.
            inventory = User.retrieveUser(user);

            //// Keep users in the action loop of managing their inventory until they decide to "log out"
            boolean loggedIn = true;
            while (loggedIn){
                System.out.println(" \nPlease Enter:");
                System.out.println("[1] to Display your Items");
                System.out.println("[2] to Add an Item");
                System.out.println("[3] to Change an Item's Quantity");
                System.out.println("[4] to Remove an Item");
                System.out.println("[5] to Log Out");

                String userChoice = "";
                userChoice = scanner.nextLine();

                Item item = null;

                switch (userChoice) {
                    case "1":
                        /// display
                        if (user.getInventory().size() > 0) {
                            System.out.println("You have:");
                            for (Item item1 : inventory) {
                                System.out.printf("[%s] %s type: %s\n", item1.getQuantity(), item1.getName(), item1.getCategory());
                            }
                        }
                        else {
                            System.out.println("\nYour inventory is currently empty. Please add some items.");
                        }
                        break;
                    case "2":
                        /// add
                        item = addItem(inventory);
                        inventory.add(item);
                        user.setInventory(inventory);
                        break;
                    case "3":
                        /// change quantity
                        if (inventory.size() > 0) {
                            item = null;
                            while (item == null) {
                                System.out.println("\nPlease Enter the name of the item you would like to change.");
                                item = Item.searchItem(inventory);
                                if (item == null) {
                                    System.out.println("That name did not match any item in you inventory.");
                                }
                            }

                            System.out.printf("\nYou currently have %s of the %s %s.", item.getQuantity(), item.getCategory(), item.getName());
                            System.out.println("\nPlease Enter a new quantity for this item.");
                            item = Item.changeQuantity(item);
                            user.setInventory(inventory);
                        }
                        else {
                            System.out.println("\nYou currently have no Items in your inventory. Please add some first.");
                        }
                        break;
                    case "4":
                        /// remove
                        if (inventory.size() > 0) {
                            item = null;
                            while (item == null) {
                                System.out.println("\nPlease Enter the name of the item you would like to remove.");
                                item = Item.searchItem(inventory);
                                if (item == null) {
                                    System.out.println("That name did not match any item in you inventory.");
                                }
                            }
                            inventory.remove(item);
                            user.setInventory(inventory);
                            System.out.println("\nItem Removed.");
                        }
                        else {
                            System.out.println("\nYou currently have no Items in your inventory. Please add some first.");
                        }
                        break;
                    case "5":
                        /// logout
                        loggedIn = false;
                        break;
                    default:
                        System.out.println("\nInvalid Input. You must Enter 1, 2, 3, 4, or 5.");
                }

            }
        }
    }

    public static Item addItem(ArrayList<Item> inventory) {
        String userChoice = "";
        boolean categoryNotChosen = true;
        while (categoryNotChosen) {
            System.out.println("\nPlease Select the Type of Item to Add:");
            System.out.println("[1] Food");
            System.out.println("[2] Tool");
            System.out.println("[3] Junk");
            System.out.println("[4] Weapon");
            System.out.println("[5] Armor");


            userChoice = Main.scanner.nextLine();
            if (userChoice.equalsIgnoreCase("1") ||
                    userChoice.equalsIgnoreCase("2") ||
                    userChoice.equalsIgnoreCase("3") ||
                    userChoice.equalsIgnoreCase("4") ||
                    userChoice.equalsIgnoreCase("5")) {
                categoryNotChosen = false;
            }
            else {
                System.out.println("%s is not a Valid Type Selection. Enter 1, 2, 3, 4, or 5.");
            }
        }

        String itemName = "";
        while (itemName.equalsIgnoreCase("")) {
            System.out.println("\nPlease Enter the name of the item.");
            itemName = Item.retrieveItemName(inventory);

            if (itemName.equalsIgnoreCase("")) {
                System.out.println("\nThat item already exists in your inventory.");
            }
        }

        System.out.println("\nPlease Enter the amount you have.");
        Integer itemAmount = Validation.validatePositiveInt();

        Item item = null;

        switch (userChoice) {
            case "1":
                item = new Food(itemName, itemAmount);
                break;
            case "2":
                item = new Tool(itemName, itemAmount);
                break;
            case "3":
                item = new Junk(itemName, itemAmount);
                break;
            case "4":
                item = new Weapon(itemName, itemAmount);
                break;
            case "5":
                item = new Armor(itemName, itemAmount);
                break;

        }

        return item;
    }
}
