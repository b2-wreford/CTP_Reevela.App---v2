package com.example.benwr.reevelaapp.Utils;


/**
 * Used to manipulate strings entered by the user to match up to the database strings
 */
public class StringManipulations {

    public static String expandUsername(String username) {
        return username.replace(".", " ");
    }

    public static String condenseUsername(String username) {
        return username.replace(" ", ".");
    }
}
