package in_task_5_ships_additionally;

/**
 * "Корабли"
 *
 * 1 Есть транспортные корабли, которые подплывают к проливу и далее
 *   плывут к причалам для погрузки разного рода товара.
 *
 * 2 Они проходят через узкий пролив где одновременно могут находиться
 *   только 2 корабля.
 *
 * 3 Вид кораблей и их вместительность могут быть разными в зависимости от типа товаров,
 *   которые нужно загрузить на корабль. (Представим что корабли везут Одежду, Еду, Топливо)
 *
 * 4 Есть 3 вида причалов для погрузки кораблей в соотвествие с товарами,
 *   за одну секунду причал загружает на корабль 100 ед. товара, вместимость кораблей 500.
 *
 * 5 После загрузки нужно пройти обратно через пролив и перевести товар.
 *
 * 6 Нужно перевести 2700 ед. одежды, 5900 еды, 8500 топлива.
 *
 * Перевести груз.
 * Правильно разбить задачу на параллельность.
 * Синхронизировать потоки, сохранить целостность данных.
 */

public class Main {
    public static final int SHIPS_COUNT = 10;
    public static void main(String[] args) {
              //выполнено не в формате гонки, просто вывод порядка действий

        //массив товаров
        Clothes clothes = new Clothes(2700);
        Food food = new Food(5900);
        Fuel fuel = new Fuel(8500);

        //создание этапов
        BerthLoading bClothes = new BerthLoading(clothes, 100);
        BerthLoading bFood = new BerthLoading(food, 100);
        BerthLoading bFuel = new BerthLoading(fuel, 100);
        BerthUnloading berthUnloading = new BerthUnloading(100);
        OpenWater ow1 = new OpenWater(40);
        Strait strait = new Strait(50);
        OpenWater ow2 = new OpenWater(60);

        //создание трасс
        Track track1 = new Track(bClothes, ow1, strait, ow2, berthUnloading);
        Track track2 = new Track(bFood, ow1, strait, ow2, berthUnloading);
        Track track3 = new Track(bFuel, ow1, strait, ow2, berthUnloading);

        //создание кораблей
        Ship[] ships = new Ship[SHIPS_COUNT];
        for (int i = 0; i < 3; i++) {   //3 корабля на одежду
            ships[i] = new Ship(("Корабль " + i), clothes,500, (20 + (int) (Math.random() * 10)), bClothes, berthUnloading, track1);
        }
        for (int i = 3; i < 6; i++) {   //3 корабля на еду
            ships[i] = new Ship(("Корабль " + i), food,500, (20 + (int) (Math.random() * 10)), bFood, berthUnloading, track2);
        }
        for (int i = 6; i < 10; i++) {   //4 корабля на топливо
            ships[i] = new Ship(("Корабль " + i), fuel,500, (20 + (int) (Math.random() * 10)), bFuel, berthUnloading, track3);
        }

        //запуск потоков
        Thread[] t = new Thread[SHIPS_COUNT];
        for (int i = 0; i < ships.length; i++) {
            t[i] = new Thread(ships[i]);
            t[i].start();
        }

        System.out.println("Начало движения");
    }
}
