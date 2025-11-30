import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingListManager implements Shopping {

    String addToCategory = "";
    double shoppingBudget = 0;

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

    @Override
    public void addItem() {
        // users budget
        System.out.print("enter budget amount: $");
        shoppingBudget = scanner.nextDouble();
        scanner.nextLine();

        // item name
        System.out.print("enter item name: ");
        item.setItemName(scanner.nextLine());

        // item price
        System.out.print("enter item price: $");
        item.setItemPrice(scanner.nextDouble());

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
                System.out.println("- - - - - - - - - - - -");
                System.out.println(item.getItemName() + " has been added");
                System.out.println("- - - - - - - - - - - -");

                // add item to category
                System.out.print("enter item category: ");
                item.setItemCategory(scanner.nextLine());
                categories.add(new Item(item.getItemCategory()));
                System.out.println("- - - - - - - - - - - -");
                System.out.println(item.getItemName() + " has been added to your " + item.getItemCategory() + "'s list");
                System.out.println("- - - - - - - - - - - -");
            } else if (addToCategory.equalsIgnoreCase("n") || addToCategory.equalsIgnoreCase("no")) {
                items.add(new Item(item.getItemName(), item.getItemPrice(), item.getItemQuantity()));
            } else {
                System.out.println("invalid entry");
            }
            viewAllItems();
    }

    @Override
    public void removeItem(int index, Item item) {
        if (index < 0 || index > items.size()){
            System.out.println("❌ no item found");
        }

        for (Item i : items){
            if (i == items.get(index)){
                items.remove(item);
                System.out.println(item + " has been removed");
            }
        }
    }

    @Override
    public void updateItem(int index, Item newItem) {
        if (index < 0 || index > items.size()) {
            System.out.println("❌ no item found to update");
        }

        for (int i = 0; i < items.size(); i++) {
             items.set(index,newItem);
            System.out.println("✅ " + newItem + " has been updated.");
        }
    }

    @Override
    public void viewAllItems() {
        for (Item item : items){
            item.displayItem();
        }
        System.out.println("budget -> +$" + shoppingBudget + " | -$" +
                (df.format(item.getItemPrice() * item.getItemQuantity())));
        item.displayCategory();
    }
}
