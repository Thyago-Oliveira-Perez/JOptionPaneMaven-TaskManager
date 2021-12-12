package Model;

public class Users {

    private int id_user;
    private String login;
    private String email;
    private String password;
    private Boolean adm;
    private Boolean normal;
    private int idNote;
    private Boolean active;

    public Users() {
    }

    public Users(String login, String email, String senha, Boolean adm, Boolean normal, int idNote, int id_user) {
        this.id_user = id_user;
        this.login = login;
        this.email = email;
        this.password = senha;
        this.adm = adm;
        this.normal = normal;
        this.idNote = idNote;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }

    public Boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }

    public Boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id_user=" + id_user +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", adm=" + adm +
                ", normal=" + normal +
                ", idNote=" + idNote +
                '}';
    }

    public String showUsers() {
        return  "ID:" + id_user + "\n"+
                "Login: " + login + "\n" +
                "Email: " + email + "\n" +
                "Password: " + "***************" + "\n" +
                "Administrator: " + adm + "\n" +
                "Normal User: " + normal;
    }

    public String showeUserSingular(){
        return  "ID:" + id_user + "\n"+
                "Login: " + login + "\n";
    }

    public String showUser() {
        return  "ID:" + id_user + "\n"+
                "Login: " + login + "\n" +
                "Email: " + email + "\n" +
                "Password: " + password + "\n" +
                "Administrator: " + adm + "\n" +
                "Normal User: " + normal + "\n" +
                "Id table note: " + idNote;
    }
}
