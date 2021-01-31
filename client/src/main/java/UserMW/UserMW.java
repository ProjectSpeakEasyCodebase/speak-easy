package UserMW;

import Channels.Channel;
import Channels.PrivateChannel;
import Channels.UserChannel;
import Messages.*;
import Messages.Types.*;
import Users.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface UserMW {
    void makeUser(String invite);
    void loadOpenChannels();
    Channel makeNewPrivateChannel(String name);
    Channel makeNewOpenChannel(String name);
    void sharePrivateChannel(User invitee, String channelName);
    void react(Message<Replyable> msg, Reaction messageContent);
    void reply(Message<Replyable> msg, Reply messageContent);
    void post(Channel channel, Text messageContent);
    void DM(List<User> userList, MessageContent messageContent);
    Collection<Message<? extends MessageContent>> pollChannel(Channel channel, long lastOffset);
}
