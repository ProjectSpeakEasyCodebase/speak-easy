package Messages.Events;

import Users.User;

public class Signed implements MessageEvent {
    private final User user;
    private boolean signed;

    public Signed(User user) {
        this.user = user;
        this.signed = true;
    }

    @Override
    public void undone() {
        signed = false;
    }
}
