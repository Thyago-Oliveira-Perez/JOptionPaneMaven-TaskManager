package VIEW;
import Controller.ControllerNotes;
import Controller.ControllerUsers;
import Model.Users;

import javax.swing.*;
import javax.swing.text.View;
import java.util.List;

public class ViewUsers {

    Users user = new Users();
    ControllerUsers controllerUsers = new ControllerUsers();

    public Users login(){
        JTextField login = new JTextField();
        JTextField password = new JPasswordField();

        Users usersAux = new Users();

        Object[] message = {
                "Log in:", login,
                "Password:", password
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION){
            if(!login.getText().isEmpty() && !password.getText().isEmpty()){
                user.setLogin(login.getText());
                user.setPassword(password.getText());
                usersAux = controllerUsers.login(user);
                if(usersAux != null){
                    if(usersAux.getActive()){
                        System.out.println("Login successfully");
                        System.out.println(usersAux);
                        return usersAux;
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Login Failed!\nTry Again.");
                    login();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Please, try again.");
                login();
            }
        }else{
            System.out.println("Login canceled");
        }
        return null;
    }

    public void registrer(){
        JTextField login = new JTextField();
        JTextField password = new JPasswordField();
        JTextField email = new JTextField();

        JCheckBox adm = new JCheckBox();
        JCheckBox normal_user = new JCheckBox();

        Object[] registrer = {
                "Log in:", login,
                "Email", email,
                "Password:", password,
                "Admin", adm,
                "Normal user", normal_user
        };


        int option = JOptionPane.showConfirmDialog(null, registrer, "Sign up", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            user.setLogin(login.getText());
            user.setEmail(email.getText());
            user.setPassword(password.getText());
            if (adm.isSelected()) {
                user.setAdm(true);
                user.setNormal(false);
            } else if (normal_user.isSelected()) {
                user.setNormal(true);
                user.setAdm(false);
            }
            System.out.println(user);
            if (controllerUsers.registrer(user)) {
                System.out.println("Registered successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Registrer Failed!\nTry Again.");
                registrer();
            }
        }else{
            System.out.println("Login canceled");
        }

    }

    public String showUsers(){
        String usersList = "";
        int size = controllerUsers.showUsers().size();
        for(int i = 0; i < size; i++){
            usersList += controllerUsers.showUsers().get(i).showeUserSingular() + "\n\n";
        }
        return usersList;
    }

    public void deleteUser(){
        JTextField idSelected = new JTextField();
        int size = controllerUsers.showUsers().size();
        List<Users> usersList = controllerUsers.showUsers();
        Object[] options = {
                showUsers(),
                "Type id chosen", idSelected,
        };

        int choice = JOptionPane.showConfirmDialog(null, options, "Actions", JOptionPane.OK_CANCEL_OPTION);
        if(choice == JOptionPane.OK_OPTION){
            if(!idSelected.getText().isEmpty()){
                int idDell = Integer.parseInt(idSelected.getText());
                for(int i = 0; i < size; i++){
                    if(usersList.get(i).getId_user() == idDell && !usersList.get(i).isAdm()){
                        controllerUsers.deleteUser(idDell);
                        JOptionPane.showMessageDialog(null, "User deleted successfully");
                        menuUser();
                    }else if(usersList.get(i).getId_user() == idDell && usersList.get(i).isAdm()){
                        JOptionPane.showMessageDialog(null, "User can not be delete, try again.");
                        menuUser();
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Please, try again.");
                deleteUser();
            }
        }else{
            menuUser();
        }
    }

    public void menuUser(){
        ViewMenu viewMenu = new ViewMenu();
        Object[] options = {
                "Show All Users",
                "Delete User",
                "Log out"
        };

        int choice = JOptionPane.showOptionDialog(null, null, null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
        if(choice == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, showUsers(), "All users", JOptionPane.INFORMATION_MESSAGE);
            menuUser();
        }else if(choice == JOptionPane.NO_OPTION){
            deleteUser();
            menuUser();
        }else{
            viewMenu.mainMenu();
        }
    }

}
