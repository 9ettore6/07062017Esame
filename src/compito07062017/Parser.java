package compito07062017;

/**
 * Created by Michele on 07/06/2017.
 */
public class Parser extends Thread {
    private Store st;

    public Parser(Store st) {
        this.st = st;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String s = st.prendiHTML();
                System.out.println("Preso HTML: " + s + " effettuo parsing...");
                sleep(2000);
                int v = (int) (1 + Math.random() * 10); // con 0 mi va in stallo
                st.addURL(v, "Urls");
                st.incParser(v);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " Parser interrotto!");
        }
    }
}
