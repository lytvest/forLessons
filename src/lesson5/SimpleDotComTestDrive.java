package lesson5;

public class SimpleDotComTestDrive {
    public static void main(String[] args) {
        SimpleDotCom dot = new SimpleDotCom();
        int [] location = {2, 3, 4};

        dot.setLocationCells(location);
        String userGuess = "1";
        String result = dot.checkYourself(userGuess);
        String testResult = "Неудача";
        if (result.equals("Попал")){
            testResult = "Пройден";
        }
        System.out.println(testResult);
    }
}

class SimpleDotCom {
    int [] locations;
    void setLocationCells(int [] locations){
        this.locations = locations;
    }

    String checkYourself(String guess){
        for(int i: locations){
            if (i == Integer.parseInt(guess))
                return "Попал";
        }
        return "Мимо";
    }
}
