package SearchEngine;

import java.util.Scanner;

public class InputReader {

    protected static Scanner scan = new Scanner(System.in);

    private InputReader(){}

    public static String scanLine() {
        System.out.println("Type the word you want searched:");
        return scan.nextLine().toLowerCase();
    }
}
