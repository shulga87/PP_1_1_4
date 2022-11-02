package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl  implements UserDao {

    Connection connection = getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS user(id BIGINT NOT NULL AUTO_INCREMENT, " +
                        "name VARCHAR(255)NOT NULL, " +
                        "lastName VARCHAR(255)NOT NULL," +
                        " age TINYINT NOT NULL, " +
                        "PRIMARY KEY(id));";
        try (PreparedStatement preparedStatement = connection.prepareStatement(create)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable()  {

        String dropTable="DROP TABLE IF EXISTS user;";

        try(PreparedStatement preparedStatement= connection.prepareStatement(dropTable)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO user(name, lastName, age) VALUES(?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        String deleteById= " delete from user where id = ?;";
        try(PreparedStatement preparedStatement=connection.prepareStatement(deleteById)) {
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        String getUsers = "SELECT * FROM user;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getUsers);

            while (resultSet.next()){
                User user=new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);

            }
            System.out.println(userList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String cleanTable="DELETE FROM user;";

        try(PreparedStatement preparedStatement= connection.prepareStatement(cleanTable)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
