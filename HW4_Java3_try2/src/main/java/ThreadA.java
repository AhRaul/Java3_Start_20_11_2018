public class ThreadA implements Runnable {
    SinchMetods a;

    ThreadA (SinchMetods a) {
        this.a = a;
        new Thread (this).start();       //?? что подразумевает this в данной строке?
    }

    public void run() {
        for(int i=0; i<5; i++) {
            a.threadA();
        }
    }
}