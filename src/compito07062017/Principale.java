package compito07062017;

import java.util.Scanner;

/**
 * Created by Michele on 07/06/2017.
 */
public class Principale {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m = 0;
        do {
            System.out.print("Inserire num of Crawlers: ");
            n = sc.nextInt();
            System.out.println();
            System.out.print("Inserire num of Parsers: ");
            m = sc.nextInt();
            System.out.println();
        } while (n < 0 || m < 0);
        Store st = new Store();
        try {
            st.addURL(1, "https://www.ettorecelozzi");
        } catch (InterruptedException e) {
        }

        Crawler[] crw = new Crawler[n];
        for (int i = 0; i < crw.length; i++) {
            crw[i] = new Crawler(st);
            crw[i].start();
        }
        Parser[] par = new Parser[m];
        for (int i = 0; i < par.length; i++) {
            par[i] = new Parser(st);
            par[i].start();
        }
        for (int i = 0; i < 60; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("Crawler: " + st.getCrawler() + " Parser: " + st.getParser());
            } catch (InterruptedException e) {
            }
        }
        for (int i = 0; i < crw.length; i++) {
            crw[i].interrupt();
        }
        for (int i = 0; i < par.length; i++) {
            par[i].interrupt();
        }
    }
}
