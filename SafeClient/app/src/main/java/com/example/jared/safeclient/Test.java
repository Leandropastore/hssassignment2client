package com.example.jared.safeclient;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leandro
 */
public class Test {
    public static void main(String[] args) {

        ///*
        try {
            AES test = new AES(ByteManager.createBytes("105,-32,-74,-126,91,-44,38,-95,8,48,-45,-18,-13,7,94,56"));
            ByteManager bm = new ByteManager();

            byte[] ahaha = test.encrypt("Seth;193492842039;182379147239324");
            String ahahaha = bm.createString(ahaha);
            System.out.println(ahahaha);
            byte[] ahahahaha = bm.createBytes(ahahaha);
            System.out.println(test.decrypt(ahahahaha));
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        //*/
        /*
        RSA rsa = new RSA();

        RSA publicK = new RSA(rsa.getPublic(),"public");
        RSA privateK = new RSA(rsa.getPrivate(),"private");

        byte[] result = rsa.encryptWithPublic("Leandro");
        String res = rsa.decryptWithPrivate(publicK.encryptWithPublic("Leandro"));

        System.out.println(ByteManager.createString(result));
        System.out.println(ByteManager.createString(publicK.encryptWithPublic("Leandro")));

        System.out.println(privateK.decryptWithPrivate(publicK.encryptWithPublic("Leandro")));

        System.out.println(res);
        System.out.println(privateK.decryptWithPrivate(result));

        System.out.println(ByteManager.createString(rsa.getPublic()));
        */
        
    }
}
