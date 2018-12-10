package avaj.simulator;

import java.util.Random;

public class WeatherProvider {

    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"RAIN","FOG","SUN","SNOW"};

    private WeatherProvider(){

    }

    public static WeatherProvider getProvider() {
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates){

        Random hoza = new Random();

        hoza.nextInt(100);
        return weather[((coordinates.getLongitude() > (hoza.nextInt(100))) ? 1 : 0) + ((coordinates.getLatitude() > (hoza.nextInt(100))) ? 1 : 0) + ((coordinates.getHeight() > (hoza.nextInt(100))) ? 1 : 0)];

    }
}
