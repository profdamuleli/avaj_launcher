package avaj.simulator;

import avaj.simulator.WeatherProvider;
import avaj.simulator.Aircraft;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
     

    Baloon(String name, Coordinates coordinates)
    {
        super(name, coordinates);
        this.name = name;
        this.coordinates = coordinates;
    }

    public void updateConditions()
    {
        String weather = WeatherProvider.getProvider().getCurrentWeather(this.coordinates);
        String coordsonWeather = weatherTower.getWeather(this.coordinates);
        int Longitude = this.coordinates.getLongitude();
        int Latitude = this.coordinates.getLatitude();
        int Height = this.coordinates.getHeight();
        String name = this.getName();
        long craftId = this.getId();

        switch (coordsonWeather){
            case "SUN": 
                    this.coordinates = new Coordinates(Longitude + 2, Latitude, Height + 4);
                    FileInfo.writer.println("Baloon#" + name + "(" + craftId + ") : its very sunny and hot.");
                break;
            case "RAIN": 
                    this.coordinates = new Coordinates(Longitude, Latitude, Height - 5);
                    FileInfo.writer.println("Baloon#" + name + "(" + craftId + "): Flying on a Raining day.");
                break;
            case "FOG": 
                    this.coordinates = new Coordinates(Longitude, Latitude, Height - 3);
                    FileInfo.writer.println("Baloon#" + name + "(" + craftId + "): Be careful and stay focus.");
                break;
            case "SNOW": 
                    this.coordinates = new Coordinates(Longitude, Latitude, Height - 15);
                    FileInfo.writer.println("Baloon#" + name + "(" + craftId + "): Winter is on its way.");
                break;
            default:
                break;
        }
        

        if(Height == 0){
            FileInfo.writer.println("Baloon#" + name + "(" + craftId + "): landing.");
            weatherTower.unregister(this);
            FileInfo.writer.println("Baloon#" + name + "(" + craftId +"): unregistered from the weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower)
    {

        long craftId = this.getId();

        this.weatherTower = weatherTower;
        
        FileInfo.writer.println("Tower says: Baloon#" + name + "(" + craftId + "): registered to the weather tower.");
        try{
            this.weatherTower.register(this);
        }
        catch(Exception e){
            System.out.println("no flyable");
        }
    }
}
