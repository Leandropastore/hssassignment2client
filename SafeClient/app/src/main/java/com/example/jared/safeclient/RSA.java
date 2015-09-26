package com.example.jared.safeclient;

/**
 * Created by Jared on 25/09/2015.
 */
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Leandro
 */
public class RSA {


    private PublicKey puKey;
    private PrivateKey prKey;

    public RSA(){

        KeyPair keys = null;
        try {
            keys = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        puKey = keys.getPublic();
        prKey = keys.getPrivate();

    }

    public RSA(byte[] key,String command){

        if(command.equals("public")){

            X509EncodedKeySpec spec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = null;
            try {
                keyFactory = KeyFactory.getInstance("RSA");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                puKey = keyFactory.generatePublic(spec);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else if(command.equals("private")){

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory fact = null;
            try {
                fact = KeyFactory.getInstance("RSA");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                prKey = fact.generatePrivate(keySpec);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public byte[] encryptWithPublic(String string){

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, puKey);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] encrypted = null;
        try {
            encrypted = cipher.doFinal(string.getBytes());
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }

        return encrypted;

    }

    public byte[] encryptWithPrivate(String string){

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, prKey);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] encrypted = null;
        try {
            encrypted = cipher.doFinal(string.getBytes());
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }

        return encrypted;

    }

    public String decryptWithPrivate(byte[] bytes){

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, prKey);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] decrypted = null;
        try {
            decrypted = cipher.doFinal(bytes);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(decrypted);

    }

    public String decryptWithPublic(byte[] bytes){

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, puKey);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] decrypted = null;
        try {
            decrypted = cipher.doFinal(bytes);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(decrypted);

    }

    public byte[] getPublic(){

        return puKey.getEncoded();

    }

    public byte[] getPrivate(){

        return prKey.getEncoded();

    }
}
