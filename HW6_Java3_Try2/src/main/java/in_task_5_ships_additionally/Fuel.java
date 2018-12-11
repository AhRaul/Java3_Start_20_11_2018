package in_task_5_ships_additionally;

/**
 * Вид груза, топливо
 */

public class Fuel extends Cargo {

    public Fuel(int quantity) {
        super(quantity, "Топливо " + quantity + " единиц");
    }
}
