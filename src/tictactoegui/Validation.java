/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoegui;

/**
 *
 * @author John
 */
public class Validation {
    public static boolean usernameValid(String username) {
        String[] userLength = username.split("");
        if (userLength.length >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean passwordValid(String password) {
        String[] passLength = password.split("");
        String Chars = "!#$%&'()*+,-./:<=>?@[]^_`{|}~";
        String[] specialChars = Chars.split("");
        boolean properLength = false;
        boolean hasLowerLetter = false;
        boolean hasUpperLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialChars = false;
        if (passLength.length >= 6) {
            properLength = true;
        }
        for (int i = 0; i < password.length(); i++) {
            char x = password.charAt(i);
            if (Character.isLowerCase(x)) {
                hasLowerLetter = true;
            } else if (Character.isUpperCase(x)) {
                hasUpperLetter = true;
            } else if (Character.isDigit(x)) {
                hasDigit = true;
            }
            for (int spChar = 0; spChar < specialChars.length; spChar++) {
                if (Character.toString(x).equals(specialChars[spChar])) {
                    hasSpecialChars = true;
                }
            }
        }
        if (properLength && hasLowerLetter && hasUpperLetter && hasDigit && hasSpecialChars) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean passwordMatch(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean emailValid(String email) {
        if (email.matches("^[0-9a-zA-Z!#$%&;'*+\\-/\\=\\?\\^_`\\.{|}~]{1,64}@[0-9a-zA-Z]{1,255}\\.[a-zA-Z]{1,10}") && email.contains("@") && email.length() <= 320) {
            return true;
        } else {
            return false;
        }
    }
}
