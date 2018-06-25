package src;

//Deze klasse slaat alles op wat is aangeklikt/ingevuld bij het optiescherm. Overal is een get en set functie van.

import java.util.List;

public class Instellingen {
    private String naam;
    private String hoeveelheid;
    private List<String> soort_vragen;
    private List<String> soort_antwoorden;
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

    public List<String> getSoort_antwoorden() {
        return soort_antwoorden;
    }

    public void setSoort_antwoorden(List<String> soort_antwoorden) {
        this.soort_antwoorden = soort_antwoorden;
    }


    public List<String> getSoort_vragen() {
        return soort_vragen;
    }

    public void setSoort_vragen(List<String> soort_vragen) {
        this.soort_vragen = soort_vragen;
    }


    public void setHoeveelheid(String hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }

    public Integer getHoeveelheid() {
        int hoeveelheid = Integer.parseInt(this.hoeveelheid.trim());
        return hoeveelheid;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Instellingen returnAll(){
        return this;
    }
}
