class Node{
    double element;
    Node neste;

    public Node(double element, Node neste){
        this.element = element;
        this.neste = neste;
    }

    public double finnElement(){
        return element;
    }

    public Node finnNeste(){
        return neste;
    }

    public void settNeste(Node n){
        this.neste = n;
    }
}

class LenkaListe{
    private Node hode = null;
    private int antElementer = 0;

    public int finnAntall(){
        return antElementer;
    }

    public Node finnHode(){
        return hode;
    }

    public void settInnFremst(double verdi){
        hode = new Node(verdi, null);
        hode.settNeste(hode);
        antElementer++;
    }

    public void settInnBakerst(double verdi){
        if(hode == null){
            settInnFremst(verdi);
        }else{
            Node n = new Node(verdi, hode.finnNeste());
            hode.settNeste(n);
            antElementer++;
        }
    }

    public void fjernNode(Node n){
        Node ny = n.finnNeste().finnNeste();
        n.settNeste(ny);
    }
}
class Oppg1 {
    public static void main(String[] args){
        LenkaListe l = new LenkaListe();
        l.settInnFremst(1);
        for(int i = 101; i > 1; i--){
            l.settInnBakerst(i);

        }
        int antall = l.finnAntall();
        Node n = null;
        while(antall > 1){
            if(antall == l.finnAntall()){
                n = l.finnHode().finnNeste().finnNeste().finnNeste().finnNeste().finnNeste();
            }else{
                n = n.finnNeste().finnNeste().finnNeste().finnNeste().finnNeste().finnNeste();
            }
            l.fjernNode(n);
            antall--;
        }
        System.out.println(n.finnElement());
    }
}
