import java.util.concurrent.Semaphore;

public class Car  implements Runnable {
    public static boolean startRace;            //команда старт всем!!
    private static final Object lock = new Object();
    public static final boolean UNREADY = false;
    public static final boolean READY = true;
    public static Semaphore bandwidth;// = new Semaphore(2);

    private int numOfEndedStage;                //количество завершённых участником участков

    private static int CARS_COUNT;              //номер участника
    static {                                        //поэксперементировать со статическим полем
        CARS_COUNT = 0;
    }                                           //обнуление при пересоздании списка участников
    private boolean readinessCheck = UNREADY;   //готовность участника
    private Race race;                          //параметры трассы
    private int speed;                          //скорость участника
    private String name;                        //имя участника
    public String getName() {                   //узнать имя учатсника
        return name;
    }
    public int getSpeed() {                     //узнать скорость участника
        return speed;
    }
    public Car(Race race, int speed) {          //конструктор, создание участника
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;                           //присвоение номера участнику
        this.name = "Участник #" + CARS_COUNT;  //присвоение имени
        startRace = false;
        numOfEndedStage = 0;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            readinessCheck = READY;
        } catch (Exception e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            while (!startRace) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < race.getNumOfStages(); i++) {           //добавлен симафор, для ограничения проезда по туннелю
            if(race.getStages().get(i).getDescription().startsWith("Тоннель")) {
                try {
                    System.out.println(this.getName() + " готовится к этапу(ждет): " + race.getStages().get(i).getDescription());
                    bandwidth.acquire();
                    race.getStages().get(i).go(this);
                    bandwidth.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                race.getStages().get(i).go(this);
            }
        }
    }

    public static void continueThread() {
        startRace = true;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public boolean getReadiness() {             //проверка готовности
        return readinessCheck;
    }

    public int getNumOfEndedStage() {           //получить количество пройденных участков
        return numOfEndedStage;
    }

    public void incNumOfEndedStage() {           //добавить один пройденный участок участнику
        numOfEndedStage++;
    }
}

//Исходный редактируемый код задачи
//public class Car  implements Runnable {
//    private static int CARS_COUNT;
//    static {
//        CARS_COUNT = 0;
//    }
//    private Race race;
//    private int speed;
//    private String name;
//    public String getName() {
//        return name;
//    }
//    public int getSpeed() {
//        return speed;
//    }
//    public Car(Race race, int speed) {
//        this.race = race;
//        this.speed = speed;
//        CARS_COUNT++;
//        this.name = "Участник #" + CARS_COUNT;
//    }
//    @Override
//    public void run() {
//        try {
//            System.out.println(this.name + " готовится");
//            Thread.sleep(500 + (int)(Math.random() * 800));
//            System.out.println(this.name + " готов");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < race.getStages().size(); i++) {
//            race.getStages().get(i).go(this);
//        }
//    }
//}