package src;

import jdk.internal.org.objectweb.asm.util.TraceAnnotationVisitor;

import java.util.Random;

public class amminozuur {
    private String naam;
    private String grootte;
    private char onel;
    private String threel;
    private String Hybrofobe;
    private String charge;
    private String threed;
    private String sidechain;

    public amminozuur(String naam, char onel, String threel, String hybrofobe, String charge, String grootte, String threed) {
        this.naam = naam;
        this.grootte = grootte;
        this.onel = onel;
        this.threel = threel;
        this.Hybrofobe = hybrofobe;
        this.charge = charge;
        this.threed = threed;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getGrootte() {
        return grootte;
    }

    public void setGrootte(String grootte) {
        this.grootte = grootte;
    }

    public char getOnel() {
        return onel;
    }

    public void setOnel(char onel) {
        this.onel = onel;
    }

    public String getThreel() {
        return threel;
    }

    public void setThreel(String threel) {
        this.threel = threel;
    }

    public String getHybrofobe() {
        return this.Hybrofobe;
    }

    public void setHybrofobe(String hybrofobe) {
        this.Hybrofobe = hybrofobe;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getThreed() {
        return threed;
    }

    public void setThreed(String threed) {
        this.threed = threed;
    }

    public String getSidechain() {
        return sidechain;
    }

    public void setSidechain(String sidechain) {
        this.sidechain = sidechain;
    }

    public Boolean hasValue(String value) {
        return (this.getNaam().equals(value) || this.getHybrofobe().equals(value) || this.getThreed().equals(value) || this.getGrootte().equals(value)
                || this.getCharge().equals(value) || this.getThreel().equals(value) || ""+this.getOnel() == value);
    }

    public String getRandomValue(){
        Random rand = new Random();
        Integer randint = rand.nextInt(3);
        switch (randint){
            case 0:
                return ""+this.onel;
            case 1:
                return this.threel;
            case 2:
                return this.naam;
        }
        return "Failed";
    }
}
