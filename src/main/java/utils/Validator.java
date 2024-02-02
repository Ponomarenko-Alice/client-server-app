package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validateCommandName(String string) {
        String expression = "^[_a-z]\\w+$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

}
