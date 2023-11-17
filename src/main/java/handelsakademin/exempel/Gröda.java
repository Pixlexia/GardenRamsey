package handelsakademin.exempel;

public class Gröda extends Entitet {
    private String grödTyp;
    private int mängd;

    public String getGrödTyp() {
        return grödTyp;
    }

    public void setGrödTyp(String grödTyp) {
        this.grödTyp = grödTyp;
    }

    public int getMängd() {
        return mängd;
    }

    public void setMängd(int mängd) {
        this.mängd = mängd;
    }


    @Override
    public String hämtaBeskrivning() {
        return "Id:" + getId() + ", Namn:" + getNamn() + ", Art:" + getGrödTyp() + ", Mängd:" + getMängd();
    }
}
