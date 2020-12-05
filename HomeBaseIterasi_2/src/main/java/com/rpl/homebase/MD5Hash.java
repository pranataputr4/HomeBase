package com.rpl.homebase;

import java.security.MessageDigest;

/**
 *
 * @author Doni
 */
public class MD5Hash {
    public static String hashPassword(String pass) {
        String passwordToHash = pass;
        String generatedPassword = null;
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(passwordToHash.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();
            return generatedPassword;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}