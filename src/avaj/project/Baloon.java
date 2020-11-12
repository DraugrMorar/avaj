package avaj.project;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        switch (this.weatherTower.getWeather(coordinates)) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
                break;
        }
        if(coordinates.getHeight() > 100) {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
        } else if (coordinates.getHeight() <= 0) {
            this.weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
//        weatherTower.register(this);
    }
}
