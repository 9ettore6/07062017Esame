package compito07062017;

/**
 * Created by Michele on 07/06/2017.
 */
public class Crawler extends Thread {
    private Store st;

    public Crawler(Store st) {
        this.st = st;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String s = st.prendiURL();
                System.out.println("Preso Url: " + s + ".it faccio il download...");
                sleep(2000);
                st.insHTML(s);
                st.incCrawler();
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " Crawler interrotto!");
        }
    }
}
