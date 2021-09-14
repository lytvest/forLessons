package lesson2;

class Books{
    String title;
    String author;
}

public class BooksTestDrive {
    public static void main(String[] args) {
        Books [] myBooks = new Books[3];
        int x = 0;

        // нужно создать обекты массива
        myBooks[0] = new Books();
        myBooks[1] = new Books();
        myBooks[2] = new Books();

        myBooks[0].title = "Плоды Java";
        myBooks[1].title = "Java Гетсби";
        myBooks[2].title = "Сборник рецептов по Java";

        myBooks[0].author = "Боб";
        myBooks[1].author = "Сью";
        myBooks[2].author = "Ян";

        while (x < 3) {
            System.out.print(myBooks[x].title);
            System.out.print(", автор ");
            System.out.println(myBooks[x].author);
            x = x + 1;
        }
    }
}
