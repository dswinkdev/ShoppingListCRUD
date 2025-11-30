import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingListManager implements Shopping {

    String addToCategory = "";
    double shoppingBudget = 0;
    int choice = 0;

    // decimal format
    DecimalFormat df = new DecimalFormat("##.##");

    Item item = new Item();

    private ArrayList<Item> items;
    private ArrayList<Item> categories;

    Scanner scanner = new Scanner(System.in);

    public ShoppingListManager(){
        this.items = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    void menu(){
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

        // user menu
        menu();

        boolean running = true;

        while (running) {
            switch (choice) {
                case 1 -> addItem();
                case 2 -> updateItem();
                case 3 -> removeItem();
                case 4 -> viewAllItems();
                //case 5 -> viewAllCategories();
                case 9 -> {
                    System.out.println("exiting program");
                    running = false;
                }
                default -> System.out.println("invalid choice");
            }
        }
    }

    @Override
    public void addItem() {
        // item name
        System.out.print("enter item name: ");
        item.setItemName(scanner.nextLine());
        scanner.nextLine();

        // item price
        System.out.print("enter item price: $");
        item.setItemPrice(scanner.nextDouble());
        scanner.nextLine();

        // item qty
        System.out.print("enter item quantity: ");
        item.setItemQuantity(scanner.nextInt());
        scanner.nextLine();

        // budget calculation
        if (shoppingBudget < 0 || shoppingBudget < (item.getItemPrice() * item.getItemQuantity())){
            shoppingBudget = 0;
            System.out.println("insufficient budget");
        } else {
            shoppingBudget = shoppingBudget - (item.getItemPrice() * item.getItemQuantity());
        }
        // item category (y / n)??
        System.out.print("add this item to category? y/n: ");
        addToCategory = scanner.nextLine();

            while (addToCategory.isEmpty()) {
                System.out.println("you must add a name to category");
            }

            if (addToCategory.equalsIgnoreCase("y") || addToCategory.equalsIgnoreCase("yes")) {
                items.add(new Item(item.getItemName(), item.getItemPrice(), item.getItemQuantity()));
                System.out.println(item.getItemName() + " has been added");

                // add item to category
                System.out.print("enter item category: ");
                item.setItemCategory(scanner.nextLine());
                categories.add(new Item(item.getItemCategory()));
                System.out.println(item.getItemName() + " has been added to your " + item.getItemCategory() + "'s list");
            } else if (addToCategory.equalsIgnoreCase("n") || addToCategory.equalsIgnoreCase("no")) {
                items.add(new Item(item.getItemName(), item.getItemPrice(), item.getItemQuantity()));
                System.out.println(item.getItemName() + " has been added to your " + item.getItemCategory() + "'s list");
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

    void shopNBud(){
        System.out.println("---------- ⭐️----------");
        System.out.println(" S H O P ' N  🤖 B U D  ");
        System.out.println("---------- 🛒----------");
    }

    public void viewAllItems() {
        for (Item item : items){
            item.displayItem();
        }
        System.out.println("budget -> +$" + shoppingBudget + " | -$" +
                (df.format(item.getItemPrice() * item.getItemQuantity())));
        item.displayCategory();
    }
}
