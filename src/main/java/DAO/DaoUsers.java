package DAO;

import ConnectionFactory.ConnectionFactory;
import Model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DaoUsers {
    private Connection connection;

    public DaoUsers() {
        this.connection = ConnectionFactory.getConnection();
    }
    Users users = new Users();

    public void creatTable(){
        String sqlCreat = "CREATE TABLE IF NOT EXISTS `users` ( " +
                "`id_users` INT NOT NULL AUTO_INCREMENT," +
                "`login` VARCHAR(20) NOT NULL UNIQUE," +
                "`email` VARCHAR(50) NOT NULL UNIQUE," +
                "`password` VARCHAR(20) NOT NULL," +
                "`admin` INT," +
                "`normal_user` INT," +
                "PRIMARY KEY (id_users))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCreat);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registrerUsers(Users users){
        String sqlInsert = "INSERT INTO users (id_users, login, email, password, admin, normal_user) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setInt(1, users.getId_user());
            preparedStatement.setString(2, users.getLogin());
            preparedStatement.setString(3, users.getEmail());
            preparedStatement.setString(4, users.getPassword());
            preparedStatement.setInt(5, convertBooleanInt(users.isAdm()));
            preparedStatement.setInt(6, convertBooleanInt(users.isNormal()));
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Users login(Users userLogin){
        String sqlSelect = "SELECT * FROM `users` WHERE login = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
            preparedStatement.setString(1, userLogin.getLogin());
            preparedStatement.setString(2, userLogin.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                users.setId_user(resultSet.getInt("id_users"));
                users.setLogin(resultSet.getString("login"));
                users.setEmail(resultSet.getString("email"));
                users.setPassword(resultSet.getString("password"));
                users.setAdm(convertIntBolean(resultSet.getInt("admin")));
                users.setNormal(convertIntBolean(resultSet.getInt("normal_user")));
                users.setActive(true);
            }
            if(Objects.equals(userLogin.getLogin(), users.getLogin()) && Objects.equals(userLogin.getPassword(), users.getPassword())){
                return users;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean selectIsAdmin(Users user){
        String sqlSelect = "SELECT * FROM `users` WHERE login = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                users.setId_user(resultSet.getInt("id_users"));
                users.setLogin(resultSet.getString("login"));
                users.setEmail(resultSet.getString("email"));
                users.setPassword(resultSet.getString("password"));
                users.setAdm(convertIntBolean(resultSet.getInt("admin")));
                users.setNormal(convertIntBolean(resultSet.getInt("normal_user")));
            }
            if(users.isAdm()){
                return true;
            }else if(users.isNormal()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Users> selectAll(){
        String sqlSelectAll = "SELECT * FROM `users`";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Users> baseReturn = new ArrayList<>();
            while(resultSet.next()){
                Users users = new Users();
                users.setId_user(resultSet.getInt("id_users"));
                users.setLogin(resultSet.getString("login"));
                users.setEmail(resultSet.getString("email"));
                users.setPassword(resultSet.getString("password"));
                users.setAdm(convertIntBolean(resultSet.getInt("admin")));
                users.setNormal(convertIntBolean(resultSet.getInt("normal_user")));
                baseReturn.add(users);
            }
            return baseReturn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String selectLoginByIdNote(Users users){
        String owner = "";
        String sqlSelectByIdNote = "SELECT `login` FROM `users` WHERE `id_users` = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectByIdNote);
            preparedStatement.setInt(1, users.getId_user());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                owner = resultSet.getString("login");
            }
            return owner;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owner;
    }

    public Boolean deleteUser(int idDell){
        String sqlDelete = "DELETE FROM `users` WHERE id_users = ? AND admin != 1";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
                preparedStatement.setInt(1, idDell);
                preparedStatement.execute();
                preparedStatement.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return false;
    }

    public int convertBooleanInt(Boolean position){
        int trueValue = 1;
        int falseValue = 0;
        if(position == true){
            return trueValue;
        }else{
            return falseValue;
        }
    }

    public boolean convertIntBolean(int position){
        if(position == 1){
            return true;
        }else{
            return false;
        }
    }

}
