import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingListManager implements Shopping {

    String addToCategory = "";
    double initialBudget = 0;
    double shoppingBudget = 0;
    int choice = 0;
    int itemCount = 0;
    int totalQtyOfAllItems = 0;

    // creates item object
    Item item = new Item();

    // scanner
    Scanner scanner = new Scanner(System.in);

    // decimal format
    DecimalFormat df = new DecimalFormat("##.##");

    double itemToPurchaseTimesQty = item.getItemPrice() * (int) item.getItemQuantity();

    private ArrayList<Item> items;
    private ArrayList<Item> categories;

    public ShoppingListManager() {
        this.items = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    void menu() {
        System.out.println("(1) Add item");
        System.out.println("(2) Update item");
        System.out.println("(3) Remove item");
        System.out.println("(4) View all item(s)");
        System.out.println("(5) View all categories");

        System.out.print("enter choice: ");
        choice = scanner.nextInt();
    }

    void userJourney() {
        // branding
        shopNBud();

        // users budget
        System.out.print("enter budget amount: $");
        shoppingBudget = scanner.nextDouble();
        scanner.nextLine();
        initialBudget = shoppingBudget;

        // user menu
        menu();

        boolean running = true;

        while (running) {
            switch (choice) {
                case 1 -> addItem();
                case 2 -> updateItem();
                case 3 -> removeItem();
                case 4 -> viewAllItems();
                case 5 -> viewAllCategories();
                case 9 -> {
                    System.out.println("exiting program");
                    running = false;
                }
                default -> System.out.println("invalid choice");
            }
        }
        // close scanner
        scanner.close();
    }

    @Override
    public void addItem() {
        // item name
        System.out.print("enter item name: ");
        String itemName = scanner.next();
        item.setItemName(itemName);
        scanner.nextLine();

        // item price
        System.out.print("enter item price: $");
        item.setItemPrice(scanner.nextDouble());

        // item qty
        System.out.print("enter item quantity: ");
        item.setItemQuantity(scanner.nextInt());
        scanner.nextLine();

        // count number of items (quantity) added to list
        totalQtyOfAllItems++;

        // budget calculation
        if (shoppingBudget < 0 || shoppingBudget < itemToPurchaseTimesQty) {
            shoppingBudget = 0;
            System.out.println("insufficient budget");
        } else {
            if (shoppingBudget > 0 && shoppingBudget < 100) {
                System.out.println("- - - - - - - - - - - -");
                System.out.println("⚠️FYI: your budget is under $100");
                System.out.println("- - - - - - - - - - - -");
            } else if (shoppingBudget > 100 && shoppingBudget < 200) {
                System.out.println("- - - - - - - - - - - -");
                System.out.println("⚠️FYI: your budget is under $200");
                System.out.println("- - - - - - - - - - - -");
            } else if (shoppingBudget > 200 && shoppingBudget < 300) {
                System.out.println("- - - - - - - - - - - -");
                System.out.println("⚠️FYI: your budget is under $300");
                System.out.println("- - - - - - - - - - - -");
            }
            shoppingBudget = shoppingBudget - itemToPurchaseTimesQty;
        }

        // count number of items
        itemCount++;

        // item category (y / n)??
        System.out.print("add this item to category? y/n: ");
        addToCategory = scanner.nextLine();

        while (addToCategory.isEmpty()) {
            System.out.println("you must add a name to category");
        }

        if (addToCategory.equalsIgnoreCase("y") || addToCategory.equalsIgnoreCase("yes")) {
            items.add(new Item(item.getItemName(), item.getItemPrice(), item.getItemQuantity()));
            System.out.println(item.getItemName() + " added");
            System.out.println("- - - - - - - - - - - -");

            // add item to category
            System.out.print("enter item category: ");
            item.setItemCategory(scanner.nextLine());
            categories.add(new Item(item.getItemCategory()));
            // adding item to category unless user enter n / no
            System.out.println(item.getItemName() + " added to " + item.getItemCategory() + "'s list");
        } else if (addToCategory.equalsIgnoreCase("n") || addToCategory.equalsIgnoreCase("no")) {
            items.add(new Item(item.getItemName(), item.getItemPrice(), item.getItemQuantity()));
        } else {
            System.out.println("invalid entry");
        }
        viewAllItems();
    }

    @Override
    public void removeItem() {
    }

    @Override
    public void updateItem() {

    }

    void shopNBud() {
        System.out.println("---------- ⭐️----------");
        System.out.println(" S H O P ' N  🤖 B U D ");
        System.out.println("---------- 🛒----------");
    }

    public void viewAllItems() {
        for (Item item : items) {
            item.displayItem();
        }
        budgetSpend();
        item.displayCategory();
        System.out.println("total qty: " + totalQtyOfAllItems);
    }

    public void viewAllCategories() {
        for (Item item : categories) {
            item.displayCategory();
        }
    }

    void budgetSpend() {
        System.out.println("🤑budget amount: $" + initialBudget);
        System.out.println("☕️budget spent: -$" + itemToPurchaseTimesQty);
        System.out.println("🚀budget remaining: +$" + shoppingBudget);
    }
}
