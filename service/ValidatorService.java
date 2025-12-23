package service;

import model.UserForm;
import java.util.regex.Pattern;

public class ValidatorService{

    public static String validate(UserForm u){

        if(!isValidName(u.getName()))
            return "Name must contain only letters and spaces";

        if(!isValidEmail(u.getEmail()))
            return "Invalid email format";

        if(!isValidPhone(u.getPhone()))
            return "Phone number must be 10 digits";

        if(!isValidPassword(u.getPassword()))
            return "Password must be at least 8 characters and include uppercase, lowercase, number, and special characters";

        return " All inputs are valid ";

    }

    private static boolean isValidName(String name){
        return name != null && name.matches("[A-Za-z]+( [A-Za-z]+)*");
    }

    private static boolean isValidEmail(String email){
        String regex = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(regex, email);
    }

    private static boolean isValidPhone(String phone){
        return phone != null && phone.matches("\\d{10}");
    }

    private static boolean isValidPassword(String pwd){
        return pwd != null &&
        pwd.length() >= 8 &&
        pwd.matches(".*[A-Z].*") &&
        pwd.matches(".*[a-z].*") &&
        pwd.matches(".*\\d.*") &&
        pwd.matches(".*[@#$%!].*");
    }
}