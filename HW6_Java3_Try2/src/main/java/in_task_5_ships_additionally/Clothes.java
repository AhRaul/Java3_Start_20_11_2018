package in_task_5_ships_additionally;

/**
 * Вид груза, одежда
 */

public class Clothes extends Cargo {

    public Clothes(int quantity) {
        super(quantity, "Одежда " + quantity + " единиц");
    }
}
