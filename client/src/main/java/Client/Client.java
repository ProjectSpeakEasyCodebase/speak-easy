package Client;

import Messages.Message;
import Messages.Types.MessageContent;
import Messages.Types.MessageType;

import java.util.Collection;

public interface Client {
    void produce(String destination, Message<? extends MessageContent> message);
    Collection<Message<? extends MessageContent>> consumer(String source, long lastSeenOffset);
    void joinServer(String invite);
}
