package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static Pattern emailPattern;
    private static Pattern userNamePattern;
    private static Pattern passwordPattern;
    private static Pattern mobilePattern;
    private static Pattern numberPattern;
    private static Pattern idNumberPattern;
    public static boolean emailValidator(String email){
         emailPattern = Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)$");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }


    public static boolean usernameValidator(String userName) {
        userNamePattern = Pattern.compile("^[a-zA-Z0-9_ ]{4,}$");
        Matcher matcher = userNamePattern.matcher(userName);
        return matcher.matches();
    }

    public static boolean passwordValidator(String password) {
        passwordPattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }

    public static boolean phoneNumberValidator(String tele) {
        mobilePattern = Pattern.compile("^(0)([0-9+]{9,})$");
        Matcher matcher = mobilePattern.matcher(tele);
        return matcher.matches();

    }public static boolean numberValidator(String num) {
        numberPattern = Pattern.compile("^([0-9.]{1,15})$");
        Matcher matcher = numberPattern.matcher(num);
        return matcher.matches();

    }
    public static boolean idNumberValidator(String num) {
        idNumberPattern = Pattern.compile("^([0-9]{12})$");
        Matcher matcher = idNumberPattern.matcher(num);
        return matcher.matches();

    }
}
