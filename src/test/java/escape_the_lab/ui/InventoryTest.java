package escape_the_lab.ui;
import escape_the_lab.model.Item;

import java.util.ArrayList;
import java.util.List;

public class InventoryTest {
    private List<Item> items;

    public InventoryTest() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void resetInventory() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean hasItem(Item look) {
        for (Item item : items) {
            if (item.equals(look)) {
                return true;
            }
        }
        return false;
    }
}
