import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;;

public class LoginManager {
    private ArrayList<Customer> users;
    private ArrayList<Admin> admins;

    public LoginManager(ArrayList<Customer> users, ArrayList<Admin> admins){
        this.users = users;
        this.admins = admins;
    }

    public int checkCredentials (String inputUsername, String inputPassword){
        inputUsername = inputUsername.trim();
        inputPassword = inputPassword.trim();

        for (Customer person:users){
            String username = person.getUsername().trim();
            String password = person.getPassword().trim();
            if (((inputUsername.compareTo(username))==0)&&((inputPassword.compareTo(password))==0)){
                /*int check = person.IsRegisteredUser();
                System.out.println(check);
                if ((check==1)){
                    return 3;
                }*/
                return 1;
            }
        }
        for(Admin person:admins){
            if (((inputUsername.compareTo(person.getUsername()))==0)&&((inputPassword.compareTo(person.getPassword()))==0)){
                return 2;
            }
        }
        return 0;
    }
}
