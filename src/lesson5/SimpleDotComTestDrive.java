package lesson5;

public class SimpleDotComTestDrive {
    public static void main(String[] args) {
        SimpleDotCom dot = new SimpleDotCom();
        int [] location = {2, 3, 4};
        dot.setLocationCells(location);
        boolean isAlive = true;
        int numOfGuesses = 0;
        GameHelper gm = new GameHelper();
        while (isAlive) {
            numOfGuesses++;
            String userGuess = gm.getUserInput("Введите число :");
            String result = dot.checkYourself(userGuess);
            System.out.println(result);
            if(result.equals("Потопил")){
                isAlive = false;
            }
        }
        System.out.println("Вы справились за " + numOfGuesses + " попытки");
    }
}

class SimpleDotCom {
    int [] locations;
    int numOfHints = 0;
    void setLocationCells(int [] locations){
        this.locations = locations;
    }

    String checkYourself(String guess){
        for(int i: locations){
            if (i == Integer.parseInt(guess)) {
                numOfHints++;
                if (numOfHints == locations.length)
                    return "Потопил";
                return "Попал";
            }
        }
        return "Мимо";
    }
}
