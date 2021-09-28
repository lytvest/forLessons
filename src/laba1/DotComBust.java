package laba1;

import java.util.ArrayList;

public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<>();
    private int numOfGuesses = 0;

    private void setupGame(){
        DotCom one = new DotCom();
        one.setName("Flagman");
        DotCom two = new DotCom();
        two.setName("Karlson");
        DotCom tree = new DotCom();
        tree.setName("Pobeda");
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(tree);

        System.out.println("Ваша цель потопить три корабля как можно скорее!");

        for(DotCom dotCom: dotComsList){
            ArrayList<String> newLocation = helper.placeDotCom((int)(2 + Math.random() * 3));
            dotCom.setLocationCells(newLocation);
        }
    }
    private void startPlaying(){
        while (!dotComsList.isEmpty()){
            String userGuess = helper.getUserInput("Сделайте ход:");
            checkUserGuess(userGuess);
        }
        finishGame();
    }


    private void finishGame() {
        System.out.println("Ура! Вы потопили все корабли!");
        if(numOfGuesses <= 15){
            System.out.println("Вы справились за " + numOfGuesses + " попыток.");
            System.out.println("Ваши корабли выжили!");
        } else {
            System.out.println("Вы справились за " + numOfGuesses + " из 15 попыток.");
            System.out.println("Ваши корабли погибли!");
        }

    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Мимо";
        for (DotCom dotCom: dotComsList){
            result = dotCom.checkYourself(userGuess);
            if(result.equals("Попал"))
                break;
            if (result.equals("Потопил")){
                dotComsList.remove(dotCom);
                break;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setupGame();
        game.startPlaying();
    }

}
