package Messages.Types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReactionTest {

    private Reaction reaction;

    @BeforeEach
    void setUp() {
        reaction = new Reaction("test", null);
    }

    @Test
    void getContent() {
        assertEquals("test", reaction.getContent());
    }

    @Test
    void getSourceMessage() {
        assertNull(reaction.getSourceMessage());
    }

    @Test
    void getType() {
        assertEquals(Type.REACTION, reaction.getType());
    }

    @Test
    void serializeAndDeserialize() {
    }

}