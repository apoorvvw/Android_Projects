package com.boilers.mhacks.connections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hemil on 1/17/2015.
 */
public class ProvideData {

    public static List<String> actions = new ArrayList<String>();
    public static int Array_size = 3;
    public static String Array_actions[] = {"drive", "share", "sell"};

    public static List<String> setActions() {
        for(String act : Array_actions) {
            actions.add(act);
        }
        return actions;
    }


}
