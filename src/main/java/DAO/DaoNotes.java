package DAO;

import ConnectionFactory.ConnectionFactory;
import Model.Notes;
import Model.Users;

import java.security.cert.PKIXCertPathBuilderResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoNotes {
    private Connection connection;

    public DaoNotes() {
        this.connection = ConnectionFactory.getConnection();
    }

    Notes notes = new Notes();
    DaoUsers daoUsers = new DaoUsers();

    public void createTable(){
        String sqlCreat = "CREATE TABLE IF NOT EXISTS `notes` (" +
                "id_notes INT NOT NULL AUTO_INCREMENT," +
                "owner VARCHAR (100)," +
                "note VARCHAR(244)," +
                "id_users INT NOT NULL, " +
                "PRIMARY KEY (id_notes)," +
                "CONSTRAINT fk_id_user FOREIGN KEY (id_users)" +
                "REFERENCES  users (id_users)" +
                "ON DELETE CASCADE " +
                "ON UPDATE CASCADE)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCreat);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Notes> selectByUserId(Users users){
        String sqlSelect = "SELECT users.login, notes.* " +
                "FROM users INNER JOIN notes ON " +
                "users.id_users = notes.id_users AND users.id_users = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
            preparedStatement.setInt(1, users.getId_user());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Notes> dataReturn = new ArrayList<>();
            while(resultSet.next()){
                notes.setId(resultSet.getInt("id_notes"));
                notes.setOwner(daoUsers.selectLoginByIdNote(users));
                notes.setNote(resultSet.getString("note"));
                dataReturn.add(notes);
            }
            return dataReturn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void registerNote(Users users, String note){
        String sqlInsert = "INSERT INTO notes (owner, note, id_users) VALUES (?,?,?)";
        System.out.println(users);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, users.getLogin());
            preparedStatement.setString(2, note);
            preparedStatement.setInt(3, users.getId_user());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean deleteNote(int idNote){
        String sqlDelete = "DELETE FROM `notes` WHERE id_notes = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setInt(1, idNote);
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
