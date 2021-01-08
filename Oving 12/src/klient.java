import java.util.ArrayList;

class klient {
    public static void main(String[] args){
        Zip z = new Zip();
        byte[] b = z.readFile("C:\\NTNU\\Algoritmer og datastrukturer\\Oving 12\\src\\opg12.txt"); // Alle tegn i filen
        int[] f = z.lagFrekvens(); // alle tegnene satt inn i en frekvenstabell
        ArrayList<Node> n = z.lagNoder(); //Alle nodene sortert
        ArrayList<Node> t = z.lagTre(); //
        ArrayList<Bitstreng> bv = z.finnBitstreng();

        for(int i = 0; i < n.size(); i++){
            System.out.println(" heu " + n.get(i).verdi);
        }

        ArrayList<Byte> by = z.makeByteArray();
        /*
        for(int i = 0; i < by.size(); i++){
            System.out.print(Integer.toBinaryString(by.get(i)) + " ");
        }

         */

        z.writeFile("C:\\NTNU\\Algoritmer og datastrukturer\\Oving 12\\src\\outFile");

    }
}
