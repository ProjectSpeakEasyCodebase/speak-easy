package Messages.Events;

import Channels.Channel;

public class ChannelEncrypted implements MessageEvent{
    private final Channel channel;
    private boolean encrypt;

    public ChannelEncrypted(Channel channel){
        this.channel = channel;
        this.encrypt = true;
    }

    @Override
    public void undone() {
        encrypt = false;
    }
}
