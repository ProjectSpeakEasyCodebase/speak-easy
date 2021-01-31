package Users;

import Channels.Channel;

public class ServerUser extends ClientUser {
    private final String connectStr;

    ServerUser(String username, Channel channel, String connectStr) {
        super(username, channel);

        this.connectStr = connectStr;
    }

    public String getConnectStr() {
        return connectStr;
    }
}
