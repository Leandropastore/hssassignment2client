package com.example.jared.safeclient;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static final String CIPHER = "AES";
    private Key key;
    private byte[] keyBytes;

    public AES(){
        
        SecureRandom r = new SecureRandom();
        keyBytes = new byte[16];
        r.nextBytes(keyBytes);
        key = new SecretKeySpec(keyBytes, CIPHER);
    
    }
    
    public AES(byte[] keyBytes) throws Exception{
        if(keyBytes.length==16){
            key = new SecretKeySpec(keyBytes, CIPHER);
            this.keyBytes = keyBytes;
        }
        else{
            Exception e = new Exception("Wrong key size!");
            throw e;
        }
    }
    
    public AES(String keyString) throws Exception{
        
        keyString = ByteManager.formatString(keyString);
        if(ByteManager.stringByteSize(keyString)==16){
            key = new SecretKeySpec(ByteManager.createBytes(keyString), CIPHER);
            this.keyBytes = ByteManager.createBytes(keyString);
        }
        else{
            Exception e = new Exception("Wrong key size!");
            throw e;
        }
    }
    
    public byte[] getKey(){
    
        return keyBytes;
        
    }
    
    public byte[] encrypt(String valueToEncrypt) throws Exception {
        
        valueToEncrypt = ByteManager.formatString(valueToEncrypt);
        Cipher c = Cipher.getInstance(CIPHER);
        c.init(Cipher.ENCRYPT_MODE, key);

        byte[] encrValue = c.doFinal(ByteManager.createBytes(valueToEncrypt));
        
        return encrValue;
        
    }
    
    public byte[] encrypt(byte[] valueToEncrypt) throws Exception {
        
        Cipher c = Cipher.getInstance(CIPHER);
        c.init(Cipher.ENCRYPT_MODE, key);

        byte[] encrValue = c.doFinal(valueToEncrypt);
        
        return encrValue;
        
    }
    
    public String decrypt(byte[] valueToDecrypt) throws Exception {
        
        Cipher c = Cipher.getInstance(CIPHER);
        c.init(Cipher.DECRYPT_MODE, key);

        byte[] decrValue = c.doFinal(valueToDecrypt);
        
        return ByteManager.reformatString(ByteManager.createString(decrValue));
        
    }
    
    public String decrypt(String valueToDecrypt) throws Exception {
        
        Cipher c = Cipher.getInstance(CIPHER);
        c.init(Cipher.DECRYPT_MODE, key);

        byte[] decrValue;
        try{
            decrValue = c.doFinal(ByteManager.createBytes(valueToDecrypt));
        }catch(IllegalBlockSizeException | BadPaddingException ex){
            return null;
        }
        
        return ByteManager.reformatString(ByteManager.createString(decrValue));
        
    }

}