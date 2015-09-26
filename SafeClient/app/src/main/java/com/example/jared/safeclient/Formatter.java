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
public class Formatter {
    
    public static String formatString(String string){
    
        return string.replaceAll("(\\r|\\n|;)", "");
    
    }
    
}
