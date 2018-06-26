package src;

import com.sun.corba.se.impl.ior.OldPOAObjectKeyTemplate;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.xml.internal.fastinfoset.util.StringArray;

import javax.naming.Name;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
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
        Random rand = new Random();
        String[] typelist = new String[]{"1-lettercode", "3-lettercode", "Volledige_naam", "Grootte", "Lading", "Hydrofobiciteit"};
        String[] list = new String[]{"Hydrofobiciteit", "Lading", "Grootte", "3D-voorkeur", "Structuur"};
        String temp = typelist[rand.nextInt(typelist.length)];
        String temp2 = typelist[rand.nextInt(typelist.length)];
        Boolean go = false;
        while (go.equals(false)) {
            if (temp.trim().equals(temp2) ||
                    inlist(new String[]{temp, temp2}, list)) {
                temp = typelist[rand.nextInt(typelist.length)];
                temp2 = typelist[rand.nextInt(typelist.length)];
            } else {
                this.typequestion = temp;
                this.typeanswer = temp2;
                go = true;
            }
        }
    }

    /**
     * Constructor vraag
     * klasse kan worden aangemaakt met type vraag en type antwoord.
     *
     * @param typequestion het type vraag dat wordt aangemaakt. opties: {1l, 3l, full, hydro, charge, size, threed, sidechain}
     *                     "Welk aminozuur is {typequestion}"
     * @param typeanswer   het type antoowrd dat woord aangemaakt. opties: {1l, 3l, full, hydro, charge, size, threed, sidechain}
     *                     {"typeaanswer", "typeanswer", "typeanswer"}
     */
    public vraag(String typequestion, String typeanswer) {
        this.typequestion = typequestion;
        this.typeanswer = typeanswer;
    }

    public vraag(List<String> typequestion, List<String> typeanswer) {
        Integer size1 = typequestion.size();
        Integer size2 = typeanswer.size();
        Integer RND1 = new Random().nextInt(size1);
        Integer RND2 = new Random().nextInt(size2);
        String temp = typequestion.get(RND1);
        String temp2 = typeanswer.get(RND2);
        String[] list = new String[]{"Hydrofobiciteit", "Lading", "Grootte", "3D-voorkeur"};
        Boolean go = false;
        while (go.equals(false)) {
            if (temp.trim().equals(temp2) ||
                    inlist(new String[]{temp, temp2}, list)) {
                RND1 = new Random().nextInt(size1);
                RND2 = new Random().nextInt(size2);
                temp = typequestion.get(RND1);
                temp2 = typeanswer.get(RND2);
            } else {
                this.typequestion = temp;
                this.typeanswer = temp2;
                go = true;
            }
        }
    }

    /**
     * het ophalen van de vraag.
     *
     * @return vraag
     */
    public String getVraag() {
        return vraag;
    }


    /**
     * het zetten van de vraag.
     *
     * @param vraag
     */
    public void setVraag(String vraag) {
        this.vraag = vraag;
    }


    /**
     * het opvragen van de antwoorden van een vraag.
     *
     * @return opties, een stringlist van 3 of 4 strings.
     */
    public String[] getOpties() {
        return opties;
    }


    /**
     * het zetten van de opties.
     *
     * @param opties moet een stringlist zijn van 3 of 4 strings.
     */
    public void setOpties(String[] opties) {
        this.opties = opties;
    }

    /**
     * het antwoord opvragen
     *
     * @return antwoord, een string met het juiste antwoord op de vraag.
     */
    public String getAntwoord() {
        return antwoord;
    }


    /**
     * het antwoord setten.
     *
     * @param antwoord, een string met het juiste antwoord.
     */
    public void setAntwoord(String antwoord) {
        this.antwoord = antwoord;
    }


    /**
     * aminozuur[] een lijst van alle aminozuren opvragen
     *
     * @return aminozuren, een lijst van aminozuren.
     */
    public amminozuur[] getAminozuren() {
        return aminozuren;
    }


    /**
     * aminozuren setten
     *
     * @param aminozuren moet een lijst met aminozuur objecten in.
     */
    public void setAminozuren(amminozuur[] aminozuren) {
        this.aminozuren = lijst_aminozuur();
    }


    /**
     * vraag de hoeveelheid aminozuren in het object.
     *
     * @return het aantal aminozuren in het object.
     */
    public Integer getHoeveelaminozuur() {
        return hoeveelaminozuur;
    }


    /**
     * get typeanswer, hier kan je het type antwoord dat wordt gebruikt in de klasse opvragen.
     *
     * @return string typeantwoord.
     */
    public String getTypeanswer() {
        return typeanswer;
    }


    /**
     * hier kan je het type vraag dat wordt gebruikt zetten.
     *
     * @param typeanswer, string typeantwoord.
     */
    public void setTypeanswer(String typeanswer) {
        this.typeanswer = typeanswer;
    }


    /**
     * Hier kan je het type vraag opvragen.
     *
     * @return typequestion, string met het type vraag.
     */
    public String getTypequestion() {
        return typequestion;
    }


    /**
     * hier kan je het type vraag zetten.
     *
     * @param typequestion, string met het type vraag.
     */
    public void setTypequestion(String typequestion) {
        this.typequestion = typequestion;
    }

    private amminozuur[] lijst_aminozuur() {
        File file = new File("").getAbsoluteFile();
        File aminoacids = new File("");
        if (System.getProperty("os.name").split("")[0] == "Windows") {
            aminoacids = new File(file+"\\bstopopd\\src\\aminoacids.txt");
        } else {
            aminoacids = new File(file+"/bstopopd/src/aminoacids.txt");
        }
        ;

        Integer leng = (int) aminoacids.length();
        amminozuur[] aminozuurlist = new amminozuur[leng];
        try {
            Scanner files = new Scanner(aminoacids);
            Integer i = 0;
            while (files.hasNextLine()) {
                String line = files.nextLine();
                String[] properties = line.split(" ");
                amminozuur aminozuur = new amminozuur(properties[0], properties[1].charAt(0), properties[2],
                        properties[3], properties[4], properties[5], properties[6]);
                aminozuurlist[i] = aminozuur;
                //System.out.println(properties[0]+" "+ properties[1].charAt(0)+" "+ properties[2]+" "+
                //       properties[3]+" "+properties[4]+" "+properties[5]+" "+properties[6]);
                i++;
                this.hoeveelaminozuur = i;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return aminozuurlist;
    }

    public void generatequestions() {
        Random rnd = new Random();
        amminozuur aminozuur = this.aminozuren[rnd.nextInt(this.hoeveelaminozuur)];
        HashMap<ArrayList<String>, String> hmap = new HashMap<ArrayList<String>, String>();
        hmap.put(new ArrayList<String>(Arrays.asList("aminozuur", "1-lettercode")), "Wat is de 1-lettercode van %s?");
        hmap.put(new ArrayList<String>(Arrays.asList("aminozuur", "3-lettercode")), "Wat is de 3-lettercode van %s?");
        hmap.put(new ArrayList<String>(Arrays.asList("aminozuur", "Volledige_naam")), "Wat is de volledige_naam van %s?");
        hmap.put(new ArrayList<String>(Arrays.asList("aminozuur", "Hydrofobiciteit")), "Wat is de hydrofobiciteit van %s?");
        hmap.put(new ArrayList<String>(Arrays.asList("aminozuur", "Lading")), "Wat is de lading van %s?");
        hmap.put(new ArrayList<String>(Arrays.asList("aminozuur", "Grootte")), "Wat is de Grootte van %s?");
        hmap.put(new ArrayList<String>(Arrays.asList("aminozuur", "3D-voorkeur")), "Wat is de 3D-voorkeur van %s?");
        hmap.put(new ArrayList<String>(Arrays.asList("aminozuur", "Structuur")), "Wat is de zijketen van %s?");
        hmap.put(new ArrayList<String>(Arrays.asList("eigenschap", "eigenschap")), "Welk aminozuur is %s");
        hmap.put(new ArrayList<String>(Arrays.asList("eigenschap1", "eigenschap1")), "Welk aminozuur is niet %s");
        hmap.put(new ArrayList<String>(Arrays.asList("afbeelding", "Hydrofobiciteit")), "Wat is de hydrofobiciteit van het aminozuur met de volgende zijketen:");
        hmap.put(new ArrayList<String>(Arrays.asList("afbeelding", "Lading")), "Wat is de lading van het aminozuur met de volgende zijketen:");
        hmap.put(new ArrayList<String>(Arrays.asList("afbeelding", "Grootte")), "Wat is de grootte van het aminozuur met de volgende zijketen:");
        hmap.put(new ArrayList<String>(Arrays.asList("afbeelding", "3D-voorkeur")), "Wat is de 3D-voorkeur van het aminozuur met de volgende zijketen:");
        hmap.put(new ArrayList<String>(Arrays.asList("afbeelding", "1-lettercode")), "Wat is de 1-lettercode van het aminozuur met de volgende zijketen:");
        hmap.put(new ArrayList<String>(Arrays.asList("afbeelding", "3-lettercode")), "Wat is de 3-lettercode van het aminozuur met de volgende zijketen:");
        hmap.put(new ArrayList<String>(Arrays.asList("afbeelding", "Volledige_naam")), "Wat is de volledige_naam van het aminozuur met de volgende zijketen:");
        if (!typequestion.equals("r")) { //TODO add check for same type
            //System.out.println("\n");
            List<List<String>> keys = new ArrayList<List<String>>(hmap.keySet());
            List<List<String>> keys2 = filter_answers(keys, hmap);
            Integer rngmax = rnd.nextInt(keys2.size());
            this.vraag = String.format(hmap.get(keys2.get(rngmax)), answertype(this.typequestion, aminozuur));
            this.antwoord = answertype(this.typeanswer, aminozuur);
            String tempatype = gettemptypes()[0];
            if (this.typeanswer.trim().equals("3-lettercode") || this.typeanswer.trim().equals("1-lettercode") || this.typeanswer.trim().equals("Volledige_naam")) {
                if (!keys2.get(rngmax).get(1).equals("eigenschap")) {
                    Set<String> NameSet = new HashSet<String>();
                    NameSet.add(aminozuur.getNaam());
                    amminozuur[] acidlist = new amminozuur[4];
                    acidlist[0] = aminozuur;
                    while (NameSet.size() < 4) {
                        amminozuur newacid = this.aminozuren[rnd.nextInt(this.hoeveelaminozuur)];
                        if (!NameSet.contains(newacid.getNaam())) {
                            NameSet.add(newacid.getNaam());
                            acidlist[NameSet.size() - 1] = newacid;
                        }
                    }
                    String[] options = new String[]{answertype(this.typeanswer, acidlist[0]), answertype(this.typeanswer, acidlist[1]), answertype(this.typeanswer, acidlist[2])};
                    List<String> strList = Arrays.asList(options);
                    Collections.shuffle(strList);
                    this.opties = strList.toArray(new String[strList.size()]);
                } else if (keys2.get(rngmax).get(0).equals("eigenschap")) {
                    amminozuur acid2 = getRNDAcidNot(String.valueOf(aminozuur.getThreed()));
                    amminozuur acid3 = getRNDAcidNot(String.valueOf(aminozuur.getThreed()));
                    amminozuur acid4 = getRNDAcidNot(String.valueOf(aminozuur.getThreed()));
                    while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())
                            || acid4.getNaam().equals(acid2.getNaam()) || acid4.getNaam().equals(aminozuur.getNaam()) || acid4.getNaam().equals(acid3.getNaam())) {
                        acid2 = getRNDAcidNot(String.valueOf(answertype(this.typeanswer, aminozuur)));
                        acid3 = getRNDAcidNot(String.valueOf(answertype(this.typeanswer, aminozuur)));
                        acid4 = getRNDAcidNot(String.valueOf(answertype(this.typeanswer, aminozuur)));
                    }
                    String[] options = new String[]{answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2), answertype(this.typeanswer, acid3)};
                    List<String> strList = Arrays.asList(options);
                    Collections.shuffle(strList);
                    this.opties = strList.toArray(new String[strList.size()]);
                } else if (keys2.get(rngmax).get(0).equals("eigenschap1")) {
                    amminozuur acid2 = getRNDAcidyes(String.valueOf(aminozuur.getThreed()));
                    amminozuur acid3 = getRNDAcidyes(String.valueOf(aminozuur.getThreed()));
                    amminozuur acid4 = getRNDAcidyes(String.valueOf(aminozuur.getThreed()));
                    while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())
                            || acid4.getNaam().equals(acid2.getNaam()) || acid4.getNaam().equals(aminozuur.getNaam()) || acid4.getNaam().equals(acid3.getNaam())) {
                        acid2 = getRNDAcidyes(String.valueOf(answertype(this.typeanswer, aminozuur)));
                        acid3 = getRNDAcidyes(String.valueOf(answertype(this.typeanswer, aminozuur)));
                        acid4 = getRNDAcidyes(String.valueOf(answertype(this.typeanswer, aminozuur)));
                    }
                    String[] options = new String[]{answertype(this.typeanswer, aminozuur), answertype(this.typeanswer, acid2), answertype(this.typeanswer, acid3)};
                    List<String> strList = Arrays.asList(options);
                    Collections.shuffle(strList);
                    this.opties = strList.toArray(new String[strList.size()]);
                }
            } else if (this.typeanswer.trim().equals("Hydrofobiciteit")) {
                if (!keys2.get(rngmax).get(0).equals("eigenschap")) {
                    String[] options = new String[]{"Moderate", "Hydrofiel", "Hydrofoob"};
                    List<String> strList = Arrays.asList(options);
                    Collections.shuffle(strList);
                    this.opties = strList.toArray(new String[strList.size()]);
                }
                //System.out.println(hmap.get(keys2.get(rngmax)));
            } else if (this.typeanswer.trim().equals("Grootte")) {
                String[] options = new String[]{"groot", "middel", "klein"};
                List<String> strList = Arrays.asList(options);
                Collections.shuffle(strList);
                this.opties = strList.toArray(new String[strList.size()]);
            } else if (this.typeanswer.trim().equals("Lading")) {
                String[] options = new String[]{"neutraal", "positief", "negatief"};
                List<String> strList = Arrays.asList(options);
                Collections.shuffle(strList);
                this.opties = strList.toArray(new String[strList.size()]);
            } else if (this.typeanswer.trim().equals("3D-voorkeur")) {
                String[] options = new String[]{"Turn", "Helix", "Sheet", "Geen"};
                List<String> strList = Arrays.asList(options);
                Collections.shuffle(strList);
                this.opties = strList.toArray(new String[strList.size()]);
            } else if (this.typeanswer.trim().equals("Structuur")) {
                this.setAntwoord(aminozuur.getNaam());
                amminozuur acid2 = getRNDAcidNot(aminozuur.getNaam());
                amminozuur acid3 = getRNDAcidNot(aminozuur.getNaam());
                amminozuur acid4 = getRNDAcidNot(aminozuur.getNaam());
                while (acid2.getNaam().equals(acid3.getNaam()) || acid2.getNaam().equals(aminozuur.getNaam()) || acid3.getNaam().equals(aminozuur.getNaam())
                        || acid4.getNaam().equals(acid2.getNaam()) || acid4.getNaam().equals(aminozuur.getNaam()) || acid4.getNaam().equals(acid3.getNaam())) {
                    acid2 = getRNDAcidNot(aminozuur.getNaam());
                    acid3 = getRNDAcidNot(aminozuur.getNaam());
                    acid4 = getRNDAcidNot(aminozuur.getNaam());
                }
                String[] options = new String[]{aminozuur.getNaam(), acid2.getNaam(), acid3.getNaam()};
                List<String> strList = Arrays.asList(options);
                Collections.shuffle(strList);
                this.opties = strList.toArray(new String[strList.size()]);
            } else {
                this.opties = new String[]{"null", "null", "null"};
            }
        }
    }


    public List<List<String>> filter_answers(List<List<String>> keys, HashMap<ArrayList<String>, String> hmap) {
        String tempatype = gettemptypes()[0];
        String tempbtype = gettemptypes()[1];
        List<List<String>> newlist = new ArrayList<List<String>>();
        for (List<String> key : keys) {
            if (key.get(1).contains(tempatype) || key.get(0).equals(tempatype) ||
                    key.get(1).equals(this.typequestion.trim()) || key.get(1).contains(tempbtype) || key.get(0).contains(tempbtype)) {
                newlist.add(key);
            }
        }
        List<List<String>> newlist2 = new ArrayList<List<String>>();
        for (List<String> key : newlist) {
            if ((key.get(0).contains(tempbtype) || key.get(1).equals(this.typequestion.trim()))) {
                if (this.typequestion.trim().equals("Structuur")) {
                    if (this.typeanswer.trim().equals(key.get(1)) || key.get(1).equals("Structuur")) {
                        if (key.get(1).equals(this.typeanswer.trim())) {
                            newlist2.add(key);
                        }
                    }
                } else {
                    if ((key.get(1).equals(this.typeanswer.trim()) || key.get(1).contains(tempbtype)) && (!tempatype.equals(tempbtype))) {
                        newlist2.add(key);
                    }
                }
            }
        }
        return newlist2;
    }

    public String[] gettemptypes() {
        String tempatype = "";
        String tempbtype = "";
        if (this.typeanswer.trim().equals("Hydrofobiciteit") || this.typeanswer.trim().equals("Lading") ||
                this.typeanswer.trim().equals("Grootte") || this.typeanswer.trim().equals("3D-voorkeur")) {
            tempatype = "eigenschap";
        } else if (this.typequestion.trim().equals("Structuur")) {
            tempatype = "afbeelding";
        } else {
            tempatype = "{x}";
        }
        if (this.typequestion.trim().equals("Structuur")) {
            tempbtype = "afbeelding";
        } else if (this.typequestion.trim().equals("Hydrofobiciteit") || this.typequestion.trim().equals("Lading") ||
                this.typequestion.trim().equals("Grootte") || this.typequestion.trim().equals("3D-voorkeur")) {
            tempbtype = "eigenschap";
        } else {
            tempbtype = "aminozuur";
        }
        return new String[]{tempatype, tempbtype};
    }

    /**
     * //     * Deze functie neemt een aminozuur en returned de waarde die in typequestion of typeanswer wordt meegegeven.
     * //     * als r gekozen wordt zal er een random waarde worden teruggegeven.
     * //     * @param type, deze waarde is typequestion of typeanswer.
     * //     * @param aminozuur het aminozuur van de vraag.
     * //     * @return een waarde van het aminozuur object afhankelijk van type.
     * //
     */
    public String answertype(String type, amminozuur aminozuur) {
        switch (type.trim()) {
            case "r":
                return aminozuur.getRandomValue();
            case "1-lettercode":
                return "" + aminozuur.getOnel();
            case "3-lettercode":
                return aminozuur.getThreel();
            case "Volledige_naam":
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
        return "Failed to recognise answertype value (Line 192 vraag.java) type:" + type;
    }

    ;

    private Boolean inlist(String string, String[] stringlist) {
        Boolean value = false;
        for (String item : stringlist) {
            if (item.equals(string)) {
                value = true;
            }
        }
        return value;
    }

    private Boolean inlist(String[] string, String[] stringlist) {
        Boolean value = false;
        Integer match = 0;
        for (String item : stringlist) {
            for (Integer i = 0; i < string.length; i++) {
                if (item.equals(string[i])) {
                    match += 1;
                }
            }
        }
        if (match >= 2) {
            value = true;
        }
        return value;
    }

    /**
     * Deze functie reset meegegeven aminozuur net zo lang tot de waarde type niet meer voorkomt in het aminozuur.
     *
     * @param type een waarde in het aminozuur
     * @return aminozuur zonde waarde type.
     */
    private amminozuur getRNDAcidNot(String type) {
        Random rand = new Random();
        amminozuur aminozuur = lijst_aminozuur()[rand.nextInt(this.hoeveelaminozuur)];
        Boolean cont = false;
        while (!cont) {
            if (!aminozuur.hasValue(type)) {
                cont = true;
            } else {
                aminozuur = lijst_aminozuur()[rand.nextInt(this.hoeveelaminozuur)];
            }

        }
        return aminozuur;
    }

    /**
     * Deze functie reset meegegeven aminozuur net zo lang tot de waarde type in het random aminozuur vooromt.
     *
     * @param type een waarde die het aminozuur moet hebben.
     * @return aminozuur met waarde type.
     */
    private amminozuur getRNDAcidyes(String type) {
        Random rand = new Random();
        amminozuur aminozuur = lijst_aminozuur()[rand.nextInt(this.hoeveelaminozuur)];
        Boolean cont = false;
        while (!cont) {
            if (aminozuur.hasValue(type)) {
                cont = true;
            } else {
                aminozuur = lijst_aminozuur()[rand.nextInt(this.hoeveelaminozuur)];
            }

        }
        return aminozuur;
    }

    /*public static void main(String[] args) {
        List<String> Stringlist = new ArrayList<String>();
        Stringlist.add("Hydrofobiciteit");
        Stringlist.add("Volledige_naam");
        Stringlist.add("3-lettercode");
        Stringlist.add("1-lettercode");
        Stringlist.add("Structuur");
        Stringlist.add("Lading");
        Stringlist.add("Grootte");
        vraag[] vragen = new vraag[40];
        for (Integer i = 0; i < 40; i++) {
            vragen[i] = new vraag(Stringlist, Stringlist);
            vragen[i].maakvraag();
            System.out.println(vragen[i].getVraag());
            System.out.println(vragen[i].getAntwoord());
            for (String optie : vragen[i].getOpties()) {
                System.out.println("Optie: " + optie);
            }
        }
        //vraag vragen = new vraag("Grootte", "Hydrofobiciteit");
        //vragen.maakvraag();
    }*/
}
