package Controller;

import DAO.DaoUsers;
import Model.Users;

import java.awt.desktop.UserSessionEvent;
import java.util.List;

public class ControllerUsers {
    DaoUsers daoUsers = new DaoUsers();
    public Users login(Users userInsert){
        return daoUsers.login(userInsert);
    }

    public boolean registrer(Users userInsert){
        return daoUsers.registrerUsers(userInsert);
    }

    public List<Users> showUsers(){
        return daoUsers.selectAll();
    }

    public Boolean isAdmin(Users users){
        return daoUsers.selectIsAdmin(users);
    }

    public Boolean deleteUser(int idDell){
        return daoUsers.deleteUser(idDell);
    }
}
