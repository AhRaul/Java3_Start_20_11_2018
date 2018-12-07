public class SinchMetods {
    boolean afterA = false;
    boolean afterB = false;
    boolean afterC = true;

    synchronized void threadA() {       //??почему счетчик не работает внутри метода?
        while (!afterC) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Exception");
            }
        }
        System.out.println("A");
        afterA = true;
        afterB = false;
        afterC = false;
        notifyAll();                    //?? почему эта конструкция е работает для "notify()" ?
    }

    synchronized void threadB() {
        while (!afterA) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Exception");
            }
        }
        System.out.println("B");
        afterA = false;
        afterB = true;
        afterC = false;
        notifyAll();
    }

    synchronized void threadC() {
        while (!afterB) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Exception");
            }
        }
        System.out.println("C");
        afterA = false;
        afterB = false;
        afterC = true;
        notifyAll();
    }
}