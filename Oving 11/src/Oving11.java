class Automat{
    Character[] alfabet;
    String gyldig;
    String[][] tabell;

    public Automat(Character[] alfabet, String gyldig, String[][] tabell){
        this.tabell = tabell;
        this.gyldig = gyldig;
        this.alfabet = alfabet;

    }

    public boolean sjekkInput(Character[] input){
        String tilstand = "0";
        char inputCharacter = ' ';
        int indeks = 0;
        int indeksRad = 0;
        for(int i = 0; i < input.length; i++){
            inputCharacter = input[i];
            for(int j = 0; j < alfabet.length;j++){
                if(alfabet[j] == inputCharacter){
                    indeksRad = j;
                }
            }
            if(inputCharacter == '0'){
                tilstand = tabell[indeks][indeksRad];
            }else{
                tilstand = tabell[indeks][indeksRad];
            }
            indeks = Integer.parseInt(tilstand);
        }
        if(tilstand.equals(gyldig)){
            return true;
        }
        return false;
    }
}

public class Oving11 {
    public static void main (String[] args){
        Character[] alfabet = {'0','1'};
        String gyldig = "1";
        String[][] tabell = {{"0", "1"},{"1", "2"},{"2","2"}};

        Automat a = new Automat(alfabet,gyldig,tabell);
        Character[] ai = {'0', '1', '0'};
        Character[] aii = {'1', '1', '1'};
        Character[] aiii = {'0', '1', '0', '1', '1', '0'};
        Character[] aiiii = {'0', '0', '1', '0','0','0'};

        System.out.println("Oppg A");
        System.out.println("Sjekker 010: " + a.sjekkInput(ai));
        System.out.println("Sjekker 111: " + a.sjekkInput(aii));
        System.out.println("Sjekker 010110: " + a.sjekkInput(aiii));
        System.out.println("Sjekker 001000: " + a.sjekkInput(aiiii));

        Character[] alfa = {'a', 'b'};
        gyldig = "3";
        String[][] tab = {{"1", "3"},{"4", "3"},{"3","4"},{"3", "3"},{"4","4"}};
        Automat b = new Automat(alfa, gyldig, tab);

        Character[] bi = {'a', 'b', 'b', 'b'};
        Character[] bii = {'a', 'a', 'a', 'b'};
        Character[] biii = {'b', 'a', 'b', 'a', 'b'};

        System.out.println();

        System.out.println("Oppg B");
        System.out.println("Sjekker abbb: " + b.sjekkInput(bi));
        System.out.println("Sjekker aaab: " + b.sjekkInput(bii));
        System.out.println("Sjekker babab: " + b.sjekkInput(biii));

    }
}
