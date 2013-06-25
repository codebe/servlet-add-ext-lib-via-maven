package main.java.com.kodelokus;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Encrypt {
    private static Signature signature;
    private static KeyPair keyPair;

    public Encrypt() {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

        try {
            keyGen.initialize(ecSpec, new SecureRandom());
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        Encrypt.keyPair = keyGen.generateKeyPair();
        try {
            Encrypt.signature = Signature.getInstance("ECDSA", "BC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        // generate a signature
        try {
            signature.initSign(keyPair.getPrivate(), Utils.createFixedRandom());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(byte[] message) {
        try {
            Encrypt.signature.update(message);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        try {
            return Encrypt.signature.sign().toString();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkSignature(byte[] message, byte[] signature) {
        try {
            Encrypt.signature.initVerify(Encrypt.keyPair.getPublic());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            Encrypt.signature.update(message);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        try {
            return Encrypt.signature.verify(signature);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }
}
