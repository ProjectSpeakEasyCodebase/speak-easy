package Users;

import Channels.Channel;

import java.util.HashMap;
import java.util.Map;

public class ClientUser implements User {

    private final String username;
    private String id;
    private final Map<String, User> contacts;
    private final Map<String, Channel> channels;
    private final Channel privateChannel;

    public ClientUser(String username, Channel channel) {
        this.username = username;
        this.contacts = new HashMap<>();
        this.channels = new HashMap<>();
        privateChannel = channel;
    }

    @Override
    public String getName() {
        return username;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void addChannel(Channel channel) {
        channels.put(channel.getId(), channel);
    }

    @Override
    public void addContact(User user) {
        contacts.put(user.getId(), user);
    }

    public Channel getChannel(String channelId) {
        return channels.get(channelId);
    }

    @Override
    public Channel getContact(User dm) {
        return contacts.get(dm.getId()).getPrivateChannel();
    }

    @Override
    public Channel getPrivateChannel() {
        return privateChannel;
    }

    @Override
    public String getId() {
        return id;
    }
}
