package src;

import com.sun.corba.se.impl.ior.OldPOAObjectKeyTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;
import static jdk.nashorn.internal.runtime.ScriptObject.setGlobalObjectProto;
import static jdk.nashorn.internal.runtime.ScriptObject.spillAllocationLength;

public class vraag {

    //private Integer aantal;
    private String vraag;
    private String[] opties;
    private String antwoord;
    private amminozuur[] aminozuren = lijst_aminozuur();
    private Integer hoeveelaminozuur;
    private String typeanswer = "r";
    private String typequestion = "r";


    /**
     * constructor vraag
     * klasse kan worden aangemaakt zonder opties mee te geven.
     */
    public vraag() {
    }

    /**
     * Constructor vraag
     * klasse kan worden aangemaakt met type vraag en type antwoord.
     * @param typequestion het type vraag dat wordt aangemaakt. opties: {1l, 3l, full, hydro, charge, size, threed, sidechain}
     *               "Welk aminozuur is {typequestion}"
     * @param typeanswer het type antoowrd dat woord aangemaakt. opties: {1l, 3l, full, hydro, charge, size, threed, sidechain}
     *                   {"typeaanswer", "typeanswer", "typeanswer"}
     */
    public vraag(String typequestion, String typeanswer) {
        this.typequestion = typequestion;
        this.typeanswer = typeanswer;


    }

    /**
     * het ophalen van de vraag.
     * @return vraag
     */
    public String getVraag() {
        return vraag;
    }


    /**
     * het zetten van de vraag.
     * @param vraag
     */
    public void setVraag(String vraag) {
        this.vraag = vraag;
    }


    /**
     * het opvragen van de antwoorden van een vraag.
     * @return opties, een stringlist van 3 of 4 strings.
     */
    public String[] getOpties() {
        return opties;
    }


    /**
     * het zetten van de opties.
     * @param opties moet een stringlist zijn van 3 of 4 strings.
     */
    public void setOpties(String[] opties) {
        this.opties = opties;
    }

    /**
     * het antwoord opvragen
     * @return antwoord, een string met het juiste antwoord op de vraag.
     */
    public String getAntwoord() {
        return antwoord;
    }


    /**
     * het antwoord setten.
     * @param antwoord, een string met het juiste antwoord.
     */
    public void setAntwoord(String antwoord) {
        this.antwoord = antwoord;
    }


    /**
     * aminozuur[] een lijst van alle aminozuren opvragen
     * @return aminozuren, een lijst van aminozuren.
     */
    public amminozuur[] getAminozuren() {
        return aminozuren;
    }


    /**
     * aminozuren setten
     * @param aminozuren moet een lijst met aminozuur objecten in.
     */
    public void setAminozuren(amminozuur[] aminozuren) {
        this.aminozuren = lijst_aminozuur();
    }


    /**
     * vraag de hoeveelheid aminozuren in het object.
     * @return het aantal aminozuren in het object.
     */
    public Integer getHoeveelaminozuur() {
        return hoeveelaminozuur;
    }


    /**
     * get typeanswer, hier kan je het type antwoord dat wordt gebruikt in de klasse opvragen.
     * @return string typeantwoord.
     */
    public String getTypeanswer() {
        return typeanswer;
    }


    /**
     * hier kan je het type vraag dat wordt gebruikt zetten.
     * @param typeanswer, string typeantwoord.
     */
    public void setTypeanswer(String typeanswer) {
        this.typeanswer = typeanswer;
    }


    /**
     * Hier kan je het type vraag opvragen.
     * @return typequestion, string met het type vraag.
     */
    public String getTypequestion() {
        return typequestion;
    }


    /**
     * hier kan je het type vraag zetten.
     * @param typequestion, string met het type vraag.
     */
    public void setTypequestion(String typequestion) {
        this.typequestion = typequestion;
    }


    /**
     * Deze functie neemt een aminozuur en returned de waarde die in typequestion of typeanswer wordt meegegeven.
     * als r gekozen wordt zal er een random waarde worden teruggegeven.
     * @param type, deze waarde is typequestion of typeanswer.
     * @param aminozuur het aminozuur van de vraag.
     * @return een waarde van het aminozuur object afhankelijk van type.
     */
    public String answertype(String type , amminozuur aminozuur){
        System.out.println(type);
        switch (type){
            case "r":
                return aminozuur.getRandomValue();
            case "1-lettercode":
                return  ""+aminozuur.getOnel();
            case "3-lettercode":
                return aminozuur.getThreel();
            case "Volledige naam":
                return aminozuur.getNaam();
            case "Hydrofobiciteit":
                return aminozuur.getHybrofobe();
            case "Lading":
                return aminozuur.getCharge();
            case "Grootte":
                return aminozuur.getGrootte();
            case "3D-voorkeur":
                return aminozuur.getThreed();
            case "Structuur":
                return aminozuur.getSidechain();
        }
        return "Failed to recognise answertype value (Line 168 vraag.java)";
    };


    /**
     * deze functie zet het vraag object naar een vraag waarbij de volledige naam wordt gebruikt van het aminozuur object.
     */
    public void fullnameQuestions(){
        Random rand = new Random();
        amminozuur aminozuur = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        String[] vragenset1 = {
                "Wat is de 1-lettercode van %s?",
                "Wat is de 3-lettercode van %s?",
                "Wat is de hydrofobiciteit van %s?",
                "Wat is de lading van %s?",
                "Wat is de grootte van %s?",
                "Wat is de 3D voorkeur van %s?",
                "Wat is de zijketen van %s?"};
        String[] antwoordenset1 = {""+aminozuur.getOnel(), aminozuur.getThreel(), aminozuur.getHybrofobe(),
                aminozuur.getCharge(), aminozuur.getGrootte(), aminozuur.getThreed(), aminozuur.getSidechain()};
        Integer rng =  rand.nextInt(7);

        this.vraag = String.format(vragenset1[rng], aminozuur.getNaam());
        this.antwoord = antwoordenset1[rng];
        amminozuur acid2 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        amminozuur acid3 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        while (acid2.equals(acid3) || acid2.equals(aminozuur) || acid3.equals(aminozuur)){
            acid3 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
            acid2 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        }
        this.opties = getAnswerset(rng, aminozuur, acid2, acid3);
    }


    /**
     * deze functie zet het vraag object naar een vraag waarbij de eenlettercode wordt gebruikt van het aminozuur object.
     */
    public void onelQuestions(){
        Random rand = new Random();
        amminozuur aminozuur = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        String[] vragenset1 = {
                "Wat is de volledige naam van %s?",
                "Wat is de 3-lettercode van %s?",
                "Wat is de hydrofobiciteit van %s?",
                "Wat is de lading van %s?",
                "Wat is de grootte van %s?",
                "Wat is de 3D voorkeur van %s?",
                "Wat is de zijketen van %s?"};
        String[] antwoordenset1 = {""+aminozuur.getNaam(), aminozuur.getThreel(), aminozuur.getHybrofobe(),
                aminozuur.getCharge(), aminozuur.getGrootte(), aminozuur.getThreed(), aminozuur.getSidechain()};
        Integer rng =  rand.nextInt(7);
        this.vraag = String.format(vragenset1[rng], aminozuur.getOnel());
        this.antwoord = antwoordenset1[rng];
        amminozuur acid2 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        amminozuur acid3 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        while (acid2.equals(acid3) || acid2.equals(aminozuur) || acid3.equals(aminozuur)){
            acid3 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
            acid2 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        }
        if (rng == 0){
            rng = 7;
        }
        if(this.typequestion.equals("1-lettercode") && rng == 1){

        }
        this.opties = getAnswerset(rng, aminozuur, acid2, acid3);
    }


    /**
     * deze functie zet het vraag object naar een vraag waarbij de drie-lettercode wordt gebruikt van het aminozuur object.
     */
    public void threelQuestions(){
        Random rand = new Random();
        amminozuur aminozuur = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        String[] vragenset1 = {
                "Wat is de 1-lettercode van %s?",
                "Wat is de volledige naam van %s?",
                "Wat is de hydrofobiciteit van %s?",
                "Wat is de lading van %s?",
                "Wat is de grootte van %s?",
                "Wat is de 3D voorkeur van %s?",
                "Wat is de zijketen van %s?"};
        String[] antwoordenset1 = {""+aminozuur.getOnel(), aminozuur.getNaam(), aminozuur.getHybrofobe(),
                aminozuur.getCharge(), aminozuur.getGrootte(), aminozuur.getThreed(), aminozuur.getSidechain()};
        Integer rng =  rand.nextInt(7);

        this.vraag = String.format(vragenset1[rng], aminozuur.getThreel());
        this.antwoord = antwoordenset1[rng];
        amminozuur acid2 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        amminozuur acid3 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        while (acid2.equals(acid3) || acid2.equals(aminozuur) || acid3.equals(aminozuur)){
            acid3 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
            acid2 = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        }
        while(rng == 1){
            rng = rand.nextInt(7);
        }
        this.opties = getAnswerset(rng, aminozuur, acid2, acid3);
    }

    /**
     * deze functie maakt een stringlijst met antwoorden afhankelijk van het type antwoord dat gekozen is.
     * Deze functie heeft 4 inputs:
     *  number: het meegegeven nummer voor het type antwoorden dat moet worden gegenereerd.
     *  aminozuur, het aminozuur gebruikt in de klasse
     *  acid2,acid3 random aminozuur objecten om random antwoorden te maken.
     */
    private String[] getAnswerset(Integer number, amminozuur aminozuur, amminozuur acid2, amminozuur acid3){
        String[] optieset = {"", "", ""};
        switch (number){
            case 0:
                optieset[0] = ""+aminozuur.getOnel();
                optieset[1] = ""+acid2.getOnel();
                optieset[2] = ""+acid3.getOnel();
                return shuffle(optieset);
            case 1:
                optieset[0] = aminozuur.getThreel();
                optieset[1] = acid2.getThreel();
                optieset[2] = acid3.getThreel();
                return shuffle(optieset);
            case 2:
                optieset[0] = "Hydrofoob";
                optieset[1] = "Hydrofiel";
                optieset[2] = "Neutraal";
                return shuffle(optieset);
            case 3:
                optieset[0] = "neutraal";
                optieset[1] = "positief";
                optieset[2] = "negatief";
                return shuffle(optieset);
            case 4:
                optieset[0] = "groot";
                optieset[1] = "middel";
                optieset[2] = "klein";
                return shuffle(optieset);
            case 5:
                String[] thredset = {"Turn", "Helix", "Sheet", "Geen"};
                return shuffle(thredset);
            case 6:
                optieset[0] = aminozuur.getSidechain();
                optieset[1] = acid2.getSidechain();
                optieset[2] = acid3.getSidechain();
                return shuffle(optieset);
            case 7:
                optieset[0] = aminozuur.getNaam();
                optieset[1] = acid2.getNaam();
                optieset[2] = acid3.getNaam();
                return shuffle(optieset);
        }
        return optieset;
    }


    /**
     * shuffle functie neemt een stirnglijst, zet deze door elkaar in random volgorde.
     * @param optieset
     * @return optieset dezelfde stringlijst maar dan in andere volgorde.
     */
    public String[] shuffle(String[] optieset){
        List<String> strList = Arrays.asList(optieset);
        Collections.shuffle(strList);
        optieset = strList.toArray(new String[strList.size()]);
        return optieset;
    }


    /**
     * deze functie zet het vraag object naar een vraag waarbij de hydrofobiciteit wordt gebruikt van het aminozuur object.
     */
    public void hydrofibicQuestions(){
        Random rand = new Random();
        amminozuur aminozuur = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        String[] vragenset2 = {
                "Welk aminozuur is %s?",
                "Welk aminozuur is niet %s ?"};
        Integer rng = rand.nextInt(3);
        if (rng == 0){
            amminozuur acid2 = getRNDAcidNot(String.valueOf(aminozuur.getHybrofobe()));
            amminozuur acid3 = getRNDAcidNot(String.valueOf(aminozuur.getHybrofobe()));
            while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())){
                acid2 = getRNDAcidNot(String.valueOf(aminozuur.getHybrofobe()));
                acid3 = getRNDAcidNot(String.valueOf(aminozuur.getHybrofobe()));
            }
            String[] answers = {answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2),
                    answertype(this.typeanswer, acid3)};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format(vragenset2[rng], aminozuur.getHybrofobe());
            this.antwoord = answertype(this.typeanswer, aminozuur);
        }
        else if (rng == 1){
            String[] hydro = {"Hydrofoob", "Hydrofiel", "Moderate"};
            String state = hydro[rand.nextInt(2)];
            while (aminozuur.getHybrofobe().equals(state)){
                state = hydro[rand.nextInt(2)];
            }
            amminozuur acid2 = getRNDAcidyes(state);
            amminozuur acid3 = getRNDAcidyes(state);
            while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())){
                acid2 = getRNDAcidyes(state);
                acid3 = getRNDAcidyes(state);
            }
            String[] answers = {answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2),
                    answertype(this.typeanswer, acid3)};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format(vragenset2[rng], state);
            this.antwoord = answertype(this.typeanswer, aminozuur);
        }
        else if (rng == 2){
            String[] answers = {"Hydrofoob", "Hydrofiel", "Moderate"};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format("Wat is de hydrofobiciteit van %s", answertype(this.typequestion, aminozuur));
            this.antwoord = aminozuur.getHybrofobe();
        }
    }

    /**
     * deze functie zet het vraag object naar een vraag waarbij de dlading wordt gebruikt van het aminozuur object.
     */
    public void chargeQuestions(){
        Random rand = new Random();
        amminozuur aminozuur = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        String[] vragenset2 = {
                "Welk aminozuur is %s?",
                "Welk aminozuur is niet %s ?"};
        Integer rng = rand.nextInt(3);
        if (rng == 0){
            amminozuur acid2 = getRNDAcidNot(String.valueOf(aminozuur.getCharge()));
            amminozuur acid3 = getRNDAcidNot(String.valueOf(aminozuur.getCharge()));
            while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())){
                acid2 = getRNDAcidNot(String.valueOf(aminozuur.getCharge()));
                acid3 = getRNDAcidNot(String.valueOf(aminozuur.getCharge()));
            }
            String[] answers = {answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2),
                    answertype(this.typeanswer, acid3)};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format(vragenset2[rng], aminozuur.getCharge());
            this.antwoord = answertype(this.typeanswer, acid3);
        }
        else if (rng == 1){
            String[] charge = {"neutraal", "positief", "negatief"};
            String state = charge[rand.nextInt(3)];
            while (aminozuur.getCharge().equals(state)){
                state = charge[rand.nextInt(3)];
            }
            amminozuur acid2 = getRNDAcidyes(state);
            amminozuur acid3 = getRNDAcidyes(state);
            while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())){
                acid2 = getRNDAcidyes(state);
                acid3 = getRNDAcidyes(state);
            }
            String[] answers = {answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2),
                    answertype(this.typeanswer, acid3)};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format(vragenset2[rng], state);
            this.antwoord = answertype(this.typeanswer, aminozuur);
        }
        else if (rng == 2){
            String[] answers = {"neutraal", "positief", "negatief"};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format("Wat is de lading van %s", answertype(this.typequestion, aminozuur));
            this.antwoord = aminozuur.getCharge();
        }
    }

    /**
     * deze functie zet het vraag object naar een vraag waarbij de grootte wordt gebruikt van het aminozuur object.
     */
    public void grootteQuestions(){
        Random rand = new Random();
        amminozuur aminozuur = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        String[] vragenset2 = {
                "Welk aminozuur is %s?",
                "Welk aminozuur is niet %s ?"};
        Integer rng = rand.nextInt(3);
        if (rng == 0){
            amminozuur acid2 = getRNDAcidNot(String.valueOf(aminozuur.getGrootte()));
            amminozuur acid3 = getRNDAcidNot(String.valueOf(aminozuur.getGrootte()));
            while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())){
                acid2 = getRNDAcidNot(String.valueOf(aminozuur.getGrootte()));
                acid3 = getRNDAcidNot(String.valueOf(aminozuur.getGrootte()));
            }
            String[] answers = {answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2),
                    answertype(this.typeanswer, acid3)};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format(vragenset2[rng], aminozuur.getGrootte());
            this.antwoord = aminozuur.getGrootte();
        }
        else if (rng == 1){
            String[] grootte = {"groot", "middel", "klein"};
            String state = grootte[rand.nextInt(3)];
            while (aminozuur.getGrootte().equals(state)){
                state = grootte[rand.nextInt(3)];
            }
            amminozuur acid2 = getRNDAcidyes(state);
            amminozuur acid3 = getRNDAcidyes(state);
            while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())){
                acid2 = getRNDAcidyes(state);
                acid3 = getRNDAcidyes(state);
            }
            String[] answers = {answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2),
                    answertype(this.typeanswer, acid3)};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format(vragenset2[rng], state);
            this.antwoord = answertype(this.typeanswer, aminozuur);
        }
        else if (rng == 2){
            String[] answers = {"groot", "middel", "klein"};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format("Wat is de grootte van %s", answertype(this.typequestion, aminozuur));
            this.antwoord = aminozuur.getGrootte();
        }
    }


    /**
     * deze functie zet het vraag object naar een vraag waarbij de 3d structuur wordt gebruikt van het aminozuur object.
     */
    public void threedQuestions(){
        Random rand = new Random();
        amminozuur aminozuur = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        String[] vragenset2 = {
                "Welk aminozuur heeft een voorkeur voor %s?",
                "Welk aminozuur heeft geen voorkeur voor een %s ?"};
        Integer rng = rand.nextInt(3);
        if (rng == 0){
            amminozuur acid2 = getRNDAcidNot(String.valueOf(aminozuur.getThreed()));
            amminozuur acid3 = getRNDAcidNot(String.valueOf(aminozuur.getThreed()));
            amminozuur acid4 = getRNDAcidNot(String.valueOf(aminozuur.getThreed()));
            while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())
                    || acid4.getNaam().equals(acid2.getNaam()) || acid4.getNaam().equals(aminozuur.getNaam()) || acid4.getNaam().equals(acid3.getNaam())){
                acid2 = getRNDAcidNot(String.valueOf(aminozuur.getThreed()));
                acid3 = getRNDAcidNot(String.valueOf(aminozuur.getThreed()));
                acid4 = getRNDAcidNot(String.valueOf(aminozuur.getThreed()));
            }
            String[] answers = {answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2),
                    answertype(this.typeanswer, acid3), answertype(this.typeanswer, acid4)};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format(vragenset2[rng], aminozuur.getThreed());
            this.antwoord = answertype(this.typequestion, aminozuur);
        }
        else if (rng == 1){
            String[] threed = {"Turn", "Helix", "Sheet", "Geen"};
            String state = threed[rand.nextInt(3)];
            while (aminozuur.getThreed().equals(state)){
                state = threed[rand.nextInt(3)];
            }
            amminozuur acid2 = getRNDAcidyes(state);
            amminozuur acid3 = getRNDAcidyes(state);
            amminozuur acid4 = getRNDAcidyes(state);
            while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())
                    || acid4.getNaam().equals(acid2.getNaam()) || acid4.getNaam().equals(aminozuur.getNaam()) || acid4.getNaam().equals(acid3.getNaam())){
                acid2 = getRNDAcidyes(state);
                acid3 = getRNDAcidyes(state);
                acid4 = getRNDAcidyes(state);
            }
            String[] answers = {answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2),
                    answertype(this.typeanswer, acid3), answertype(this.typeanswer, acid4)};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format(vragenset2[rng], state);
            this.antwoord = answertype(this.typeanswer, aminozuur);
        }
        else if (rng == 2){
            String[] answers = {"Turn", "Helix", "Sheet", "Geen"};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format("Wat is de 3D-structuur van %s", answertype(this.typequestion, aminozuur));
            this.antwoord = aminozuur.getThreed();
        }
    }


    /**
     * deze functie zet het vraag object naar een vraag waarbij de sidechain wordt gebruikt van het aminozuur object.
     * //todo werkt nog niet. antwoorden zijn altijd null
     */
    public void sidechainQuestions(){
        Random rand = new Random();
        amminozuur aminozuur = this.aminozuren[rand.nextInt(this.hoeveelaminozuur)];
        String[] vragenset2 = {
                "Welk aminozuur is %s?",
                "Welk aminozuur is niet %s ?"};
        Integer rng = rand.nextInt(3);
        if (rng == 0){
            amminozuur acid2 = getRNDAcidNot(String.valueOf(aminozuur.getSidechain()));
            amminozuur acid3 = getRNDAcidNot(String.valueOf(aminozuur.getSidechain()));
            while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())){
                acid2 = getRNDAcidNot(String.valueOf(aminozuur.getSidechain()));
                acid3 = getRNDAcidNot(String.valueOf(aminozuur.getSidechain()));
            }
            String[] answers = {answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2),
                    answertype(this.typeanswer, acid3)};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format(vragenset2[rng], aminozuur.getSidechain());
            this.antwoord = aminozuur.getSidechain();
        }
        else if (rng == 1){
            String[] sidechain = {"?", "??", "???"};
            String state = sidechain[rand.nextInt(3)];
            while (aminozuur.getSidechain().equals(state)){
                state = sidechain[rand.nextInt(3)];
            }
            amminozuur acid2 = getRNDAcidyes(state);
            amminozuur acid3 = getRNDAcidyes(state);
            while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())){
                acid2 = getRNDAcidyes(state);
                acid3 = getRNDAcidyes(state);
            }
            String[] answers = {answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2),
                    answertype(this.typeanswer, acid3)};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format(vragenset2[rng], state);
            this.antwoord = aminozuur.getSidechain();
        }
        else if (rng == 2){
            String[] answers = {"?", "??", "???"};
            List<String> strList = Arrays.asList(answers);
            Collections.shuffle(strList);
            this.opties = strList.toArray(new String[strList.size()]);
            this.vraag = String.format("Wat is de zijketen van %s", answertype(this.typequestion, aminozuur));
            this.antwoord = aminozuur.getSidechain();
        }
    }


    /**
     * Deze functie reset meegegeven aminozuur net zo lang tot de waarde type niet meer voorkomt in het aminozuur.
     * @param type een waarde in het aminozuur
     * @return aminozuur zonde waarde type.
     */
    private amminozuur getRNDAcidNot(String type){
        Random rand = new Random();
        amminozuur aminozuur = lijst_aminozuur()[rand.nextInt(this.hoeveelaminozuur)];
        Boolean cont = false;
        while (!cont){
            if (!aminozuur.hasValue(type)){
                cont = true;
            }
            else {
                aminozuur = lijst_aminozuur()[rand.nextInt(this.hoeveelaminozuur)];
            }

        }
        return aminozuur;
    }

    /**
     * Deze functie reset meegegeven aminozuur net zo lang tot de waarde type in het random aminozuur vooromt.
     * @param type een waarde die het aminozuur moet hebben.
     * @return aminozuur met waarde type.
     */
    private amminozuur getRNDAcidyes(String type){
        Random rand = new Random();
        amminozuur aminozuur = lijst_aminozuur()[rand.nextInt(this.hoeveelaminozuur)];
        Boolean cont = false;
        while (!cont){
            if (aminozuur.hasValue(type)){
                cont = true;
            }
            else {
                aminozuur = lijst_aminozuur()[rand.nextInt(this.hoeveelaminozuur)];
            }

        }
        return aminozuur;
    }


    /**
     * Deze functie genereerd een random vraag, als deze functie wordt aangeroepen zal random worden gekozen wat voor
     * soort vraag het object wordt.
     */
    public void generatequestions(){
        Random rand = new Random();
        int type = rand.nextInt(7);

        type = typequestionCheck(type);
        switch (type){
            case 0:
                onelQuestions();
                break;
            case 1:
                threelQuestions();
                break;
            case 2:
                fullnameQuestions();
                break;
            case 3:
                hydrofibicQuestions();
                break;
            case 4:
                chargeQuestions();
                break;
            case 5:
                grootteQuestions();
                break;
            case 6:
                threedQuestions();
                break;
            case 7:
                sidechainQuestions();
                break;
        }
    }


    /**
     * Deze functie checkt of de antwoord en vraag gekozen overeenkomen met de gekozen type vraag en antwoord.
     * zo niet zal een ander antwoord gekozen worden.
     * @param type
     * @return type
     */
    public int typequestionCheck(Integer type){
        Random rand = new Random();
        type = rand.nextInt(7);
        switch (this.typequestion){
            case "1-lettercode":
                while (type == 1 || type == 2){
                    type = rand.nextInt(7);
                }
                break;
            case "3-lettercode":
                while (type == 0 || type == 2){
                    type = rand.nextInt(7);
                }
                break;
            case "Volledige naam":
                while (type == 1 || type == 0){
                    type = rand.nextInt(7);
                }
                break;
        }
        return type;
    }

    /**
     * deze functie leest het bestand met aminozuren in en maakt een lijst van aminozuren.
     * @return
     */
    private amminozuur[] lijst_aminozuur(){
        File file = new File("").getAbsoluteFile();
        File aminoacids = new File("");
        if (System.getProperty("os.name").split("")[0] == "Windows" ){
            aminoacids = new File(file+"\\bstopopd\\src\\aminoacids.txt");
        }
        else{
            aminoacids = new File(file+"/bstopopd/src/aminoacids.txt");
        };

        Integer leng = (int) aminoacids.length();
        amminozuur[] aminozuurlist = new amminozuur[leng];
        try {
            Scanner files = new Scanner(aminoacids);
            Integer i = 0;
            while (files.hasNextLine()) {
                String line = files.nextLine();
                String[] properties = line.split(" ");
                amminozuur aminozuur = new amminozuur(properties[0], properties[1].charAt(0), properties[2],
                        properties[3],properties[4],properties[5],properties[6]);
                aminozuurlist[i] = aminozuur;
                //System.out.println(properties[0]+" "+ properties[1].charAt(0)+" "+ properties[2]+" "+
                //       properties[3]+" "+properties[4]+" "+properties[5]+" "+properties[6]);
                i++;
                this.hoeveelaminozuur = i;
            }
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        return aminozuurlist;
    }



        /*public static void main(String[] args) {
        vraag[] vragen = new vraag[40];
        for (Integer i = 0; i < 40; i++){
            vragen[i] = new vraag("3-lettercode", "1-lettercode");
            vragen[i].generatequestions();
            System.out.println(vragen[i].getVraag());
            System.out.println(vragen[i].getAntwoord());
            System.out.println(vragen[i].getOpties()[0]+ " " +vragen[i].getOpties()[1]+ " " + vragen[i].getOpties()[2] + "\n");
        }

        vragen.fullnameQuestions();
        System.out.println(vragen.getVraag());
        System.out.println(vragen.getAntwoord());
        System.out.println(vragen.getOpties()[0]+ " " +vragen.getOpties()[1]+ " " + vragen.getOpties()[2]);
    /*}*/
}


