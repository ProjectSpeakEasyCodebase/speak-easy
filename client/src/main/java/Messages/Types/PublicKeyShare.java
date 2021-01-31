package Messages.Types;

import Crypto.PublicKey;
import Users.User;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;


public class PublicKeyShare extends MessageContent implements MessageType{
    private final User owner;
    private final PublicKey key;

    public PublicKeyShare(String msg, User owner, PublicKey key) {
        super(msg);
        this.owner = owner;
        this.key = key;
    }

    @Override
    public Type getType() {
        return Type.PUBLIC_KEY;
    }

    public PublicKey getKey() {
        return key;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    String serialize() {
        return JsonStream.serialize(this);
    }

    @Override
    PublicKeyShare deserialize(String msg) {
        return JsonIterator.deserialize(msg, PublicKeyShare.class);
    }
}
