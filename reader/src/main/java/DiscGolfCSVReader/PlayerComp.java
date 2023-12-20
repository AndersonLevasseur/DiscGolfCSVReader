package DiscGolfCSVReader;

import java.util.Comparator;

public class PlayerComp implements Comparator<String[]> {

    @Override
    public int compare(String[] o1, String[] o2) {
        return Integer.parseInt(o1[4]) - Integer.parseInt(o2[4]);
    }
}
