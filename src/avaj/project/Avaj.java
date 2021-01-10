package avaj.project;

import java.io.*;

public class Avaj {

    static WeatherTower weatherTower;
    static PrintStream o;
    static PrintStream console = System.out;

    static {
        try {
            o = new PrintStream(new File("A.txt"));
            System.setOut(o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            read_from_file(args[0]);
        } else {
            System.out.println("Usage: java Avaj.java [file]");
        }
    }

    public static void error(String message) {
        System.setOut(console);
        System.out.println(message);
        System.exit(0);
    }

    public static void read_from_file(String arg) {
        long cycles = 0;
        File file = new File(arg);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            error("File was not found");
        }
        try {
            String buff = br.readLine();
            if (buff != null && buff.matches("\\d+") && buff.length() < 10) {
                cycles = Long.parseLong(buff);
                if (cycles > Integer.MAX_VALUE || cycles <= 0) {
                    error("Error. Not valid first line");
                }
            } else {
                error("Error. First line.");
            }
            weatherTower = new WeatherTower();

            while (buff != null) {
                buff = br.readLine();

                createAircraft(buff);
            }
            while (cycles > 0) {
                weatherTower.changeWeather();
                cycles--;
            }
        } catch (IOException e) {
            error("Error");
        }
    }

    private static void createAircraft(String text) {
        long latitude;
        long longitude;
        long height;
        if (text != null && !text.isEmpty()) {
            String[] aircraft = text.split("\\s+");
            if (aircraft.length != 5 || aircraft[1].isEmpty()) {
                error("Not valid string " + text);
            }
            if ((aircraft[0].equals("Baloon") || aircraft[0].equals("JetPlane") || aircraft[0].equals("Helicopter")) &&
                    (aircraft[2].matches("\\d+") && aircraft[3].matches("\\d+") && aircraft[4].matches("\\d+"))) {
                latitude = Long.parseLong(aircraft[2]);
                longitude = Long.parseLong(aircraft[3]);
                height = Long.parseLong(aircraft[4]);
                if (latitude > Integer.MAX_VALUE || longitude > Integer.MAX_VALUE || height > Integer.MAX_VALUE) {
                    error("Wrong coordinates " + latitude + " " + longitude + " " + height);
                }
                if (height > 100) {
                    height = 100;
                }
                AircraftFactory.newAircraft(aircraft[0], aircraft[1], (int) latitude, (int) longitude, (int) height).registerTower(weatherTower);
            } else {
                error("Wrong line " + text);
            }
        }
    }
}
