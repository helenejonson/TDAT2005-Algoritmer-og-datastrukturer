import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class Unzip {
    int[] frekvenser = new int[256];
    ArrayList<Node> mineNoder = new ArrayList<>();
    ArrayList<Node> blad = new ArrayList<>();
    int ubrukte;
    ArrayList<Byte> byteArray = new ArrayList<>();
    ArrayList<Bitstreng> bitvei = new ArrayList<>();
    ArrayList<Byte> text = new ArrayList<>();

    public void readFile(String filnavn) {
        try {
            DataInputStream innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(filnavn)));

            for(int i = 0; i < frekvenser.length; i++){
                frekvenser[i] = innfil.readInt();
            }

            ubrukte = innfil.readInt();

            while (innfil.available() > 0) {
                byteArray.add(innfil.readByte());
            }
            System.out.println(byteArray.size());
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    public ArrayList<Node> lagNoder(){
        for(int i = 0; i < frekvenser.length; i++){
            if(frekvenser[i] != 0){
                int verdi = i;
                int antall = frekvenser[i];
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
                    //System.out.print(0);
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

    public void decode() {
        Node now;
        Node neste;
        int teller = 0;
        int byteCount = 0;
        boolean done = false;
        boolean funnet;
        byte myByte= byteArray.get(byteCount);

        while(!done){
            now = blad.get(0);
            funnet = false;
            while(!funnet){
               // System.out.println(Long.toBinaryString(myByte));
                byte shift = (byte) (myByte << teller);
              //  System.out.println(Long.toBinaryString(shift));
                int value = shift >> 7;
               // System.out.println(Long.toBinaryString(value));
                if(value == -1){
                    value = 1;
                }
                if(value == 0){
                   // System.out.print(0);
                    neste = now.venstre;
                    if(neste == null){
                        teller--;
                        byte b = (byte) now.verdi;
                        text.add(b);
                        funnet = true;
                    }else{
                        now = neste;
                    }
                }
                if(value == 1) {
                   //System.out.print(1);

                    neste = now.høyre;
                    if (neste == null) {
                        teller--;
                        byte b = (byte) now.verdi;
                        text.add(b);
                        funnet = true;
                    }else{
                        now = neste;
                    }
                }
                teller++;
                if (teller == 8) {
                    if(byteCount < byteArray.size() -1){
                        byteCount++;
                    }
                    myByte = byteArray.get(byteCount);
                    teller = 0;
                }
            }
            //System.out.println();
            if(byteCount == byteArray.size() - 1){
                if(8 - teller == ubrukte){
                    done = true;
                }else if(8 - teller < ubrukte) {
                  //  System.out.println("Feil har skjedd. Helene se her");
                }
            }
        }
    }

    public void writeFile(String filnavn){
        DataOutputStream utfil = null;
        try{
            utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(filnavn))));
            for(int i = 0; i < text.size(); i++){
                System.out.println(text.get(i));
                utfil.writeByte(text.get(i));
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
}

/*
public ArrayList<Node> lagTre(){
        for(int i = 0; i < mineNoder.size(); i++){
            blad.add(mineNoder.get(i));
        }
        Collections.sort(blad);
        while (blad.size() > 1) {
            for (int i = 0; i < blad.size(); i ++) {
                if (blad.size() - i >= 2) {
                    Node v = blad.get(0);
                    Node h = blad.get(0 + 1);
                    Node ny = new Node(null, v, h, -1, v.antall + h.antall);
                    v.forelder = ny;
                    h.forelder = ny;
                    blad.remove(v);
                    blad.remove(h);
                    blad.add(i, ny);
                } else {
                    Node v = blad.get(i);
                    Node ny = new Node(null, v, null, -1, v.antall);
                    v.forelder = ny;
                    blad.remove(v);
                    blad.add(i, ny);
                }
                Collections.sort(blad);
            }
        }
        return blad;
    }
 */