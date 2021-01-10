package avaj.project;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String aircraft = "Helicopter#" + name + "(" + this.id + ")";
        switch (this.weatherTower.getWeather(coordinates)) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
                System.out.println(aircraft + "Прекрасная погода для вертолетной прогулки");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                System.out.println(aircraft + "Кажется дождик начинается");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                System.out.println(aircraft + "Видимость нулевая, я не вижу свои лопасти");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
                System.out.println(aircraft + "Кажется мы падаем");
                break;
        }
        if (coordinates.getHeight() > 100) {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
        } else if (coordinates.getHeight() <= 0) {
            System.out.println("Tower says: Helicopter#" + name + "(" + this.id + ") unregistered from weather tower");
            this.weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: Helicopter#" + name + "(" + this.id + ") registered to weather tower.");
    }
}
