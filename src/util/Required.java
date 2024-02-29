package util;

import java.util.Scanner;

public class Required {

    static Scanner sc = new Scanner(System.in);

    public static int requiredNumber(String text) {
        System.out.print(text);
        return Integer.parseInt(sc.nextLine());
    }

    public static String requiredText(String text) {
        System.out.print(text);
        return sc.nextLine();
    }
}
