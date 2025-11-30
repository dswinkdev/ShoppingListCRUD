public interface Item {

    void addItem(Item item);
    void removeItem(int index, Item item);
    void updateItem(int index, Item newItem);
    void viewAllItems();
}
