package Messages.Types;

import Messages.Message;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;

public class Reply extends MessageContent implements Replyable{

    private final Message<? extends Replyable> message;

    Reply(String msg, Message<? extends Replyable> replyTo) {
        super(msg);
        message = replyTo;
    }

    public Message<? extends Replyable> getSourceMessage(){
        return message;
    }

    @Override
    public Type getType(){
        return Type.REPLY;
    }

    @Override
    String serialize() {
        return JsonStream.serialize(this);
    }

    @Override
    Reply deserialize(String msg) {
        return JsonIterator.deserialize(msg, Reply.class);
    }
}
