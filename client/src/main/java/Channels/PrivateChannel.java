package Channels;

import Client.Client;
import Messages.Message;
import Messages.Types.Invitation;
import Messages.Types.MessageContent;
import Messages.Types.PublicKeyShare;
import Messages.Types.Type;
import Users.User;

import java.util.ArrayList;
import java.util.Collection;

public class PrivateChannel implements Channel{
    private final User me;
    private final Client client;
    private final String channelId;
    private final String name;

    public PrivateChannel(User me, Client client, String channelId, String name){
        this.me = me;
        this.client = client;
        this.channelId = channelId;
        this.name = name;
    }

    @Override
    public void publish(Message<? extends MessageContent> message, User receiver, KeyChain keyChain) {
        message.setTimeSent();
        keyChain.sign(message);
        keyChain.encrypt(this, message);
        client.produce(channelId, message);
    }

    @Override
    public Collection<Message<? extends MessageContent>> consume(long offset, KeyChain keyChain) {
        Collection<Message<? extends MessageContent>> received = new ArrayList<>();
        Collection<Message<? extends MessageContent>> messages = client.consumer(channelId, offset);
        for (Message<? extends MessageContent> message: messages) {
            keyChain.decrypt(this, message);
            keyChain.verify(message.getSender(), message);
            if (!message.getMessageContent().nonce.equals("SpeakEasy")){
                continue;
            }
            if (message.getMessageContent().getType() == Type.INVITATION){
                Invitation invitation = (Invitation) message.getMessageContent();
                keyChain.add(invitation.getChannel(), invitation.getKey(), message.getSender());
                me.addChannel(invitation.getChannel());
            } else if(message.getMessageContent().getType() == Type.PUBLIC_KEY) {
                PublicKeyShare publicKeyShare = (PublicKeyShare) message.getMessageContent();
                keyChain.add(publicKeyShare.getOwner(), publicKeyShare.getKey());
                me.addContact(publicKeyShare.getOwner());
            } else {
                received.add(message);
            }
        }
        return received;
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
