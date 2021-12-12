package VIEW;

import Controller.ControllerNotes;
import Controller.ControllerUsers;
import Model.Users;

import javax.swing.*;
import java.awt.*;

public class ViewMenu {

    ViewUsers viewUsers = new ViewUsers();
    ControllerUsers controllerUsers = new ControllerUsers();
    ViewNotes viewNotes = new ViewNotes();

    public void mainMenu(){
        int choice;
        Object[] firstOptions = {
          "Login",
          "Sign up",
          "Exit"
        };

        Object[] admOptions = {
                "Notes",
                "Users",
                "Back"
        };

        choice = JOptionPane.showOptionDialog(null, "First Progam \nUsing JOptionPane", "Enter a Number",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, firstOptions, null);
        if(choice == JOptionPane.OK_OPTION){
            Users users;
            users = viewUsers.login();
            if(users != null){
                if(users.getActive()){
                    userMenu(users);
                    if(controllerUsers.isAdmin(users)){
                        choice = JOptionPane.showOptionDialog(null, null, null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, admOptions, null);
                        if(choice == JOptionPane.YES_OPTION){
                            viewNotes.menuNotes(users);
                        }else if(choice == JOptionPane.NO_OPTION){
                            viewUsers.menuUser();
                        }else{
                            mainMenu();
                        }
                    }else if(controllerUsers.isAdmin(users) == false){
                        userMenu(users);
                    }
                }
            }else{
                mainMenu();
            }
        }else if(choice == JOptionPane.NO_OPTION){
            viewUsers.registrer();
            mainMenu();
        }else{
            JOptionPane.showMessageDialog(null,"See ya!");
            System.exit(0);
        }
    }

    public void userMenu(Users users){
        int choice;

        Object[] userDatasAndBackButton = {
                "Enter system",
                "Log out"
        };

        choice = JOptionPane.showOptionDialog(null, ("User on: " + users.getLogin()), null, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, userDatasAndBackButton, null);

        if(choice == JOptionPane.YES_OPTION){
            if(controllerUsers.isAdmin(users)){
                Object[] admOptions = {
                        "Notes",
                        "Users",
                        "Back"
                };
                choice = JOptionPane.showOptionDialog(null, null, null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, admOptions, null);
                if(choice == JOptionPane.YES_OPTION){
                    viewNotes.menuNotes(users);
                }else if(choice == JOptionPane.NO_OPTION){
                    viewUsers.menuUser();
                }else{
                    userMenu(users);
                }
            }else{
                Object[] normalUserOptions = {
                        "My notes",
                        "Add note",
                        "Back"
                };
                choice = JOptionPane.showOptionDialog(null, null, null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, normalUserOptions, null);
                if(choice == JOptionPane.YES_OPTION){
                    viewNotes.menuNotes(users);
                }else if(choice == JOptionPane.NO_OPTION){
                    viewUsers.menuUser();
                }else{
                    userMenu(users);
                }
            }
        }else{
            mainMenu();
        }

    }
}
