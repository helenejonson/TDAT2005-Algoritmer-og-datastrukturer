import java.util.Date;

class oppg2 {

    public double funk(double x, double n){
        double res = 0.0;
        if(n == 0){
            return 1;
        }else if(n%2 == 0.0){
            return funk(x*x, n/2);
        }else if(n%2 != 0.0) {
            return x * funk(x * x, (n - 1) / 2);
        }
        return -1;
    }
    public static void main(String[] args){
        oppg2 b = new oppg2();
        double r = 0.0;
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            r = b.funk(1.001,5000);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde:" + tid);
        System.out.println(r);
        System.out.println("");
        runder = 0;
        start = new Date();
        do {
        r = Math.pow(1.001,5000);
        runder++;
        slutt = new Date();
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double)
                (slutt.getTime()-start.getTime()) / runder;
        System.out.println("Millisekund pr. runde i java metode:" + tid);
    }
}
