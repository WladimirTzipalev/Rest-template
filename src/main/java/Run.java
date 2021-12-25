import model.User;
import service.UserService;

public class Run {

    public static void main(String[] args) {

        UserService userService = new UserService();

        User user = new User(3L, "James", "Brown", (byte) 22);
        User user2 = new User(3L, "Thomas", "Shelby", (byte) 25);

        userService.readUsers();
        String result1 = userService.createUser(user);
        String result2 = userService.updateUser(user2);
        String result3 = userService.deleteUser(3L);
        System.out.println(result1 + result2 + result3);

    }
}
