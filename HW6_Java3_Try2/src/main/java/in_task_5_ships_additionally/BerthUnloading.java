package in_task_5_ships_additionally;

import java.util.concurrent.Semaphore;

/**
 * Берег разгрузки
 * Разгружает любой груз, добавить возможность показывать виды и количество хранимого груза
 */

public class BerthUnloading extends Berth {
    Clothes clothes;
    Food food;
    Fuel fuel;
    public Semaphore bandsUnload = new Semaphore(1);

    //создать свободный берег разгрузки

    /**
     * конструктор, создать пустой берег разгрузки
     *
     * @param loadspeed
     */
    public BerthUnloading(int loadspeed) {
        this.loadspeed = loadspeed;
        this.clothes = new Clothes(0);
        this.food = new Food(0);
        this.fuel = new Fuel(0);
    }

    /**
     * Общий метод! процесс разгрузки
     *
     * @param ship  корабль, с которого осуществляется разгрузка, используется для получения доступа
     *              к товару на борту shipCargo = ship.getCargo() (для выяснения класса товара, и манипуляции с ним);
     *              к информации о количестве товара на борту shipCargo.getQuantity();
     *              и разгруске товара с борта shipCargo.decQuantity();
     */
    @Override
    public void go(Ship ship) {
        try {
            bandsUnload.acquire();
            long start = System.currentTimeMillis();                //засекли время начала выполнения разгрузки
            Cargo shipCargo = ship.getCargo();
            int millisec;

            if(shipCargo instanceof Clothes) {                      //проверка пришедшего класса (если одежда)
                System.out.println("Начат процесс разгрузки одежды");
                millisec = timing(ship);
                this.clothes.incQuantity(shipCargo.getQuantity());   //добавить весь товар с корабля на разгрузочный берег
                shipCargo.decQuantity(shipCargo.getQuantity());        //опустошить корабль

                long finish = System.currentTimeMillis();
                long thismethodtime = finish - start;                  //получить время выполнения расчётов
                try {
                    Thread.sleep(millisec-thismethodtime);   //выждать время разгрузки с вычетом времени расчёта
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Процесс разгрузки одежды завершён");

            } else if(shipCargo instanceof Food) {
                System.out.println("Начат процесс разгрузки еды");
                millisec = timing(ship);
                this.food.incQuantity(shipCargo.getQuantity());   //добавить весь товар с корабля на разгрузочный берег
                shipCargo.decQuantity(shipCargo.getQuantity());        //опустошить корабль

                long finish = System.currentTimeMillis();
                long thismethodtime = finish - start;                  //получить время выполнения расчётов
                try {
                    Thread.sleep(millisec-thismethodtime);   //выждать время разгрузки с вычетом времени расчёта
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Процесс разгрузки еды завершён");

            } else if(shipCargo instanceof Fuel) {
                System.out.println("Начат процесс разгрузки топлива");
                millisec = timing(ship);
                this.fuel.incQuantity(shipCargo.getQuantity());   //добавить весь товар с корабля на разгрузочный берег
                shipCargo.decQuantity(shipCargo.getQuantity());        //опустошить корабль

                long finish = System.currentTimeMillis();
                long thismethodtime = finish - start;                  //получить время выполнения расчётов
                try {
                    Thread.sleep(millisec-thismethodtime);   //выждать время разгрузки с вычетом времени расчёта
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Процесс разгрузки топлива завершён");
            } else {
                System.out.println("Разгрузка не удалась, не определен вид товара");
            }
            bandsUnload.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * расчет необходимого времени для разгрузки корабля
     *
     * @param ship  рассматриваемый корабль, получение количества груза на корабле для рассчета времени
     * @return      возврат времени в миллисекундах (время, необходимое для разгрузки)
     */
    private int timing(Ship ship) {
        int millisec;
        Cargo shipCargo = ship.getCargo();           //получить инфу о грузе на борту корабля
        if(shipCargo.getQuantity()>=loadspeed) {     //если товара на борту больше пропускной способности за секунду
            millisec = 1000*(shipCargo.getQuantity()/loadspeed)+(1000/loadspeed*(shipCargo.getQuantity()%loadspeed));
        } else {                                    //если товара на борту меньше пропускной способности за секунду
            millisec = (1000/loadspeed)*shipCargo.getQuantity();
        }
        return millisec;
    }
}
