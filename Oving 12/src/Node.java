class Node implements Comparable<Node> {
    Node forelder;
    Node venstre;
    Node høyre;
    int verdi;
    int antall;

    public Node(Node forelder, Node venste, Node høyre, int verdi, int antall) {
        this.forelder = forelder;
        this.venstre = venste;
        this.høyre = høyre;
        this.verdi = verdi;
        this.antall = antall;
    }

    public int compareTo(Node n){
        if(this.antall < n.antall){
            return -1;
        }else if(this.antall > n.antall) {
            return 1;
        }else{
            return 0;
        }
    }
}
