import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {                                  //??что за конструкция конструктора??
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public int getNumOfStages() {       //количество стадий
        return stages.size();
    }
}

//Исходный редактируемый код задачи
//public class Race {
//    private ArrayList<Stage> stages;
//    public ArrayList<Stage> getStages() { return stages; }
//    public Race(Stage... stages) {
//        this.stages = new ArrayList<>(Arrays.asList(stages));
//    }
//}
