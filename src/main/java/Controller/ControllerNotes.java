package Controller;

import DAO.DaoNotes;
import Model.Notes;
import Model.Users;

import java.util.List;

public class ControllerNotes {

    DaoNotes daoNotes = new DaoNotes();

    public List<Notes> selectByUserId(Users users){
        return daoNotes.selectByUserId(users);
    }

    public Boolean deleteNote(int idNote){
        return daoNotes.deleteNote(idNote);
    }

    public void createNewNote(Users users, String note){
        daoNotes.registerNote(users ,note);
    }
}
