package Messages;

import Channels.Channel;
import Messages.Events.MessageEvent;
import Messages.Types.MessageType;
import Users.User;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Message<T extends MessageType>{

    private final User sender;
    private final List<User> receiver;
    private final Channel channel;
    private T messageContent;
    private int signed;
    private User privateEncrypted;
    private boolean channelEncrypted;
    private final List<MessageEvent> messageEventList;
    private final String msgId;
    public long timeSent;

    public Message(User sender, List<User> receiver, T messageContent, Channel channel) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageContent = messageContent;
        privateEncrypted = null;
        this.channel = channel;
        signed = 0;
        channelEncrypted = false;
        messageEventList = new ArrayList<>();
        msgId = messageContent.toString(); //hash
    }

    Message(User sender, User receiver, T messageContent, Channel channel) {
        this(sender, Collections.singletonList(receiver), messageContent, channel);
    }

    public Message(User sender, T messageContent, Channel channel) {
        this(sender, (List<User>) null, messageContent, channel);
    }

    public boolean isSigned() {
        return signed > 0;
    }

    public boolean isVerified() {
        return signed == 2;
    }

    public void setSigned() {
        this.signed = 1;
    }

    public void setVerified() {
        this.signed = 1;
    }

    boolean isEncrypted() {
        return privateEncrypted == null;
    }

    boolean isChannelEncrypted() {
        return channelEncrypted;
    }

    public void setChannelEncrypted(boolean bool) {
        channelEncrypted = bool;
    }

    public User getSender() {
        return sender;
    }

    public List<User> getReceivers() {
        return receiver;
    }

    public T getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(T messageContent) {
        this.messageContent = messageContent;
    }

    public User getPrivateEncrypted() {
        return privateEncrypted;
    }

    public void setPrivateEncrypted(User privateEncrypted) {
        Objects.requireNonNull(privateEncrypted);
        this.privateEncrypted = privateEncrypted;
    }

    public Channel getChannel() {
        return channel;
    }

    public void log(MessageEvent messageEvent) {
        messageEventList.add(messageEvent);
    }

    public void setTimeSent(){
        timeSent = System.currentTimeMillis();
    }

    @Override
    public String toString() { //to json
        return "Message{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", channel=" + channel +
                ", messageContent=" + messageContent +
                ", signed=" + signed +
                ", privateEncrypted=" + privateEncrypted +
                ", channelEncrypted=" + channelEncrypted +
                ", messageEventList=" + messageEventList +
                ", msgId='" + msgId + '\'' +
                '}';
    }
}
