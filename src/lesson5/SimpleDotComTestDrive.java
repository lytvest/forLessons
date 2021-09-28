package lesson5;

import java.util.ArrayList;
import java.util.Arrays;

public class SimpleDotComTestDrive {
    public static void main(String[] args) {
        SimpleDotCom dot = new SimpleDotCom();
        int randomNum = (int) (Math.random() * 5);
        ArrayList<String> location = new ArrayList<>();
        location.add("" + randomNum);
        location.add("" + (randomNum + 1));
        location.add("" + (randomNum + 2));

        dot.setLocationCells(location);
        boolean isAlive = true;
        int numOfGuesses = 0;
        GameHelper gm = new GameHelper();
        while (isAlive) {
            numOfGuesses++;
            String userGuess = gm.getUserInput("Введите число :");
            String result = dot.checkYourself(userGuess);
            System.out.println(result);
            if (result.equals("Потопил")) {
                isAlive = false;
            }
        }
        System.out.println("Вы справились за " + numOfGuesses + " попытки");
    }
}

class SimpleDotCom {
    ArrayList<String> locations = new ArrayList<>();

    void setLocationCells(ArrayList<String> locations) {
        this.locations = locations;
    }

    String checkYourself(String guess) {
        int index = locations.indexOf(guess);
        if (index >= 0){
            locations.remove(index);
            if (locations.isEmpty())
                    return "Потопил";
            return "Попал";
        }
        return "Мимо";
    }
}
