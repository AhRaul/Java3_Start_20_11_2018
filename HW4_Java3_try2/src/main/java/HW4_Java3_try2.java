/**
 * Урок 4. Многопоточность. Часть I
 *
 * 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
 * Используйте wait/notify/notifyAll.
 */

public class HW4_Java3_try2 {
    public static void main(String[] args) {
        SinchMetods abc = new SinchMetods();
        new ThreadA(abc);
        new ThreadB(abc);
        new ThreadC(abc);
    }
}
