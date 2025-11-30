public interface Shopping {

    void addItem();
    void removeItem(int index, Item item);
    void updateItem(int index, Item newItem);
    void viewAllItems();
}
