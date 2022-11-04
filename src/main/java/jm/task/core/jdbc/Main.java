package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserService userService=new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 18);
        userService.saveUser("Boris", "Smirnov", (byte) 21);
        userService.saveUser("Konstantin", "Vasiliev", (byte) 33);
        userService.saveUser("Valery", "Popov", (byte) 44);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeConnection();











    }
}
