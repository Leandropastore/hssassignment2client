/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jared.safeclient;

/**
 *
 * @author Leandro
 */
public class ByteManager {
    
    public static byte[] createBytes(String string){
    
        String[] values = string.split(",");
        byte[] bytes = new byte[values.length];
        int g;
        for(int i=0; i<values.length;i++){
            g = new Integer(values[i]);
            bytes[i]= (byte) g;
        }
        
        return bytes;
    
    }
    
    public static String createString(byte[] bytes){
    
        String string = "";
        int g;
        for(int i=0; i<bytes.length;i++){
            g=bytes[i];
            string+=g;
            if(i!=bytes.length-1)
                string += ",";
        }
        
        return string;
    
    }
    
    public static String formatString(String string){
    
        String s = "";
        for(int i=0;i<string.length();i++){
            s += new Integer(string.charAt(i));
            if(i!=string.length()-1)
                s += ",";
        }
        return s;
        
    }
    
    public static String reformatString(String string){
    
        String[] s = string.split(",");
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<s.length;i++){
        
            sb.append(Character.toChars(new Integer(s[i])));
            
        }
        return new String(sb);
    
    }
    
    public static int stringByteSize(String string){
    
        String[] s = string.split(",");
        return s.length;
    
    }
    
}
