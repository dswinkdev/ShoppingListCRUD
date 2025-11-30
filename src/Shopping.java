public interface Shopping {

    void addItem(ShoppingList item);
    void removeItem(int index, ShoppingList item);
    void updateItem(int index, ShoppingList newItem);
    void viewAllItems();
}
