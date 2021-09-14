package lesson1;
// вариант С
public class Exerciselb {
    public static void main(String [] args){
        // не скомпилируется, так как отсутствовала
        // точка входа в программу
        int x = 5;
        while(x > 1) {
            x = x - 1;
            if (x < 3) {
                System.out.println("маленький икс");
            }
        }
    }
}
