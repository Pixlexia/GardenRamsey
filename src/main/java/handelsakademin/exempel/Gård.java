package handelsakademin.exempel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Gård {
    private String sökväg = System.getProperty("user.dir");
    private String djurFilsNamn = "djurfil.csv";
    private String grödFilsNamn = "grödfil.csv";
    private List<Djur> djurenListan = new ArrayList<>();
    private List<Gröda> grödListan = new ArrayList<>();

    public Gård() throws IOException {
        String komplettDjurSökväg = Paths.get(sökväg, djurFilsNamn).toString();
        String komplettGrödaSökväg = Paths.get(sökväg, grödFilsNamn).toString();

        boolean djurFilFinns = Files.exists(Paths.get(komplettDjurSökväg));
        boolean djurFilÄrTom = false;

        if (djurFilFinns) {
            List<String> hämtadeDjur = Files.readAllLines(Paths.get(komplettDjurSökväg));
            djurFilÄrTom = hämtadeDjur.isEmpty();

            for (String linje : hämtadeDjur) {
                String[] deladLinje = linje.split(",");
                Djur djur = new Djur();
                djur.setId(Integer.parseInt(deladLinje[0]));
                djur.setNamn(deladLinje[1]);
                djur.setArt(deladLinje[2]);
                djurenListan.add(djur);
            }
        }

        boolean grödaFilFinns = Files.exists(Paths.get(komplettGrödaSökväg));
        boolean grödFilÄrTom = false;

        if (grödaFilFinns == true) {
            try {
                List<String> hämtadeGrödor = Files.readAllLines(Paths.get(komplettGrödaSökväg));
                grödFilÄrTom = hämtadeGrödor.isEmpty();

                for (String linje : hämtadeGrödor) {
                    String[] deladLinje = linje.split(",");
                    Gröda gröda = new Gröda();
                    gröda.setId(Integer.parseInt(deladLinje[0]));
                    gröda.setNamn(deladLinje[1]);
                    gröda.setGrödTyp(deladLinje[2]);
                    gröda.setMängd(Integer.parseInt(deladLinje[3]));
                    grödListan.add(gröda);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (djurFilFinns == false || djurFilÄrTom == true) {
            Djur djur1 = new Djur();
            djur1.setNamn("Berit");
            djur1.setArt("Häst");
            djur1.setId(1);
            djurenListan.add(djur1);

            Djur djur2 = new Djur();
            djur2.setNamn("Hans");
            djur2.setArt("Häst");
            djur2.setId(2);
            djurenListan.add(djur2);
        }

        if (grödaFilFinns == false || grödFilÄrTom == true) {
            Gröda gröda1 = new Gröda();
            gröda1.setNamn("Lyxig fröblanding");
            gröda1.setGrödTyp("Frö");
            gröda1.setMängd(25);
            gröda1.setId(3);
            grödListan.add(gröda1);

            Gröda gröda2 = new Gröda();
            gröda2.setNamn("Hö");
            gröda2.setGrödTyp("Torrfoder");
            gröda2.setMängd(15);
            gröda2.setId(4);
            grödListan.add(gröda2);
        }
    }


    public void Huvudmeny() {
        System.out.println("\n"
                + " * GRÖDOR *\n"
                + "1. Visa gröda\n"
                + "2. Lägg till gröda\n"
                + "3. Tabort gröda\n"
                + "\n"
                + " * DJUR *\n"
                + "4. Visa djur\n"
                + "5. Lägg till djur\n"
                + "6. Mata djur\n"
                + "7. Tabort djur\n"
                + "***\n"
                + "8. Spara\n");

        Scanner scanner = new Scanner(System.in);
        String menyInput = scanner.nextLine();

        switch (menyInput) {
            case "1":
                VisaGröda();
                break;
            case "2":
                LäggTillGröda();
                break;
            case "3":
                TabortGröda();
                break;
            case "4":
                VisaDjur();
                break;
            case "5":
                LäggTillDjur();
                break;
            case "6":
                MataDjur();
                break;
            case "7":
                TabortDjur();
                break;
            case "8":
                Spara();
                break;
            default:
                System.out.println("Ogiltigt val, försök igen.");
                break;
        }
    }

    private void VisaGröda() {
        System.out.println("\nGrödor: ");
        for (Gröda g : grödListan) {
            System.out.println(g.hämtaBeskrivning());
        }
    }

    private void TabortGröda() {
        System.out.println("Ta bort gröda:");
        VisaGröda();

        System.out.println("Skriv id på den gröda du vill ta bort.");
        Scanner scanner = new Scanner(System.in);
        int idAttTabort = scanner.nextInt();

        Gröda grödaAttTaBort = null;
        for (Gröda g : grödListan) {
            if (g.getId() == idAttTabort) {
                grödaAttTaBort = g;
                break;
            }
        }

        if (grödaAttTaBort != null) {
            grödListan.remove(grödaAttTaBort);
            System.out.println("Du tog bort gröda med Id: " + idAttTabort);
        } else {
            System.out.println("Idt finns inte i listan");
        }
    }


    private void LäggTillGröda() {
        System.out.println("Lägg till gröda");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Typ av gröda: ");
        String typAvGröda = scanner.nextLine();

        System.out.print("Namn: ");
        String grödNamn = scanner.nextLine();

        System.out.print("Antal: ");
        int antal = scanner.nextInt();

        int högstaId = 0;
        for (Gröda g : grödListan) {
            if (g.getId() > högstaId) {
                högstaId = g.getId();
            }
        }

        Gröda nyGröda = new Gröda();
        nyGröda.setGrödTyp(typAvGröda);
        nyGröda.setNamn(grödNamn);
        nyGröda.setMängd(antal);
        nyGröda.setId(högstaId + 1);
        grödListan.add(nyGröda);
    }

    private void VisaDjur() {
        System.out.println("\nDjuren: ");
        for (Djur d : djurenListan) {
            System.out.println(d.hämtaBeskrivning());
        }
    }

    private void LäggTillDjur() {
        Djur nyaDjur = new Djur();

        System.out.println("Lägg till Djur");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Namn: ");
        String djurNamn = scanner.nextLine();

        System.out.print("Art: ");
        String djurArt = scanner.nextLine();

        int högstaId = 0;
        for (Djur d : djurenListan) {
            if (d.getId() > högstaId) {
                högstaId = d.getId();
            }
        }

        nyaDjur.setNamn(djurNamn);
        nyaDjur.setArt(djurArt);
        nyaDjur.setId(högstaId + 1);
        djurenListan.add(nyaDjur);
    }

    private void MataDjur() {
        System.out.println("Mata djur\n");
        VisaDjur();

        System.out.println("Skriv in Id på det djur du vill mata.");
        Scanner scanner = new Scanner(System.in);
        int mataDjurMedId = scanner.nextInt();

        System.out.println("\nVälj det id på den gröda du vill mata djuret med?");
        VisaGröda();
        int vilkenGrödaId = scanner.nextInt();

        for (Gröda g : grödListan) {
            if (g.getId() == vilkenGrödaId) {
                if (g.getMängd() > 0) {
                    for (Djur d : djurenListan) {
                        if (mataDjurMedId == d.getId()) {
                            d.matadjur(g);
                            break;
                        }
                    }
                    System.out.println("En enhet av gröda " + g.getId() + " har använts för att mata djuret.");
                    return;
                } else {
                    System.out.println("Ingen gröda kvar av denna typ.");
                }
                break;
            }
        }
    }

    private void TabortDjur() {
        System.out.println("Ta bort djur:");
        VisaDjur();

        System.out.println("\nSkriv id på det djur du vill ta bort. (Ge bort till grannen Gunnar)");
        Scanner scanner = new Scanner(System.in);
        int typAvDjurId = scanner.nextInt();

        Djur djurAttTaBort = null;
        for (Djur d : djurenListan) {
            if (d.getId() == typAvDjurId) {
                djurAttTaBort = d;
                break;
            }
        }

        if (djurAttTaBort != null) {
            djurenListan.remove(djurAttTaBort);
            System.out.println("Du tog bort/gav bort djur med Id: " + typAvDjurId);
        } else {
            System.out.println("Idt finns inte i listan");
        }
    }


    private void Spara() {
        String komplettDjurSökväg = Paths.get(sökväg, djurFilsNamn).toString();
        String komplettGrödaSökväg = Paths.get(sökväg, grödFilsNamn).toString();

        // Djur spara start
        try {
            String[] djurCsvFormat = new String[djurenListan.size()];
            int räknaDjur = 0;

            for (Djur d : djurenListan) {
                djurCsvFormat[räknaDjur] = d.getId() + "," + d.getNamn() + "," + d.getArt();
                räknaDjur++;
            }

            Files.write(Paths.get(komplettDjurSökväg), Arrays.asList(djurCsvFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Djur spara slut

        // Gröda spara start
        try {
            String[] grödaCsvFormat = new String[grödListan.size()];
            int räknaGröda = 0;

            for (Gröda g : grödListan) {
                grödaCsvFormat[räknaGröda] = g.getId() + "," + g.getNamn() + "," + g.getGrödTyp() + "," + g.getMängd();
                räknaGröda++;
            }

            Files.write(Paths.get(komplettGrödaSökväg), Arrays.asList(grödaCsvFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Gröda spara slut
        System.out.println("Sparat");
    }
}
