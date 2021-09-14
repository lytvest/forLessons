package lesson2;

class DrumKit{
    boolean topHat = true;
    boolean snare = true;
    void playTopHat(){
        System.out.println("динь динь ди-динь");
    }
    void playSnare(){
        System.out.println("бах бах ба-бах");
    }
}

public class DrumKitTestDrive {
    public static void main(String[] args) {
        DrumKit d = new DrumKit();
        d.playSnare();
        d.playTopHat();
        d.snare = false;
        if (d.snare == true){
            d.playSnare();
        }
    }
}
