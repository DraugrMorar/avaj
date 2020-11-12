package avaj.project;

public class WeatherProvider {

    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[((coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude()) % 4)];
    }
}
