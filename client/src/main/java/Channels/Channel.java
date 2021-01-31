package Channels;

import Messages.Message;
import Messages.Types.MessageContent;
import Users.User;

import java.util.Collection;

public interface Channel {
    void publish(Message<? extends MessageContent> message, User receiver, KeyChain keyChain) throws IllegalAccessException;
    Collection<Message<? extends MessageContent>> consume(long offset, KeyChain keyChain);
    String getId();
    String getName();
}
