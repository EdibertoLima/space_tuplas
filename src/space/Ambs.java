package space;

import java.util.ArrayList;
import java.util.List;
import net.jini.core.entry.Entry;

public class Ambs implements Entry {

    public String name;
    public List<String> user;
    public List<String> disp;
    //public String[] array=new String[10];;
    //public String idArray;

    public Ambs() {
        //dips = "0";
        //user = "0";
    }

public int getIdUser(String name){
        int n=-1;
        for (int i = 0; i < user.size(); i++) {
            if(user.get(i).equals(name)){
                n=i;
            }
        }
        return n;
    }
public int getIdDisp(String name){
        int n=-1;
        for (int i = 0; i < disp.size(); i++) {
            if(disp.get(i).equals(name)){
                n=i;
            }
        }
        return n;
    }
//
//    public void setUser() {
//        int id = Integer.parseInt(user);
//        user = Integer.toString(id + 1);
//
//    }
//    public void removeUser() {
//        int id = Integer.parseInt(user);
//        user = Integer.toString(id - 1);
//
//    }
//    public int getdip() {
//        int id = Integer.parseInt(dips);
//
//        return id;
//
//    }
//
//    public void setdip() {
//        int id = Integer.parseInt(dips);
//        dips = Integer.toString(id + 1);
//    }
//    public void removedip() {
//        int id = Integer.parseInt(dips);
//        dips = Integer.toString(id + 1);
//    }

}
