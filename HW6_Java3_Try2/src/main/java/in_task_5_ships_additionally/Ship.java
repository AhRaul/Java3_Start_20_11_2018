package in_task_5_ships_additionally;

/**
 * родительский класс корабль
 * зацикленно плавает туда, сюда, загружается, разгружается пока груз не закончится
 * кораблей на каждый вид может быть несколько
 */

public class Ship<T extends Cargo> implements Runnable  {
    protected int capacity;     //вместимость корабля
    protected int speed;        //скорость корабля
    private Cargo cargo;                //перевозимый груз (хранит вес груза, описание загруженного груза)
    private BerthLoading berthLoading;      //корабльл привязан к определённому причалу загрузки
    private BerthUnloading berthUnloading;  //и причалу разгрузки
    private String name;
    private Track track;

    public Ship(String name, T cargo, int capacity, int speed, BerthLoading berthLoading, BerthUnloading berthUnloading, Track track) {
        this.name = name;
        this.cargo = cargo;
        this.speed = speed;
        this.berthLoading = berthLoading;
        this.berthUnloading = berthUnloading;
        this.capacity = capacity;
        this.track = track;
    }

    //совершаемые кораблем действия
    public void run() {
            //!! дописать метод !!
            //!! с учетом цикла, пока не закончится товар, и обратного пути
    }

    //получить инфу про перевозимый груз
    public Cargo getCargo() {
        return cargo;
    }

    //получить доступ к переменной, чтобы задать её
    public void setCargo(Cargo cargo) {
         this.cargo = cargo;
    }

    //получить инфу про вместимость корабля
    public int getCapacity() {
       return this.capacity;
    }

    //получить имя
    public String getName() {
        return this.name;
    }

    //получить сорость корабля
    public int getSpeed() {
        return this.speed;
    }
}
