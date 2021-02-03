package Channels;

import Client.Client;
import Messages.Message;
import Messages.Types.MessageContent;
import Messages.Types.Type;
import Users.User;

import java.util.Collection;

public class OpenChannel implements  Channel {

    private final Client client;
    private final String channelId;
    private final String name;

    public OpenChannel(Client client, String channelId, String name) {
        this.client = client;
        this.channelId = channelId;
        this.name = name;
    }

    @Override
    public void publish(Message<? extends MessageContent> message, User receiver, KeyChain keyChain) throws IllegalAccessException {
        if (message.getMessageContent().getType() == Type.INVITATION) {
            throw new IllegalAccessException("Cannot Invite to private channel over an open Channel");
        }
        message.setTimeSent();
        client.produce(channelId, message);
    }

    @Override
    public Collection<Message<? extends MessageContent>> consume(long offset, KeyChain chain) {
        return client.consumer(channelId, offset);
    }

    @Override
    public String getId() {
        return channelId;
    }

    @Override
    public String getName() {
        return name;
    }
}
