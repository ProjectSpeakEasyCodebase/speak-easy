package Messages.Types;

import Messages.Message;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;

public class Reaction extends MessageContent {

    private final Message<? extends Replyable> message;

    Reaction(String msg, Message<? extends Replyable> reactTo) {
        super(msg);
        message = reactTo;
    }

    public Message<? extends Replyable> getSourceMessage(){
        return message;
    }

    @Override
    public Type getType(){
        return Type.REACTION;
    }

    @Override
    String serialize() {
        return JsonStream.serialize(this);
    }

    @Override
    Reaction deserialize(String msg) {
        return JsonIterator.deserialize(msg, Reaction.class);
    }
}
