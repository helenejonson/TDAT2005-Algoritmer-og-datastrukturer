

public class Oving10 {
    public static void main (String[] args){
        Sammenlikning s = new Sammenlikning();
        String a1 = "sdf ed4d dlg";
        String a2 = "dgkj dlfk sdf";

        String b1 = "28/10/2019";
        String b2 = "28/109/92019";

        String c1 = "etldk dkjs kdfjsdf";
        String c2 = "qde dsdg";

        String d1 = "llsdfdfk?df";
        String d2 = "kdfjlsdksfk";

        System.out.println("Har tall i seg? " + s.oppgA(a1));
        System.out.println("Har tall i seg? " + s.oppgA(a2));
        System.out.println("Skriver dato riktig? " + s.oppgB(b1));
        System.out.println("Skriver dato riktig? " + s.oppgB(b2));
        System.out.println("Minst 10 tegn? " + s.oppgC(c1));
        System.out.println("Minst 10 tegn? " + s.oppgC(c2));
        System.out.println("ikke tegn? " + s.oppgD(d1));
        System.out.println("ikke tegn? " + s.oppgD(d2));
    }
}

class Sammenlikning{
    public boolean oppgA(String t){
        return t.matches(".*\\d.*");
    }

    public boolean oppgB(String t){
        return t.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d");
    }

    public boolean oppgC(String t){
        return t.matches(".{10,}"); //...........*
    }

    public boolean oppgD(String t){
        return t.matches(".*[^a-zA-Z].*");
    }
}
