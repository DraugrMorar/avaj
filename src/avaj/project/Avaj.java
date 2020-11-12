package avaj.project;

import java.io.*;

public class Avaj {

    static WeatherTower weatherTower = new WeatherTower();
    public static void main(String[] args) {
        if (args.length == 1) {
            read_from_file(args[0]);
        } else {
            System.out.println("Usage: java Avaj.java [file]");
        }
    }

    public static void error(String message) {
        PrintStream console = new PrintStream(System.out);
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
            if(validateFirstString(buff)) {
                cycles = Long.parseLong(buff);
                if (cycles > Integer.MAX_VALUE || cycles <= 0) {
                    error("Error. Not valid first line");
                }
            } else {
                error("Error. First line.");
            }
            while (buff != null) {
                buff = br.readLine();
                createAircraft(buff);
            }
            while(cycles > 0) {
                weatherTower.changeWeather();
                cycles--;
            }
        } catch (IOException e) {
            error("Error");
        }
    }

    private static boolean validateFirstString(String buff) {
        if (buff == null || buff.length() == 0 || buff.contains(" ") || buff.contains(".")) {
            return false;
        }
        for (int i = 0; i < buff.length(); i++) {
            if ((buff.charAt(i) > 47 && buff.charAt(i) < 58)) {
                i++;
            } else {
                return false;
            }
        }
        return true;
    }

    private static void createAircraft(String text) throws FileNotFoundException {
        long latitude;
        long longitude;
        long height;
        if (text!= null && !text.isEmpty()) {
            String[] aircraft = text.split(" ");
            if (aircraft.length != 5 || aircraft[1].isEmpty()) {
                error("Not valid string " + text);
            }
            if (aircraft[0].equals("Baloon") || aircraft[0].equals("JetPlane") || aircraft[0].equals("Helicopter")) {
                if (validateFirstString(aircraft[2]) && validateFirstString(aircraft[3]) && validateFirstString(aircraft[4])) {
                    latitude= Long.parseLong(aircraft[2]);
                    longitude = Long.parseLong(aircraft[3]);
                    height = Long.parseLong(aircraft[4]);
                    if (latitude > Integer.MAX_VALUE || longitude > Integer.MAX_VALUE) {
                        error("Wrong coordinates " + latitude + " " + longitude + " " + height);
                    }
                    weatherTower.register(AircraftFactory.newAircraft(aircraft[0], aircraft[1], (int)latitude, (int)longitude, (int)height));
                }
            } else {
                error("Wrong line " + aircraft[0]);
            }
        }
    }
}
