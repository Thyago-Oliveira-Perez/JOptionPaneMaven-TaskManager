package Model;

public class Notes {

    private int id;
    private String owner;
    private String note;

    public Notes() {
    }

    public Notes(int id, String nome_note, String note) {
        this.id = id;
        this.owner = nome_note;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNome_note() {
        return owner;
    }

    public void setNome_note(String nome_note) {
        this.owner = nome_note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", nome_note='" + owner + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
