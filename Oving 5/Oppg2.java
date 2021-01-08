import java.util.Date;
import java.util.HashMap;
import java.util.Random;

class Hash{
    int[] hashtabell = new int[6660011];

    public void leggInn(int value){
        int h1 = value % hashtabell.length;
        int h2 = value % (hashtabell.length - 1) + 1;
        if(hashtabell[h1] == 0){
            hashtabell[h1] = value;
        }else{
            boolean funnet = false;
            int teller = 0;
            int probe = (h1 + h2) % hashtabell.length;
            while(!funnet){
                probe = (probe + h2) % hashtabell.length;
                if(hashtabell[probe] == 0){
                    hashtabell[probe] = value;
                    funnet = true;
                }

                teller++;
            }
        }
    }


}

class Oppg2 {
    public static void main(String[] args){


        int[] tabell = new int[5000000];
        for(int i = 0; i < tabell.length; i++){
            Random r = new Random();
            int tall = r.nextInt(10000000);
            tabell[i] = tall;
        }


        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;

        do {
            Hash h = new Hash();
            for(int i = 0; i < tabell.length; i++){
                h.leggInn(tabell[i]);
            }
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 10000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde min metode:" + tid);

        start = new Date();
        runder = 0;
        do {
            HashMap<Integer, Integer> tab = new HashMap<Integer, Integer>(6660011);
            for(int i = 0; i < tabell.length; i++){
                tab.put(tabell[i], 1);
            }
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 10000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde:" + tid);


    }
}

class main{
    public static void main(String[] args){
        int[] tabell = new int[5000000];
        for(int i = 0; i < tabell.length; i++){
            Random r = new Random();
            int tall = r.nextInt(10000000);
            tabell[i] = tall;
        }
        HashMap<Integer, Integer> tab = new HashMap<Integer, Integer>(6000011,1);
        for(int i = 0; i < tabell.length; i++){
            tab.put(tabell[i], 1);
        }

    }
}
