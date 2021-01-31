package Messages.Types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MessageContent implements MessageType {
    private String msg;
    public final String nonce;

    MessageContent(String msg){
        this.msg = msg;
        nonce = "SpeakEasy";
    }

    public String getContent() {
        return msg;
    }

    void escape() {
        Pattern pattern = Pattern.compile(":");
        // get a matcher object
        Matcher matcher = pattern.matcher(msg);
        msg = matcher.replaceAll("--:");
    }

    void unescape(){
        Pattern pattern = Pattern.compile("--:");
        // get a matcher object
        Matcher matcher = pattern.matcher(msg);
        msg = matcher.replaceAll(":");
    }

    public void setContent(String msg){
        this.msg = msg;
    }

    abstract String serialize();
    abstract MessageContent deserialize(String msg);
}
