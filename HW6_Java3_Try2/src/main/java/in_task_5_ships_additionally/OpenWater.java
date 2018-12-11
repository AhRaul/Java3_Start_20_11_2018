package in_task_5_ships_additionally;
/**
 * Пролив
 */
public class OpenWater extends Stage {
    private int length;

    public OpenWater(int length) {
        this.length = length;
        this.description = "Открытая вода " + length + " км";
    }

    //описано движение по открытой воде
    @Override
    public void go(Ship ship) {
        System.out.println(ship.getName() + " начал этап: " + description);
        try {
            Thread.sleep(length / ship.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ship.getName() + " закончил этап: " + description);
    }
}
