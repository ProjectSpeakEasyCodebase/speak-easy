package Messages.Types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PublicKeyShareTest {

    private PublicKeyShare publicKeyShare;

    @BeforeEach
    void setUp() {
        publicKeyShare = new PublicKeyShare("", null, null);
    }
    @Test
    void serialize() {
    }

    @Test
    void deserialize() {
    }

    @Test
    void getType() {
        assertEquals(Type.PUBLIC_KEY, publicKeyShare.getType());
    }
}