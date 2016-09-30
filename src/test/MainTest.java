import org.junit.Test;
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
}
