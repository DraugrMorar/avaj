package avaj.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }
//    static PrintStream o;
//    static {
//        try {
//            o = new PrintStream("A.txt");
//            System.setOut(o);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void updateConditions() {
        switch (weatherTower.getWeather(coordinates)) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
                System.out.println("Прекрасная погода для воздушной прогулки");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                System.out.println("Кажется дождик начинается");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                System.out.println("Видимость нулевая, яне вижу лопасти");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
                System.out.println("Зима близко");
                break;
        }
        if (coordinates.getHeight() > 100) {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
        } else if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
    }
}
