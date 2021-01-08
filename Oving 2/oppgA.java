import java.util.Date;

class oppgA {
    public double funk(double x, double n){
        if(n == 0){
            return 1;
        }
        return x * funk(x, n-1);
    }

    public static void main (String[] args) {
        oppgA a = new oppgA();
        double r = 0.0;
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            r = a.funk(1.001,4371);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde:" + tid);
        System.out.println(r);
        System.out.println("");

        do {
            r = Math.pow(1.001,4371);
            runder++;
            slutt = new Date();
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde i java metode:" + tid);
    }
}
