package Messages.Types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTest {

    private Text text;

    @Test
    void serialize() {
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