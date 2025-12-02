import java.text.DecimalFormat;

public class Item {

    private String itemName;
    private String itemCategory;
    private double itemPrice;
    private int itemQuantity;

    public Item() {}

    public Item(String itemName, double itemPrice, int itemQuantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    // decimal format
    DecimalFormat df = new DecimalFormat("##.##");

    public Item(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    void displayItem() {
        System.out.println("- - - - - - - - - - - -");
        System.out.println("⚡️item -> " + itemName);
        System.out.println("🏷️price -> $" + df.format(itemPrice));
        System.out.println("🛒qty -> " + itemQuantity);
        System.out.println("💵est. total -> $" + df.format((itemPrice) * itemQuantity));
    }

    void displayCategory() {
        System.out.println(((itemCategory == null) ? "🧺category -> none" : "🧺️category -> " + itemCategory));
        System.out.println("- - - - - - - - - - - -");
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
