package Messages.Types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReplyTest {

    private Reply reply;

    @Test
    void getType() {
        assertEquals(Type.REPLY, reply.getType());
    }

    @Test
    void serialize() {
    }

    @Test
    void deserialize() {
    }

    @BeforeEach
    void setUp() {
        reply = new Reply("", null);
    }
}