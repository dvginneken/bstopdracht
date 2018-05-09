package src;

import java.util.Random;

public class vraag {

    private Integer aantal;
    private String vraag;
    private String[] opties;
    private String antwoord;

    public vraag(Integer aantal) {
        this.aantal = aantal;
    }

    public Integer getAantal() {
        return aantal;
    }

    public void setAantal(Integer aantal) {
        this.aantal = aantal;
    }

    public String getVraag() {
        return vraag;
    }

    public void setVraag(String vraag) {
        this.vraag = vraag;
    }

    public String[] getOpties() {
        return opties;
    }

    public void setOpties(String[] opties) {
        this.opties = opties;
    }

    public String getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(String antwoord) {
        this.antwoord = antwoord;
    }

    public void generatequestions(){
        Random rand = new Random();
        int type = rand.nextInt(7);
        String[] vragenset1 = {
                "Wat is de 1-lettercode van %s?",
                "Wat is de 3-lettercode van %s?",
                "Wat is de hydrofobiciteit van %s?",
                "Wat is de lading van %s?",
                "Wat is de grootte van %s?",
                "Wat is de 3D voorkeur van %s?",
                "Wat is de zijketen van %s?"};
        String[] vragenset2 = {
                "Welk aminozuur is %s?",
                "Welk aminozuur is niet %s ?",
                "Wat is de %s van aminozuur %s?",
                "Wat is de %s van aminozuur %s?",
                "Wat is de %s van aminozuur %s?",
                "Wat is de %s van aminozuur met de volgende zijketen:"};
        for (Integer i = 0; i < this.aantal; i++){
            type = 1;
            String aminozuur = "Glycine";
            switch (type){
                case 0:
                    System.out.println(String.format(vragenset1[rand.nextInt(6)], aminozuur));
                    break;
                case 1:
                    System.out.println(String.format(vragenset2[rand.nextInt(6)], aminozuur, "asdjh"));
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        vraag vragen = new vraag(10);
        vragen.generatequestions();
    }
}


