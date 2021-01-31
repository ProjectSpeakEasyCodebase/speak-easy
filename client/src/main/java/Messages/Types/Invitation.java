package Messages.Types;

import Channels.Channel;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;

public class Invitation extends MessageContent implements MessageType{

    private final Channel channel;
    private final String key;

    public Invitation(String msg, Channel channel, String key) {
        super(msg);
        this.channel = channel;
        this.key = key;
    }

    @Override
    public Type getType() {
        return Type.INVITATION;
    }

    public Channel getChannel(){
        return channel;
    }

    public String getKey(){
        return key;
    }

    @Override
    String serialize() {
        return JsonStream.serialize(this);
    }

    @Override
    Invitation deserialize(String msg) {
        return JsonIterator.deserialize(msg, Invitation.class);
    }
}
