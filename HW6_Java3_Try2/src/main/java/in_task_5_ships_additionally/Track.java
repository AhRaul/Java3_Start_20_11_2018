package in_task_5_ships_additionally;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Порядок действий
 */

public class Track {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Track(Stage... stages) {                                  //??что за конструкция конструктора??
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public int getNumOfStages() {       //количество стадий
        return stages.size();
    }
}
