package handelsakademin.exempel;

public class Entitet {
    private int id;
    private String namn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String hämtaBeskrivning() {
        return "En beskrivning.";
    }
}
