package Messages.Types;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;

public class Text extends MessageContent  implements Replyable {

    public Text(String msg) {
        super(msg);
    }

    @Override
    String serialize() {
        return JsonStream.serialize(this);
    }

    @Override
    Text deserialize(String msg) {
        return JsonIterator.deserialize(msg, Text.class);
    }

    @Override
    public Type getType(){
        return Type.TEXT;
    }
}
