package lesson2;

class DVDPlayer {
    boolean canRecord = false;
    void recordDVD(){
        System.out.println("Идет запись DVD");
    }

    public void playDVD() {
        System.out.println("Проигрывается DVD");
    }
}

public class DVDPlayerTestDrive {
    public static void main(String[] args) {

        DVDPlayer d = new DVDPlayer();
        d.canRecord = true;
        d.playDVD(); // без объявления метода не работает

        if(d.canRecord == true){
            d.recordDVD();
        }
    }
}
