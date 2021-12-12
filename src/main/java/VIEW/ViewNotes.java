package VIEW;

import Controller.ControllerNotes;
import Model.Users;

import javax.swing.*;

public class ViewNotes {

    ControllerNotes controllerNotes = new ControllerNotes();

    public String showMyNotes(Users users){
        String notes= "";
        int size = controllerNotes.selectByUserId(users).size();
        for(int i = 0; i < size; i++){
            notes += controllerNotes.selectByUserId(users).get(i) + "\n";
        }
        return notes;
    }

    public void menuNotes(Users users){
        ViewMenu viewMenu = new ViewMenu();
        JTextField idDelete = new JTextField();
        JTextField newNote = new JTextField();

        Object[] options = {
                "Show my notes",
                "Delete note",
                "Back"
        };

        Object[] delete = {
                showMyNotes(users),
                "Id to delete: ", idDelete,
        };

        Object[] myNotes = {
                controllerNotes.selectByUserId(users)
        };

        Object[] createNote = {
                controllerNotes.selectByUserId(users),
                "Add note", newNote,
        };

        int choice = JOptionPane.showOptionDialog(null, null, null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(choice == JOptionPane.YES_OPTION){
            int newNoteOption = JOptionPane.showConfirmDialog(null, createNote, "All my notes", JOptionPane.OK_CANCEL_OPTION);
            if(newNoteOption == JOptionPane.OK_OPTION){
                controllerNotes.createNewNote(users, newNote.getText());
            }
            menuNotes(users);
        }else if(choice == JOptionPane.NO_OPTION){
            int deleteId = JOptionPane.showConfirmDialog(null, delete, null, JOptionPane.OK_CANCEL_OPTION);
            int idToDelete = Integer.parseInt(idDelete.getText());
            if(deleteId == JOptionPane.OK_OPTION){
                if(controllerNotes.deleteNote(idToDelete)){
                    JOptionPane.showMessageDialog(null, "Note deleted.");
                    menuNotes(users);
                }
            }else{
                menuNotes(users);
            }
            System.out.println("in progress");
            menuNotes(users);
        }else{
            viewMenu.userMenu(users);
        }
    }
}
