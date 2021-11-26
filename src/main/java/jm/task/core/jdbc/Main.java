package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

//        userService.createUsersTable();
          userService.dropUsersTable();
//        userService.saveUser("Mike", "Dzen", (byte) 12);
//        userService.saveUser("Petr", "Ozerov", (byte) 18);
//        userService.saveUser("Zina", "kelli", (byte) 45);
//        userService.saveUser("Oracle", "Data", (byte) 50);
//        userService.getAllUsers();
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

    }
}
