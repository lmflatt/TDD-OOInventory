import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by lee on 9/30/16.
 */
public class MainTest {
    @Test
    public void testUserSearch() {
        User user = new User("Bob", "password");
        Main.users.add(user);

        assertTrue(user.getName().equals("Bob"));

        user = new User("Charlie", "other");
        Main.users.add(user);

        assertTrue(Main.users.size() == 2);

        user = User.searchUser("Bob");

        assertTrue(user.getName().equals("Bob"));
    }

        @Test
        public void testItemSearch() {
            User user = new User("Bob", "password");
            ArrayList<Item> items = new ArrayList<>();
            items.add(new Food("Coconut", 5));
            items.add(new Food("Banana", 10));
            items.add(new Food("Apple", 3));
            items.add(new Food("Beer", 2));
            user.setInventory(items);

            Item item = Item.searchItem(user.getInventory(), "Coconut");

            assertTrue(item.getCategory().equals("Food"));
            assertTrue(item.getName().equals("Coconut"));
            assertTrue(item.getQuantity() == 5);
    }
}
