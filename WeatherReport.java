import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author John Cigas
 * David Montenegro 25 November 2020
 * CS352-Unit 6 Assignment 2
 */

public class WeatherReport {
    private final List<WeatherReading> readings = new LinkedList<>();

    public WeatherReport(){
        readings.add(new WeatherReading("Florida", 72, 57));  // Orlando
        readings.add(new WeatherReading("Alabama", 46, 32));  // Birmingham
        readings.add(new WeatherReading("Alaska", 38, 29));   // Anchorage
        readings.add(new WeatherReading("Florida", 71, 59));  // Tampa
        readings.add(new WeatherReading("Alabama", 47, 31));  // Huntsville
        readings.add(new WeatherReading("Alaska", 44, 31));   // Annette
        readings.add(new WeatherReading("Alabama", 51, 41));  // Mobile
        readings.add(new WeatherReading("Florida", 82, 70));  // Miami
        readings.add(new WeatherReading("Alabama", 52, 38));  // Montgomery
    }
    public static void main(String[] args) {
        WeatherReport sample = new WeatherReport();
        System.out.println("Readings loaded: " + sample.readings.size());

        for(WeatherReading reading : sample.readings){
            if (reading.hasLargeSwing()){
                System.out.println("Qualifies: " + reading.getState());
            }
        }

    }

}