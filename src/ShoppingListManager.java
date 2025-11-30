import java.util.ArrayList;

public class ShoppingListManager implements Shopping {

    private ArrayList<ShoppingList> items;

    public ShoppingListManager(){
        this.items = new ArrayList<>();
    }

    public ArrayList<ShoppingList> getItems() {
        return items;
    }

    public void setItems(ArrayList<ShoppingList> items) {
        this.items = items;
    }

    @Override
    public void addItem(ShoppingList item) {
        items.add(item);
    }

    @Override
    public void removeItem(int index, ShoppingList item) {
        if (index < 0 || index > items.size()){
            System.out.println("❌ no item found");
        }

        for (ShoppingList i : items){
            if (i == items.get(index)){
                items.remove(item);
                System.out.println(item + " has been removed");
            }
        }
    }

    @Override
    public void updateItem(int index, ShoppingList newItem) {
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
        for (ShoppingList item : items){
            System.out.println(item);
        }
    }

    void displayInfo(){
        System.out.println();
    }
}
