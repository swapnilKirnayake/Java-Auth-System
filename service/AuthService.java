package service;

import model.User;
import java.io.*;
import java.util.*;

public class AuthService {
    private static final String FILE = "users.txt";

    public static boolean emailExists(String email) throws IOException{
        for(User u : getAllUsers()){
            if(u.getEmail().equalsIgnoreCase(email)){
                return true;
            }
            
        }
        return false;
    }

    public static void register(User user) throws IOException{
        if(emailExists(user.getEmail())){
            throw new IOException("Email is already registered");
        }
        try(FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(user.toFileString() + "\n");
        }
    }
    
        public static boolean login(String  email, String password) throws IOException {
            String hashed = PasswordUtil.hash(password);
            for(User u : getAllUsers()) {
                if(u.getEmail().equalsIgnoreCase(email) && u.getHashedPassword().equals(hashed)){
                    return true;
                }
            }
            return false;
        }

        private static List<User> getAllUsers() throws IOException {
            List<User> users = new ArrayList<>();
            File file = new File(FILE);
            if(!file.exists()) return users;

            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                String line;
                while((line = br.readLine()) != null){
                    users.add(User.fromFileString(line));
                }
            }
            return users;
        
    
    }
}
