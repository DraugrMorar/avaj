package avaj.project;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
        System.out.println(observers.size());
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
        System.out.println(observers);
        for(Flyable fly: observers){
            fly.updateConditions();
        }
    }
}
