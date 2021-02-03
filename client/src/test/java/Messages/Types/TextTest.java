package Messages.Types;

import Messages.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTest {

    private Text text;

    @Test
    void serializeAndDeserialize() {
    }

    @Test
    void deserialize() {
    }

    @Test
    void getType() {
        assertEquals(Type.TEXT, text.getType());
    }

    @BeforeEach
    void setUp() {
        text = new Text("testing");
    }
}