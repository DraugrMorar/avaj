package avaj.project;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String aircraft = "Baloon#" + name + "(" + this.id + ")";
        switch (this.weatherTower.getWeather(coordinates)) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
                System.out.println(aircraft + "Прекрасный вид и прекрасная погода!");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
                System.out.println(aircraft + "Мы промокли до ниточки");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
                System.out.println(aircraft + "Мы как ёжики в тумане");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
                System.out.println(aircraft + "Бррр... Кажется у меня на носу сосулька");
                break;
        }
        if (coordinates.getHeight() > 100) {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
        } else if (coordinates.getHeight() <= 0) {
            System.out.println("Tower says: Baloon" + name + "(" + this.id + ") unregistered from weather tower");
            this.weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: Baloon#" + name + "(" + this.id + ")  registered to weather tower.");
    }
}
