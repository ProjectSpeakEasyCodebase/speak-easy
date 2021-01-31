package Messages.Types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReactionTest {

    private Reaction reaction;

    @BeforeEach
    void setUp() {
        reaction = new Reaction("", null);
    }

    @Test
    void getContent() {

    }

    @Test
    void escape() {
    }

    @Test
    void unescape() {
    }

    @Test
    void setContent() {
    }

    @Test
    void getSourceMessage() {
    }

    @Test
    void getType() {
        assertEquals(Type.REACTION, reaction.getType());
    }

    @Test
    void serialize() {
    }

    @Test
    void deserialize() {
    }

    @Test
    void testGetType() {
    }

    @Test
    void testSerialize() {
    }

    @Test
    void testDeserialize() {
    }
}