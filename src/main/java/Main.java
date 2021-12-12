import DAO.DaoNotes;
import DAO.DaoUsers;
import Model.Users;
import VIEW.ViewMenu;
import VIEW.ViewUsers;

public class Main {
    public static void main(String[] args) {
        //create the table users
        DaoUsers daoUsers = new DaoUsers();
        daoUsers.creatTable();
        //create the table notes
        DaoNotes daoNotes = new DaoNotes();
        daoNotes.createTable();

        ViewMenu viewMenu = new ViewMenu();

        viewMenu.mainMenu();
    }
}
