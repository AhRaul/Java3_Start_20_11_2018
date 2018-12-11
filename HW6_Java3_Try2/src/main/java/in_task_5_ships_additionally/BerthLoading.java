package in_task_5_ships_additionally;

/**
 * Причал загрузки
 */

import java.util.concurrent.Semaphore;

/**
 *
 * @param <T> вид груза, загружаемый на этом причале
 *           (причал может загружать только один вид груза)
 */
public class BerthLoading<T extends Cargo> extends Berth {
    Cargo cargo;    //сохранить вид груза, количество груза
    public Semaphore bandsLoad = new Semaphore(1);

    /**
     * конструктор информация о грузе на берегу
     *
     * @param cargo         груз, тип груза
     * @param loadspeed     скорость загрузки
     */
    public BerthLoading(T cargo, int loadspeed) {
        this.cargo = cargo;
        this.loadspeed = loadspeed;
    }

    /**
     * Процесс загрузки
     *
     * @param ship
     */
    @Override
    public void go(Ship ship) {
        try {
            bandsLoad.acquire();
            long start = System.currentTimeMillis();                //засекли время начала выполнения разгрузки
            Cargo shipCargo = ship.getCargo();
            int millisec;

            if(this.cargo instanceof Clothes) {                      //проверка типа товара на берегу (если одежда)
                System.out.println("Начат процесс загрузки одежды");
                millisec = timing(ship);                             //расчёт времени загрузки

                if(shipCargo.getQuantity() == 0) {                                                   //если корабль пустой
                    if (cargo.getQuantity() >= ship.getCapacity()) {      //если на берегу больше или равно товара, чем места на корабле
                        shipCargo.incQuantity(ship.getCapacity());       //добавить весь товар с загрузочного берега на корабль до предела
                        this.cargo.decQuantity(shipCargo.getQuantity()); //вычесть с берега столько, сколько загрузили на корабль
                    } else {                                             //если на берегу меньше, чем может поместиться, то загрузить всё, что есть, грузить на корабль
                        shipCargo.incQuantity(this.cargo.getQuantity()); //добавить весь товар с загрузочного берега на корабль до предела
                        this.cargo.decQuantity(shipCargo.getQuantity()); //вычесть с берега столько, сколько загрузили на корабль
                    }

                    long finish = System.currentTimeMillis();
                    long thismethodtime = finish - start;                //получить время выполнения расчётов
                    try {
                        Thread.sleep(millisec - thismethodtime);     //выждать время разгрузки с вычетом времени выполнения метода и расчёта
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Процесс загрузки одежды завершён");
                } else {
                    System.out.println("Процесс загрузки одежды прерван, корабль не пустой");
                }
            } else if(shipCargo instanceof Food) {
                System.out.println("Начат процесс загрузки еды");
                millisec = timing(ship);                             //расчёт времени загрузки

                if(shipCargo.getQuantity() == 0) {                                                   //если корабль пустой
                    if (cargo.getQuantity() >= ship.getCapacity()) {      //если на берегу больше или равно товара, чем места на корабле
                        shipCargo.incQuantity(ship.getCapacity());       //добавить весь товар с загрузочного берега на корабль до предела
                        this.cargo.decQuantity(shipCargo.getQuantity()); //вычесть с берега столько, сколько загрузили на корабль
                    } else {                                             //если на берегу меньше, чем может поместиться, то загрузить всё, что есть, грузить на корабль
                        shipCargo.incQuantity(this.cargo.getQuantity()); //добавить весь товар с загрузочного берега на корабль до предела
                        this.cargo.decQuantity(shipCargo.getQuantity()); //вычесть с берега столько, сколько загрузили на корабль
                    }

                    long finish = System.currentTimeMillis();
                    long thismethodtime = finish - start;                //получить время выполнения расчётов
                    try {
                        Thread.sleep(millisec - thismethodtime);     //выждать время разгрузки с вычетом времени выполнения метода и расчёта
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Процесс загрузки еды завершён");
                } else {
                    System.out.println("Процесс загрузки еды прерван, корабль не пустой");
                }
            } else if(shipCargo instanceof Fuel) {
                System.out.println("Начат процесс загрузки топлива");
                millisec = timing(ship);                             //расчёт времени загрузки

                if(shipCargo.getQuantity() == 0) {                                                   //если корабль пустой
                    if (cargo.getQuantity() >= ship.getCapacity()) {      //если на берегу больше или равно товара, чем места на корабле
                        shipCargo.incQuantity(ship.getCapacity());       //добавить весь товар с загрузочного берега на корабль до предела
                        this.cargo.decQuantity(shipCargo.getQuantity()); //вычесть с берега столько, сколько загрузили на корабль
                    } else {                                             //если на берегу меньше, чем может поместиться, то загрузить всё, что есть, грузить на корабль
                        shipCargo.incQuantity(this.cargo.getQuantity()); //добавить весь товар с загрузочного берега на корабль до предела
                        this.cargo.decQuantity(shipCargo.getQuantity()); //вычесть с берега столько, сколько загрузили на корабль
                    }

                    long finish = System.currentTimeMillis();
                    long thismethodtime = finish - start;                //получить время выполнения расчётов
                    try {
                        Thread.sleep(millisec - thismethodtime);     //выждать время разгрузки с вычетом времени выполнения метода и расчёта
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Процесс загрузки топлива завершён");
                } else {
                    System.out.println("Процесс загрузки топлива прерван, корабль не пустой");
                }
            } else {
                System.out.println("Загрузка не удалась, не определен вид товара");
            }
            bandsLoad.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * расчет необходимого времени для загрузки корабля
     *
     * @param ship  рассматриваемый корабль, получение количества груза на корабле для рассчета времени
     * @return      возврат времени в миллисекундах (время, необходимое для разгрузки)
     */
    private int timing(Ship ship) {                                                                                         //!!не учтен вариант, когда общая вместимость корабля меньше скорости загрузки, возможны ошибки, возможно позже подумаю, пока меньше не задавать!!
        int millisec;
        Cargo shipCargo = ship.getCargo();           //получить инфу о грузе на борту корабля (должен быть пустой)
        if (this.cargo.getQuantity() >= loadspeed && this.cargo.getQuantity() > ship.getCapacity()) {           //если товара на берегу больше пропускной способности за секунду, и места на корабле меньше чем есть на берегу
            millisec = 1000 * (ship.getCapacity() / loadspeed) + ((1000 / loadspeed) * (ship.getCapacity() % loadspeed));
        } else if (this.cargo.getQuantity() >= loadspeed && this.cargo.getQuantity() <= ship.getCapacity()){    //если товара на берегу больше пропускной способности за секунду, и места на корабле больше чем есть на берегу
            millisec = 1000 * (this.cargo.getQuantity() / loadspeed) + ((1000 / loadspeed) * (this.cargo.getQuantity() % loadspeed));
        } else {                                     //если товара на берегу меньше пропускной способности за секунду
            millisec = (1000/loadspeed)*this.cargo.getQuantity();
        }
        return millisec;
    }
}
