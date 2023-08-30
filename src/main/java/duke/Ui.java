package duke;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Ui {
    Scanner uiScanner = new Scanner(System.in);

    public static void print(String thingToPrint) {
        System.out.println(thingToPrint);
    }


    public String next() {
        return uiScanner.next();
    }


    public String nextLine() {
        return uiScanner.nextLine();
    }
}