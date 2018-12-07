public class ThreadB implements Runnable {
    SinchMetods b;

    ThreadB (SinchMetods b) {
        this.b = b;
        new Thread (this).start();       //?? что подразумевает this в данной строке?
    }

    public void run() {
        for(int i=0; i<5; i++) {
            b.threadB();
        }
    }
}