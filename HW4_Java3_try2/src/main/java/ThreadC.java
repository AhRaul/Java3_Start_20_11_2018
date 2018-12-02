public class ThreadC implements Runnable {
    SinchMetods c;

    ThreadC (SinchMetods c) {
        this.c = c;
        new Thread (this).start();       //?? что подразумевает this в данной строке?
    }

    public void run() {
        for(int i=0; i<5; i++) {
            c.threadC();
        }
    }
}
