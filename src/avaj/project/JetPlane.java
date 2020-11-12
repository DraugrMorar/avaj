package avaj.project;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String aircraft = "JetPlane#" + name + "(" + this.id + ")";
        switch (this.weatherTower.getWeather(coordinates)) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
                System.out.println(aircraft + "Я вижу свой дом в элюминаторе");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                System.out.println(aircraft + "Хорошо что мы не на воздушном шаре!");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
                System.out.println(aircraft + "Мы сейчас пролетаем облако?");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
                System.out.println(aircraft + "Сколько за бортом градусов?");
                break;
        }
        if (coordinates.getHeight() > 100) {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
        } else if (coordinates.getHeight() <= 0) {
            System.out.println("Tower says: JetPlane" + name + "(" + this.id + ") unregistered from weather tower");
            this.weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: JetPlane#" + name + "(" + this.id + ") registered to weather tower.");
    }
}