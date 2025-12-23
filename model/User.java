package model;

public class User {
    private String name;
    private String email;
    private String hashedPassword;

    public User(String name, String email, String hashedPassword){
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getHashedPassword(){
        return hashedPassword;
    }

    //convert object to  file line 
    public String toFileString(){
        return name + "," + email + "," + hashedPassword;
    }

    public static User fromFileString(String line){
        String[] parts = line.split(",");
        return new User(parts[0], parts[1], parts[2]);
    }

}
