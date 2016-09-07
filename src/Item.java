import java.util.ArrayList;

/**
 * Created by lee on 9/6/16.
 */
public class Item {

    protected String name = "";
    protected Integer quantity;
    protected String category;

    public Item() {
    }

    public Item(String name, int quantity, String category) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    protected void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static Item changeQuantity(Item item) {
        Integer newQuantity = Validation.validatePositiveInt();
        item.setQuantity(newQuantity);
        return item;
    }

    public static Item searchItem(ArrayList<Item> inventory) {
        String itemName = Validation.validateString();
        for (Item item : inventory) {
            if (itemName.equalsIgnoreCase(item.getName())){
                return item;
            }
        }
        return null;
    }

    public static String retrieveItemName(ArrayList<Item> inventory) {
        String itemName = Validation.validateString();
        for (Item item : inventory) {
            if (itemName.equalsIgnoreCase(item.getName())){
                itemName = "";
            }
        }
        return itemName;
    }
}
