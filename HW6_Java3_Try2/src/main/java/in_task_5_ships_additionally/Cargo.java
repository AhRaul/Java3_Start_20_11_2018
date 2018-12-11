package in_task_5_ships_additionally;

/**
 * Груз
 */

public abstract class Cargo {
    protected int quantity;                   //количество
    protected String description;             //описание груза

    public Cargo(int quantity, String description) {
        this.quantity = quantity;
        this.description = description;
    }

    //вернуть инфу "описание"
    public String getDescription() {
        return description;
    }

    //вернуть инфу "количество"
    public int getQuantity() {
        return quantity;
    }

    /**
     * Увеличение количества единиц товара на increase единиц
     *
     * @param increase количество единиц товара, на которое необходимо увеличить
     */
    public void incQuantity(int increase) {
        this.quantity = this.quantity + increase;
    }

    /**
     * Уменьшение количества единиц товара на increase единиц
     *
     * @param decrease количество единиц товара, на которое необходимо уменьшить
     */
    public void decQuantity(int decrease) {
        this.quantity = this.quantity - decrease;
    }
}
