import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;

class Stakk{
    private ArrayList<String> tab;
    private int antall;

    public Stakk(){
        tab = new ArrayList<>();
    }

    public boolean tom(){
        if(tab.size() == 0){
            return true;
        }
        return false;
    }

    public void push(String e){
        tab.add(e);
        antall++;
    }

    public void pop(){
        tab.remove(antall-1);
        antall--;
    }

    public String sjekkStack(){
        if(!tom()){
            return tab.get(antall -1);
        }
        return null;
    }
}

class Oppg3 {
    public static void main(String[] args){
        Stakk s = new Stakk();
        String tekst = "[Dette er en test { som } skal funke] ()[]";
        //String tekst = "{Dette er en test [ med }feil]";

        String[] res = new String[tekst.length()];
        for (int i = 0; i < tekst.length(); i++) {
            res[i] = Character.toString(tekst.charAt(i));
        }
        for(int i = 0; i < res.length; i++){
            if(res[i].equals("{") || res[i].equals("[") || res[i].equals("(")){
                s.push(res[i]);
            }
            else if(res[i].equals("}") || res[i].equals("]") || res[i].equals(")")){
                String stakk = s.sjekkStack();
                if(res[i].equals("}") && stakk.equals("{")){
                    s.pop();
                }else if(res[i].equals(")") && stakk.equals("(")) {
                    s.pop();
                }else if(res[i].equals("]") && stakk.equals("[")) {
                    s.pop();
                }else{
                    break;
                }
            }
        }
        System.out.println(s.tom());
    }
}