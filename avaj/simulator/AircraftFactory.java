package avaj.simulator;

import avaj.simulator.Baloon;
import avaj.simulator.Helicopter;
import avaj.simulator.JetPlane;

public class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws Exception {
       
		if(longitude < 0 || latitude < 0 || height < 0) throw new Exception("pass only possitive numbers");

		Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type) {
            case ("Helicopter"):
                return new Helicopter(name, coordinates);
            case ("JetPlane"):
                return new JetPlane(name, coordinates);
            case ("Baloon"):
                return new Baloon(name, coordinates);
            default:
                return null;

        }
    }
}
