/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.List;
import java.util.Locale;
import net.jini.core.entry.Entry;

/**
 *
 * @author ediberto
 */
public class ListAmbs implements Entry {

    public List<String> ListNames;
    //private String name = "amb";

    public String getName() {
        if (ListNames == null) {
            return "amb" + "1";
        }
        return "amb" + Integer.toString(ListNames.size() + 1);

    }

    public int getID(String name) {
        int n = -1;
        for (int i = 0; i < ListNames.size(); i++) {
            if (ListNames.get(i).equals(name)) {
                n = i;
            }
        }
        return n;
    }
}
