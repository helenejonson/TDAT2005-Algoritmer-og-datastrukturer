import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class AStar {
    int N, K;
    Node[] node;
    Kant[] kant;
    ArrayList<Node> funnet = new ArrayList<>();
    Node start;
    int startId;
    int sluttId;
    Node slutt;
    PriorityQueue<Node> pq = new PriorityQueue<>();
    int teller = 0;

    public AStar(int startId, int sluttId){
        this.startId = startId;
        this.sluttId = sluttId;
    }

    public void getNodeFromFile(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int nodeNr = Integer.parseInt(st.nextToken());
            double breddegrad = Double.parseDouble(st.nextToken());
            double lengdegrad = Double.parseDouble(st.nextToken());
            int lengde = 2000000000;
            node[i] = new Node(nodeNr, breddegrad, lengdegrad, lengde, null, false, 0);
            if(i == startId){
                start = node[i];
            }
            if(i == sluttId){
                slutt = node[i];
            }
        }
    }



    public void getKantFromFile(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        kant = new Kant[K];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int franode = Integer.parseInt(st.nextToken());
            int tilnode = Integer.parseInt(st.nextToken());
            int kjoretid = Integer.parseInt(st.nextToken());
            int veilengde = Integer.parseInt(st.nextToken());
            int fart = Integer.parseInt(st.nextToken());
            kant[i] = new Kant(franode, tilnode, kjoretid, veilengde, fart);
            node[franode].mineKanter.add(kant[i]);
        }
    }


    public Node dijkstra(){
        start.lengde = 0;
        boolean finnSlutt = false;
        pq.add(start);
        Node n = null;


        while(!finnSlutt){
            n = pq.poll();
            if(n.equals(slutt)){
                finnSlutt = true;
                break;
            }
            n.funnet = true;
            naboAvstand(n);
        }

        return n;
    }

    public void naboAvstand(Node n){
        ArrayList<Kant> kanter = n.mineKanter;
        for(int i = 0; i < kanter.size(); i++){
            Kant k = kanter.get(i);
            Node nyNabo = node[k.tilnode];
            if(!nyNabo.funnet){
                int sattDist = nyNabo.lengde;
                int lengde = n.lengde + k.kjoretid;
                if(lengde < sattDist){
                    nyNabo.lengde = lengde;
                    nyNabo.forrige = node[n.nodenr];
                    teller++;
                }
                pq.remove(nyNabo);
                pq.add(nyNabo);
            }
        }

    }

    public void settEstimering(){
        for(int i = 0; i < node.length; i++){
            int est = node[i].avstand(slutt);
            node[i].estimering = est;
        }
    }

}
