package laba1;

import java.util.ArrayList;

public class DotCom {
    public ArrayList<String> locationCells;
    private String name;

    public void setLocationCells(ArrayList<String> cells){
        locationCells = cells;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String checkYourself(String guess) {
        int index = locationCells.indexOf(guess);
        if (index >= 0){
            locationCells.remove(index);
            if (locationCells.isEmpty()){
                System.out.println("Ой! Вы потопили " + name + " :(");
                return "Потопил";
            }
            return "Попал";
        }
        return "Мимо";
    }

}
