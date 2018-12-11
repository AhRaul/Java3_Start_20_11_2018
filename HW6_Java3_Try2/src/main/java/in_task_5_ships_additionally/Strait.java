package in_task_5_ships_additionally;

import java.util.concurrent.Semaphore;

/**
 * Узкий пролив
 * С учетом кораблей, плывущих как в одну сторону, так и в другую
 */

public class Strait extends Stage {
    public Semaphore bandwidth = new Semaphore(2);
    public int length;      //длина трассы

    public Strait(int length) {
        this.length = length;
        this.description = "Узкий пролив " + length + " км";
    }

    @Override
    public void go(Ship ship) {
        try {
            bandwidth.acquire();
            try {
                System.out.println(ship.getName() + " начал этап: " + description);
                Thread.sleep(length / ship.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(ship.getName() + " закончил этап: " + description);
            }
            bandwidth.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
