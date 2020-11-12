package avaj.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AircraftFactory {
//    static PrintStream o;
//    static {
//        try {
//            o = new PrintStream(new File("A.txt"));
//            System.setOut(o);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height){

        if (type.equals("Baloon")) {
            System.out.println("New baloon with name: " + name + "!");
            return new Baloon(name, new Coordinates(longitude, latitude, height));
        } else if (type.equals("JetPlane")) {
            System.out.println("New JetPlane with name: " + name + "!");
            return new JetPlane(name, new Coordinates(longitude, latitude, height));
        } else if (type.equals("Helicopter")) {
            System.out.println("New Helicopter with name: " + name + "!");
            return new Helicopter(name, new Coordinates(longitude, latitude, height));
        }
        return null;
    }
}
