package avaj.simulator;

import avaj.simulator.Aircraft;
import avaj.simulator.FileInfo;
import avaj.simulator.Coordinates;
import avaj.simulator.WeatherProvider;


public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    //Coordinates coordinates = new Coordinates();
    //FileInfo write = new FileInfo();

    public JetPlane(String name, Coordinates coordinates)
    {
        super(name, coordinates);
        this.name = name;
        this.coordinates = coordinates;
    }

    public void updateConditions()
    {
        String weather = WeatherProvider.getProvider().getCurrentWeather(this.coordinates);
        String coordsonWeathers = weatherTower.getWeather(this.coordinates);
        String coordsonWeather = weatherTower.getWeather(this.coordinates);
        int Longitude = this.coordinates.getLongitude();
        int Latitude = this.coordinates.getLatitude();
        int Height = this.coordinates.getHeight();
        String name = this.getName();
        long craftId = this.getId();

        switch (coordsonWeathers){
            case "SUN": 
                    this.coordinates = new Coordinates(Longitude + 2, Latitude, Height + 4);
                    FileInfo.writer.println("JetPlane#" + name + "(" + craftId + ") : its very sunny and hot.");
                break;
            case "RAIN": 
                    this.coordinates = new Coordinates(Longitude, Latitude, Height - 5);
                    FileInfo.writer.println("JetPlane#" + name + "(" + craftId + "): Flying on a Raining day.");
                break;
            case "FOG": 
                    this.coordinates = new Coordinates(Longitude, Latitude, Height - 3);
                    FileInfo.writer.println("JetPlane#" + name + "(" + craftId + "): Be careful and stay focus.");
                break;
            case "SNOW": 
                    this.coordinates = new Coordinates(Longitude, Latitude, Height - 15);
                    FileInfo.writer.println("JetPlane#" + name + "(" + craftId + "): Winter is on its way.");
                break;
            default:
                break;
        }
        
        
        /*if (weather.equals("SUN")) {
            this.coordinates = new Coordinates(Longitude, Latitude + 2, Height + 4);
            FileInfo.writer.println("JetPlane#" + name + "(" + craftId + "): A beautiful day to fly.");
        } else if (weather.equals("RAIN")) {
            this.coordinates = new Coordinates(Longitude, Latitude - 5, Height);
            FileInfo.writer.println("JetPlane#" + name + "(" + craftId + "): What a rainy day.");
        } else if (weather.equals("FOG")) {
            this.coordinates = new Coordinates(Longitude, Latitude - 3, Height);
            FileInfo.writer.println("JetPlane#" + name + "(" + craftId + "): I can't see shit in this fog");
        } else if (weather.equals("SNOW")) {
            this.coordinates = new Coordinates(Longitude, Latitude, Height - 15);
            FileInfo.writer.println("JetPlane#" + name + "(" + craftId + "): I'm freezing my balls");
        }*/


        if(Height == 0){
            FileInfo.writer.println("Jetpalne#" + name + "(" + craftId + "): landing.");
            weatherTower.unregister(this);
            FileInfo.writer.println("JetPlane#" + name + "(" + craftId +"): unregistered from the weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower)
    {

        long craftId = this.getId();

        
        
        FileInfo.writer.println("Tower says: JetPlane#" + name + "(" + craftId + "): registered to the weather tower.");
        this.weatherTower = weatherTower;
        try{
            this.weatherTower.register(this);
        }
        catch(Exception e){
            System.out.println("no flyable");
        }
    }
}
