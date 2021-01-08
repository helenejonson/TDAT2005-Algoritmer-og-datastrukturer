import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

class DijkstraKlient {
    public static void main(String[] args) throws Exception {
        Dijkstras d = new Dijkstras(30236, 14416);
        BufferedReader brn = new BufferedReader(new FileReader("C:\\NTNU\\Algoritmer og datastrukturer\\Oving 13\\src\\noderNorden.txt"));
        BufferedReader brk = new BufferedReader(new FileReader("C:\\NTNU\\Algoritmer og datastrukturer\\Oving 13\\src\\kanterNorden.txt"));
        d.getNodeFromFile(brn);
        d.getKantFromFile(brk);

        Date start = new Date();
       Node n = d.dijkstra();
       Date slutt = new Date();
        double tid = (double)
                (slutt.getTime()-start.getTime());

        int lengde = n.lengde;
        int tall = 1;
        while(n.forrige != null){
            System.out.println(n.breddegrad + ", " + n.lengdegrad);
            n = n.forrige;
            tall++;
        }
        System.out.println(tall);
        int sec = (lengde /100);
        int hours = sec / 3600;
        int secondsLeft = sec - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        System.out.println("Dijkstra");
        System.out.println(lengde);
        System.out.println("Det vil ta " + hours + " timer, " + minutes + " minutter, " + seconds + " sekunder");
        System.out.println("Vi går gjennom " + d.teller + " noder");
        System.out.println("Millisekund på runde:" + tid);
    }
}


