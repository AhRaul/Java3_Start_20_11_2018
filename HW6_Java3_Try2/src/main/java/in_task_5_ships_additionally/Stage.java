package in_task_5_ships_additionally;
/**
 * Этап перемещения, загрузки/разгрузки
 */
public abstract class Stage {
    protected int processTime;      //длительность процесса в секундах
    protected String description;
    public String getDescription() {
        return description;
    }

    //запуск процесса этапа
    public abstract void go(Ship ship);
}
