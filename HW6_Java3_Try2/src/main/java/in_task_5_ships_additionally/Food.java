package in_task_5_ships_additionally;

/**
 * Вид груза, еда
 */

public class Food extends Cargo {

    public Food(int quantity) {
        super(quantity, "Еда " + quantity + " единиц");
    }
}
