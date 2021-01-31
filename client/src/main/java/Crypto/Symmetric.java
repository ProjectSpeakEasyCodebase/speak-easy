package Crypto;

public interface Symmetric {
    String encrypt(SymmetricKey secret, String msg);
    String decrypt(SymmetricKey secret, String msg);
    SymmetricKey genKey(String string);
}
