import static javax.swing.JOptionPane.*;

class Node{
    String element;
    Node neste;

    public Node(String element, Node neste){
        this.element = element;
        this.neste = neste;
    }

    public String finnElement(){
        return element;
    }

    public Node finnNeste(){
        return neste;
    }
}

class Hashtabell{
    Node [] hashtabell = new Node[101];
    public int antKollisjoner = 0;

    public static int hashfunk(int k, int m){
        return k % m;
    }

    public int getAntKollisjoner(){
        return antKollisjoner;
    }

    public void setHashtabell(int value, String tekst){
        int nøkkel = hashfunk(value, hashtabell.length);
        Node ny = null;
        if(hashtabell[nøkkel] == null){
            ny = new Node(tekst, null);
        }else {
            antKollisjoner++;
            Node liggende = hashtabell[nøkkel];
            System.out.println("Krash mellom: " + liggende.finnElement() + " og " + tekst);
            ny = new Node(tekst, liggende);
        }
        hashtabell[nøkkel] = ny;
    }

    public String getTekst(int value, String tekst){
        int nøkkel = hashfunk(value, hashtabell.length);
        Node liggende = hashtabell[nøkkel];
        String tekstUt = null;
        boolean funnet = false;
        while(!funnet){
            if(liggende.finnElement().equals(tekst)){
                tekstUt = liggende.finnElement();
                funnet = true;
            }else{
                liggende = liggende.finnNeste();
            }
        }
        return tekstUt;
    }

    public String getTekst(int value){
        Node liggende = hashtabell[value];
        String tekstUt = "";
        boolean slutt = false;
        while(!slutt){
            if(liggende.finnNeste() != null){
                tekstUt += liggende.finnElement() + "\n";
                liggende = liggende.finnNeste();
            }else{
                tekstUt += liggende.finnElement();
                slutt = true;
            }
        }
        return tekstUt;
    }



    public Node[] getHashtabell() {
        return hashtabell;
    }

    public int gjorOm(String tekst){
        int value = 0;
        for (int i = 0; i < tekst.length(); i++) {
            char c =  tekst.charAt(i);
            value += c * i;
        }
        return value;
    }
}

class oppg1 {
    public static void main (String [] args){
        Hashtabell h = new Hashtabell();
        String[] liste ={ "Adolfsen Joakim Moe Andersen", "Tobias Meyer Andersson", "Vegard Bakken", "Cato Bastiansen", "Hans Petter Årvik",
        "Baugerud Magnus", "Berg Henrik Mathias", "Bergebakken Tore", "Bergquist Jon Åby", "Bjerke Thomas","Blichfeldt Victoria",
        "Brevik Magnus","Bright Brigitt Gyamfi", "Bui Aria Thuy Dung", "Carlsen Alexander", "Dalheim William", "Derouiche Emir",
        "Dokken Nikolai Roede", "Eggum Randi", "Eidsvaag Mikael Nervik", "Evangelista Ian-Angelo Roman", "Evje Kjerand", "Faksdal Stine Olava",
        "Fornes Mia", "Gjengedal Helene", "Grande Trym", "Granli Hans Kristian Olsen", "Gulaker Kristian William Macdonald", "Gultvedt Even",
        "Gundersen Kasper Vedal", "Harnes Håkon Anthonsen", "Harnes Vetle Kristaver Widnes", "Heggelund Mathias Oppedal", "Helgeland Kevin Andre",
        "Hemstad Eirik", "Hestmark Bård Sørensen", "Hollum Jørgen", "Holt-Seeland Per", "Høie Svein Jakob", "Ikin Sebastian Anthony",
        "Imran Zaim Ul-Abrar", "Jacobsen Jonas Brager", "Jarbeaux William", "Jonson Helene Yuee", "Kalleberg Espen", "Kallestad Asbjørn Fiksdal",
        "Kalstad Mikael", "Karlsen Audun", "Knutsen Yair Day", "Koch,Carl Egil", "Kopperud Pernille", "Lande Maria", "Larsen Juni Leirvik",
        "Larsen Michael Staff", "Larsson Jonas Brunvoll", "Lauvvik Torbjørn Bøe", "Legendre Patrick Øivind Helvik", "Liahagen Ole Jonas",
        "Liland Tiril Bjerkebakke", "Loennechen Jan", "Luick Andine", "Madsen Jakob Lønnerød", "Mahmood Dilawar", "Markhus Håvard Stavnås",
        "Mcculloch Maria Kleppestø", "Moe Thomas Bakken", "Mohammad Mahmoud Ibrahim", "Mohammadi Sara", "Nayef Mohammad Nayef Al",
        "Nilsen Martin Johannes", "Olsen Mathias Årstad", "Pickel Pascal", "Plahte Eirik", "Riksvold Christian", "Rønning Gaute Wierød",
        "Røstgård Kim Richard", "Schau Max Torre", "Seivaag Lasse Ivar", "Seljeseth Sabine", "Sevaldsen Marcus", "Solvoll Kenneth",
        "Steig Jørgen", "Sundfær Torstein Holmberget", "Thorkildsen Torje Dahll-Larssøn", "Tolnes Andreas", "Torbjørnsen Marius",
        "Tronstad Henrik Wanderås", "Trømborg Steffen", "Tverfjell Julie Isabelle Malmedal", "Utne Sivert", "Vanebo Kristoffer",
        "Willa Lisa", "Yogalingam Abilash", "Younger Eric", "Østmo-Sæter Lars Olsnes", "Årdal Simon"};


        for(int i = 0; i < liste.length; i++){
            String navn = liste[i];
            int value = h.gjorOm(navn);
            h.setHashtabell(value, navn);
        }

        Node [] list = h.getHashtabell();

        double last = (double) liste.length/list.length;
        System.out.println("Lastfaktor: " + last);
        System.out.println("Antall kolLisjoner: " + h.getAntKollisjoner());

    }

}
