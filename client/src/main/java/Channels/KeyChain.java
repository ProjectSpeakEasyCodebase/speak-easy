package Channels;

import Messages.Events.ChannelEncrypted;
import Messages.Events.Encrypted;
import Messages.Events.Signed;
import Messages.Message;
import Messages.Types.MessageContent;
import Messages.Types.PublicKeyShare;
import Crypto.*;
import Users.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyChain {
    private final Map<User, PublicKey> userKeys;
    private final Map<Channel, SymmetricKey> channelKeys;
    private Asymmetric asymmetric;
    private Symmetric symmetric;
    private PrivateKey privateKey;

    public KeyChain(){
        userKeys = new HashMap<>();
        channelKeys = new HashMap<>();
    }

    public int genUser(User user, String randomInput){
        List<Key> keys = asymmetric.genKey(user.getName() + randomInput);
        privateKey = (PrivateKey) keys.get(0);
        userKeys.put(user, (PublicKey) keys.get(1));
        return keys.get(1).hashCode();
    }

    public int genChannel(Channel channel, String randomInput){
        SymmetricKey key = symmetric.genKey(channel.getName() + randomInput);
        channelKeys.put(channel, key);
        return key.hashCode();
    }

    void encrypt(Channel channel, Message<? extends MessageContent> msg) {
        msg.setChannelEncrypted(true);
        msg.log(new ChannelEncrypted(channel));
        msg.getMessageContent().setContent(symmetric.encrypt(channelKeys.get(channel), msg.getMessageContent().getContent()));
    }

    void decrypt(Channel channel, Message<? extends MessageContent> msg) {
        msg.setChannelEncrypted(false);
        msg.getMessageContent().setContent(symmetric.decrypt(channelKeys.get(channel), msg.getMessageContent().getContent()));
    }

    void sign(Message<? extends MessageContent> msg) {
        msg.setSigned();
        msg.log(new Signed(msg.getSender()));
        msg.getMessageContent().setContent(asymmetric.sign(privateKey, msg.getMessageContent().getContent()));
    }

    void encryptTo(User recipient, Message<? extends MessageContent> msg) {
        msg.setPrivateEncrypted(recipient);
        msg.log(new Encrypted(recipient));
        msg.getMessageContent().setContent(asymmetric.encrypt(userKeys.get(recipient), msg.getMessageContent().getContent()));
    }

    void verify(User sender, Message<? extends MessageContent> msg) {
        msg.setVerified();
        msg.getMessageContent().setContent(asymmetric.verify(userKeys.get(sender), msg.getMessageContent().getContent()));
    }

    void decrypt(Message<? extends MessageContent> msg) {
        msg.getMessageContent().setContent(asymmetric.decrypt(privateKey, msg.getMessageContent().getContent()));
    }

    void add(User user, PublicKey publicKey) {
        userKeys.put(user, publicKey);
    }

    void add(Channel channel, String symmetricKey, User sender) {
        channelKeys.put(channel, symmetric.genKey(asymmetric.verify(userKeys.get(sender), asymmetric.decrypt(privateKey, symmetricKey))));
    }

    public Message<PublicKeyShare> shareMyPublicKey(User me, Channel channel) {
        PublicKeyShare publicKeyShare = new PublicKeyShare("", me, userKeys.get(me));
        return new Message<>(me, publicKeyShare, channel);
    }

    public String shareChannel(Channel channel, User invitee) {
        return asymmetric.shareSymetricKey(privateKey, userKeys.get(invitee), channelKeys.get(channel));
    }
}
