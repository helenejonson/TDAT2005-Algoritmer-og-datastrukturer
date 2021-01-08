import java.util.ArrayList;

public class Node implements Comparable<Node> {
    int nodenr;
    double breddegrad;
    double lengdegrad;
    int lengde;
    Node forrige;
    boolean funnet;
    ArrayList<Kant> mineKanter = new ArrayList<>();
    int estimering;
    double cos_bredde;

    public Node(int nodenr, double breddegrad, double lengdegrad, int lengde, Node forrige, boolean funnet, int estimering){
        this.nodenr = nodenr;
        this.breddegrad = breddegrad;
        this.lengdegrad = lengdegrad;
        this.lengde = lengde;
        this.forrige = forrige;
        this.funnet = funnet;
        this.cos_bredde = Math.cos(Math.toRadians(breddegrad));
        this.estimering = estimering;
    }

    public int compareTo(Node n){
        if(this.lengde + this.estimering < n.lengde + n.estimering){
            return -1;
        }else if(this.lengde + this.estimering > n.lengde + n.estimering) {
            return 1;
        }else{
            return 0;
        }
    }

    public int avstand (Node n2) {
        double sin_bredde = Math.sin(Math.toRadians((this.breddegrad-n2.breddegrad)/2.0));
        double sin_lengde = Math.sin(Math.toRadians((this.lengdegrad-n2.lengdegrad)/2.0));
        return (int) (35285538.46153846153846153846*Math.asin(Math.sqrt(
                sin_bredde*sin_bredde+this.cos_bredde*n2.cos_bredde*sin_lengde*sin_lengde)));
    }

}
