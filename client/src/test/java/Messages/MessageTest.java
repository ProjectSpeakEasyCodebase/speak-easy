package Messages;

import Messages.Types.Text;
import Users.ClientUser;
import org.junit.jupiter.api.BeforeEach;

class MessageTest {

    private ClientUser user;
    private Message<Text> message;

    @BeforeEach
    void setUp() {
        user = new ClientUser("name", null);
        message = new Message<>(user, new Text("test"),  null);
    }

}