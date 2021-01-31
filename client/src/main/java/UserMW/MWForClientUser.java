package UserMW;

import Channels.*;
import Client.Client;
import Messages.*;
import Messages.Types.*;
import Users.User;

import java.util.*;

public class MWForClientUser implements UserMW {

    private final Client client;
    private final KeyChain keyChain;
    User me;

    MWForClientUser(Client client) {
        keyChain = new KeyChain();
        this.client = client;
    }

    @Override
    public void makeUser(String invite) {
        String username = getUserName();
        Channel channel = new UserChannel(me, me, client, me.getId() + "-dm", me.getName() + "-dm");
        keyChain.genChannel(channel, "");
        me = new Users.ClientUser(username, channel);
        String id = genUserKey();
        me.setId(id);
        client.joinServer(invite);
    }

    private String getUserName() {
        return "";
    }

    private String genUserKey(){
        int hash = keyChain.genUser(me, "");
        PrivateChannel publicKeys = new PrivateChannel(me, client, "publicKeys", "publicKeys");
        keyChain.genChannel(publicKeys, "publicKeys");
        client.produce("publicKeys", keyChain.shareMyPublicKey(me, publicKeys));
        return me.getName() + hash;
    }

    @Override
    public void loadOpenChannels() {
        PrivateChannel cmdChannel = new PrivateChannel(me, client, "cmd", "cmd");
        keyChain.genChannel(cmdChannel, "cmd");
        cmdChannel.consume(0, keyChain);
    }

    @Override
    public Channel makeNewPrivateChannel(String name) {
        return new PrivateChannel(me, client, name+me.hashCode(), name);
    }

    @Override
    public Channel makeNewOpenChannel(String name) {
        return new OpenChannel(client, name + me.hashCode(), name);
    }

    @Override
    public void sharePrivateChannel(User invitee, String channelId) {
        DM(Collections.singletonList(invitee), new Invitation("", me.getChannel(channelId), keyChain.shareChannel(me.getChannel(channelId), invitee)));
    }

    @Override
    public void react(Message<Replyable> msg, Reaction messageContent) {
        Message<Reaction> message = new Message<>(me, messageContent, msg.getChannel());
        try {
            msg.getChannel().publish(message, null, keyChain);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reply(Message<Replyable> msg, Reply messageContent) {
        Message<Reply> message = new Message<>(me, messageContent, msg.getChannel());
        try {
            msg.getChannel().publish(message, null, keyChain);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void post(Channel channel, Text messageContent) {
        Message<Text> message = new Message<>(me, messageContent, channel);
        try {
            channel.publish(message, null, keyChain);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DM(List<User> userList, MessageContent messageContent) {
        for (User dm: userList) {
            try {
                me.getContact(dm).publish(new Message<>(me, userList, messageContent, me.getContact(dm)), dm, keyChain);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Collection<Message<? extends MessageContent>> pollChannel(Channel channel, long lastOffset) {
        return channel.consume(lastOffset, keyChain);
    }
}
