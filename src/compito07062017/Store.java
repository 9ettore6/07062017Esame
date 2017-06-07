package compito07062017;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by Michele on 07/06/2017.
 */
public class Store {
    private Semaphore urlSt;
    private Semaphore mutex;
    private Semaphore mutexListD;
    //private Semaphore mutexList;
    private Semaphore mutexListU;
    private Semaphore docSt;
    private ArrayList urlStore;
    private ArrayList docStore;
    private int countC, countP;

    public Store() {
        urlSt = new Semaphore(0);
        mutex = new Semaphore(1);
        urlStore = new ArrayList();
        docStore = new ArrayList();
        //mutexList = new Semaphore(1);
        mutexListD = new Semaphore(1);
        mutexListU = new Semaphore(1);
        docSt = new Semaphore(0);
    }

    public void setCrawler() {
        countC = 0;
    }

    public void setParser() {
        countP = 0;
    }

    public int getCrawler() {
        return countC;
    }

    public int getParser() {
        return countP;
    }

    public String prendiURL() throws InterruptedException {
        urlSt.acquire();
        mutexListU.acquire();
        String s = (String) urlStore.remove(0);
        mutexListU.release();
        return s;
    }

    public void insHTML(String s) throws InterruptedException {
        mutexListD.acquire();
        docSt.release();
        docStore.add(s);
        mutexListD.release();
    }

    public void incCrawler() throws InterruptedException {
        mutex.acquire();
        countC++;
        mutex.release();
    }

    //Parser

    public String prendiHTML() throws InterruptedException {
        docSt.acquire();
        mutexListD.acquire();
        String s = (String) docStore.remove(0);
        mutexListD.release();
        return s;
    }


    public void addURL(int v, String s) throws InterruptedException {
        mutexListU.acquire();
        urlSt.release(v);
        for (int i = 0; i < v; i++) {
            urlStore.add(s);
        }
        mutexListU.release();
    }

    public void incParser(int v) throws InterruptedException {
        mutex.acquire();
        countP += v;
        mutex.release();
    }
}
