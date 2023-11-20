package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        //СОЗДАНИЕ
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql="CREATE TABLE `mydbtest`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` TINYINT(100) NOT NULL,\n" +
                "  PRIMARY KEY (`id`))";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {

        }finally {
            preparedStatement.close();
            connection.close();
        }

    }

    public void dropUsersTable() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql="DROP TABLE `mydbtest`.`users`";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
            System.out.println("drop db ok!");
        } catch (SQLException e) {

        }finally {
            preparedStatement.close();
            connection.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql="INSERT INTO users ( NAME, LASTNAME,AGE) VALUE( ?, ?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            preparedStatement.close();
            connection.close();
        }
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement= null;
        String sql ="DELETE FROM users WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("не удалось удалить -"+id);
        }finally {
            preparedStatement.close();
            connection.close();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = getConnection();
        List<User> userList = new ArrayList<>();

        String sql ="select * from users";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            statement.close();
            connection.close();
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement= null;
        String sql ="DELETE FROM USERS";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
