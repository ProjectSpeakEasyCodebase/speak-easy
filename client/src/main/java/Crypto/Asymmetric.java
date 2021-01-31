package Crypto;

import java.util.List;

public interface Asymmetric {
    String encrypt(PublicKey secret, String msg);
    String decrypt(PrivateKey secret, String msg);
    String sign(PrivateKey secret, String msg);
    String verify(PublicKey secret, String msg);
    List<Key> genKey(String randomInput);
    String shareSymetricKey(PrivateKey privateKey, PublicKey publicKey, SymmetricKey key);
}
