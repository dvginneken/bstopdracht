package src;

//Deze klasse slaat alles op wat is aangeklikt/ingevuld bij het optiescherm. Overal is een get en set functie van.

public class Instellingen {
    private String naam;
    private String hoeveelheid;
    private String soort_vragen;
    private String soort_antwoorden;
    private String tijd;
    private String seconden;

    public String getSeconden() {
        return seconden;
    }

    public void setSeconden(String seconden) {
        this.seconden = seconden;
    }

    public String getTijd() {
        return tijd;
    }

    public void setTijd(String tijd) {
        this.tijd = tijd;
    }

    public String getSoort_antwoorden() {
        return soort_antwoorden;
    }

    public void setSoort_antwoorden(String soort_antwoorden) {
        this.soort_antwoorden = soort_antwoorden;
    }


    public String getSoort_vragen() {
        return soort_vragen;
    }

    public void setSoort_vragen(String soort_vragen) {
        this.soort_vragen = soort_vragen;
    }


    public void setHoeveelheid(String hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }

    public String getHoeveelheid() {
        return hoeveelheid;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
