package Messages.Events;

import Users.User;

public class Encrypted implements MessageEvent {
    private final User user;
    private boolean encrypted;

    public Encrypted(User user){
        this.user = user;
        this.encrypted = true;
    }

    @Override
    public void undone() {
        encrypted = false;
    }
}
