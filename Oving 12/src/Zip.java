import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


class Zip{
    int[] frekvenser = new int[256];
    byte[] data; // all data hentet fra fil
    ArrayList<Node> mineNoder = new ArrayList<>();
    ArrayList<Node> blad = new ArrayList<>(); //arrayListe jeg forkorter til å bare holde rota
    ArrayList<Bitstreng> bitvei = new ArrayList<>();
    ArrayList<Byte> byteArray = new ArrayList<>();
    int ubrukt;

    public byte[] readFile(String filnavn) {
        try {
            DataInputStream innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(filnavn)));
            int length = innfil.available();
            data = new byte[length];
            int posisjon = 0;
            int mengde = 8;
            while (innfil.available() > 0) {
                if (innfil.available() > mengde) {
                    innfil.readFully(data, posisjon, mengde);
                } else {
                    innfil.readFully(data, posisjon, innfil.available());
                }
                posisjon += mengde;
            }
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
        return data;

    }

    public void writeFile(String filnavn){
        DataOutputStream utfil = null;
        try{
            utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(filnavn))));
            for(int i = 0; i < frekvenser.length; i++){
                utfil.writeInt(frekvenser[i]);
            }

            utfil.writeInt(ubrukt);
           // System.out.println();
            for(int i = 0; i < byteArray.size(); i++){
                utfil.writeByte(byteArray.get(i));

               // System.out.print( Integer.toBinaryString(byteArray.get(i)) + " ");
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (utfil != null) utfil.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int[] lagFrekvens() {
        for (int i = 0; i < data.length; i++) {
            System.out.println("Data på plass " + i + " " + data[i]);
            int index = data[i] & 0xFF;
            System.out.println("Data når gjort om " + index);
            frekvenser[index]++;
        }
        return frekvenser;
    }

    public ArrayList<Node> lagNoder(){
        for(int i = 0; i < frekvenser.length; i++){
            if(frekvenser[i] != 0){
                System.out.println(i);
                int verdi = i;
                int antall = frekvenser[i];
                System.out.println(antall);
                Node n = new Node(null, null, null, verdi, antall);
                mineNoder.add(n);
            }
        }
        return mineNoder;
    }

    public ArrayList<Node> lagTre(){
        for(int i = 0; i < mineNoder.size(); i++){
            blad.add(mineNoder.get(i));
        }
        Collections.sort(blad);
        while (blad.size() > 1) {
                Node v = blad.get(0);
                Node h = blad.get(1);
                Node ny = new Node(null, v, h, -1, v.antall + h.antall);
                v.forelder = ny;
                h.forelder = ny;
                blad.remove(v);
                blad.remove(h);
                blad.add(0, ny);
                Collections.sort(blad);
            }

        return blad;
    }

    public ArrayList<Bitstreng> finnBitstreng() {
        long neste;
        for (int i = 0; i < mineNoder.size(); i++) {
            boolean rot = false;
            Node her = mineNoder.get(i);
            Node forelder = her.forelder;
            int teller = 0;
            neste = 0b0;
            while (!rot) {
                if (forelder == null) {
                    rot = true;
                    break;
                } else if (her.equals(forelder.venstre)) {
                    long a = 0b0;
                    a = a << teller;
                    neste = neste | a;
                   // System.out.print(0);
                } else if (her.equals(forelder.høyre)) {
                    long b = 0b1;
                    b = b << teller;
                    neste = neste | b;
                    //System.out.print(1);
                }
                teller++;
                her = forelder;
                forelder = her.forelder;
            }
            //System.out.println();
            Bitstreng ny = new Bitstreng(neste, teller, mineNoder.get(i).verdi);
            bitvei.add(ny);
        }
        return bitvei;
    }

    public ArrayList<Byte> makeByteArray(){
        int ledig = 8;
        byte byteBlock = 0;
        for(int i = 0; i < data.length; i++){
            int tallVerd = data[i] & 0xFF; //henter første tallet i datarekken fra fil
            for(int j = 0; j < bitvei.size(); j++){
                if(tallVerd == bitvei.get(j).tallverdi){ // ser om tallverdien stemmer med det som ligger i ordboka
                    //System.out.println(n);
                    System.out.println("Se her " + bitvei.get(j).tallverdi);
                    long bit = bitvei.get(j).bit;
                   // System.out.println("Før " + Long.toBinaryString(bit));
                    int siffer = bitvei.get(j).tall;
                   // System.out.println("Siffer " + siffer);
                    //System.out.println("Før " + Long.toBinaryString(byteBlock));
                    while(siffer > 0){
                        if(ledig - siffer >= 0){
                            int shift = ledig - siffer;
                            byteBlock |= (byte)(bit << shift);
                           // System.out.println("etter når det er plass " + Long.toBinaryString(byteBlock));

                            ledig = ledig - siffer;
                            siffer = 0;
                            if(ledig == 0){
                                byteArray.add(byteBlock);
                                byteBlock = 0;
                                ledig = 8;
                            }
                        }else{
                            int rest = siffer - ledig;
                            byteBlock |= (byte)(bit >> rest);
                           // System.out.println("etter når det ikke er plass " + Long.toBinaryString(byteBlock));
                            byteArray.add(byteBlock);
                            byteBlock = 0;
                            ledig = 8;
                            siffer = rest;

                        }
                    }

                }
            }
        }
        if(byteBlock != 0){
            byteArray.add(byteBlock);
        }
        ubrukt = ledig;
        //System.out.println("Ubrukt " + ubrukt);
        return byteArray;
    }
}