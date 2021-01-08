import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

class AStarKlient {
    public static void main(String[] args) throws Exception{
        AStar a = new AStar(	2460904, 2473695);
        BufferedReader brn = new BufferedReader(new FileReader("C:\\NTNU\\Algoritmer og datastrukturer\\Oving 13\\src\\noderNorden.txt"));
        BufferedReader brk = new BufferedReader(new FileReader("C:\\NTNU\\Algoritmer og datastrukturer\\Oving 13\\src\\kanterNorden.txt"));
        a.getNodeFromFile(brn);
        a.getKantFromFile(brk);
        System.out.println("Innlesning ferdig");
        a.settEstimering();

        Date start = new Date();
        Node n = a.dijkstra();
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

        System.out.println("A*");
        System.out.println("Det vil ta " + hours + " timer, " + minutes + " minutter, " + seconds + " sekunder");
        System.out.println("Vi går gjennom " + a.teller + " noder");
        System.out.println("Millisekund på runde:" + tid);

    }
}
