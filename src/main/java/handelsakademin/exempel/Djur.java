package handelsakademin.exempel;

public class Djur extends Entitet {
    private String art;

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public void matadjur(Gröda g) {
        g.setMängd(g.getMängd() - 1);
        System.out.println(getNamn() + " blev mätt.");
    }

    @Override
    public String hämtaBeskrivning() {
        return "ID:" + getId() + ", Namn:" + getNamn() + ", Art:" + getArt();
    }
}
