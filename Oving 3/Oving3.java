import java.util.Date;
import java.util.Random;

public class Oving3 {
    public void quicksort(int[] t, int v, int h){
        int deler = 1000;
        if(h-v > deler){
            int delepos = splitt(t,v,h);
            quicksort(t,v,delepos - 1);
            quicksort(t,delepos + 1, h);
        }else{
            insettingsort(t,v,h);
        }
    }

    public int splitt(int[] t, int v, int h){
        int iv, ih;
        int m = median3sort(t,v,h);
        int dv = t[m];
        bytt(t,m,h-1);
        for(iv = v, ih = h-1;;) {
            while (t[++iv] < dv) ;
            while (t[--ih] > dv) ;
            if (iv >= ih) {
                break;
            }
            bytt(t, iv, ih);
        }
        bytt(t,iv,h-1);
        return iv;
    }

    public int median3sort(int[] t, int v, int h){
        int m = (v+h) / 2;
        if(t[v] > t[m]){
            bytt(t,v,m);
        }
        if(t[m] > t[h]){
            bytt(t,m,h);
            if(t[v] > t[m]){
                bytt(t,v,m);
            }
        }
        return m;
    }

    public void bytt(int[] t, int v, int h){
        int hjelp = t[v];
        t[v] = t[h];
        t[h] = hjelp;
    }

    public void insettingsort(int[] t, int v, int h){
        for(int j = v + 1; j < h + 1; j++){
            int bytt = t[j];
            int i = j - 1;
            while(i >= v && t[i] > bytt){
                t[i + 1] = t[i];
                --i;
            }
            t[i + 1] = bytt;
        }
    }

    public static void main(String[] args){
        Oving3 a = new Oving3();
        int [] b = new int [1000000];
        int[] t = new int[b.length];
        Random r = new Random();
        int sum = 0;
        for(int i = 0; i < b.length; i++){
            int s = r.nextInt(100) - 50;
            b[i] = s;
            sum += s;
        }

        System.out.println("Sum før: " + sum);

        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            for(int i = 0; i < b.length; i++){
                t[i] = b[i];
            }
            int v = 0;
            int h = t.length - 1;
            a.quicksort(t,v,h);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde:" + tid);
        boolean sjekk = true;
        sum = 0;
        for(int i = 0; i < t.length; i++){
            if(i != t.length-1){
                if(t[i+1] < t[i]) {
                    sjekk = false;
                }
            }
            sum += t[i];
        }
        System.out.println("Alle tall står i rekkefølge: " + sjekk);
        System.out.println("Sum etter: " + sum);
    }
}
