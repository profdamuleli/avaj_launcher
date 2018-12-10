package avaj.simulator;

import java.io.*;

import static java.lang.System.exit;

public class FileInfo {
    static PrintWriter writer;
    static int simulationLoop;

    public static void main(String[] args) throws Exception {

        AircraftFactory aircraftFactory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();

        try {
            String inputFile = args[0];
            if (inputFile.length() < 1) throw new Exception();

            File outputFile = new File("simulation.txt");
            writer = new PrintWriter(outputFile);
            if (outputFile.exists()) writer.print("");

            BufferedReader boy = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            String nextLine;
            int line = 0;

            while ((nextLine = boy.readLine()) != null) {
                if (line == 0) {
                    simulationLoop = (Integer.parseInt(nextLine));
                    if (simulationLoop < 0) throw new Exception();
                } else {
                    String[] nowLine = nextLine.split(" ");
					System.out.println(nowLine[0]);
                    if (nowLine.length == 5)
                        aircraftFactory.newAircraft(nowLine[0], nowLine[1], Integer.parseInt(nowLine[2]), Integer.parseInt(nowLine[3]), Integer.parseInt(nowLine[4])).registerTower(weatherTower);
                    else throw new IOException();
                }
                line++;
            }
            boy.close();
        } catch (Exception e) {
            System.out.println("Wrong input! Try fixing your input file first.");
            exit(1);
        }

        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        while (simulationLoop > 0){
            weatherTower.changeWeather();
            simulationLoop--;
        }

        writer.close();
    }
}
