import java.util.ArrayList;

class UnzipKlient {
    public static void main(String[] args){
        Unzip z = new Unzip();

        z.readFile("C:\\NTNU\\Algoritmer og datastrukturer\\Oving 12\\src\\outFile");
        ArrayList<Node> n = z.lagNoder(); //Alle nodene sortert
        System.out.println(n.size());
        ArrayList<Node> t = z.lagTre(); //
        ArrayList<Bitstreng> bv = z.finnBitstreng();
        System.out.println(bv.size());
        z.decode();
        System.out.println();
        System.out.println("ferdig med utskrift");
        z.writeFile("C:\\NTNU\\Algoritmer og datastrukturer\\Oving 12\\src\\newFile");

    }
}
