package Users;

import Channels.Channel;

public interface User {
    String getName();
    String getId();
    void setId(String string);
    void addChannel(Channel channel);
    void addContact(User user);
    Channel getChannel(String channelId);
    Channel getContact(User dm);
    Channel getPrivateChannel();
}
