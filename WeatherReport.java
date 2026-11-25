import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public WeatherReport(String filename) throws FileNotFoundException{
        try (Scanner input = new Scanner(new File(filename))){
            if (input.hasNextLine()){
                input.nextLine(); // Skip the column headings.
            }

            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.isBlank()){
                    continue;
                }

                String[] fields = line.split(",");
                int high = Integer.parseInt(fields[5].trim());//Max
                int low = Integer.parseInt(fields[6].trim());//Min
                String state = fields[10].trim();

                readings.add(new WeatherReading(state, high, low));
            }
        }
    }
    
    public List<StateSummary> computeByList(){
        List<StateSummary> results = new LinkedList<>();

        for (WeatherReading reading : readings) {
            String state = reading.getState();
            StateSummary match = null;

            for (StateSummary summary : results) {
                if (summary.getState().equals(state)) {
                    match = summary;
                    break;
                }
            }

            if (match == null) {
                match = new StateSummary(state);
                results.add(match);
            }
            match.recordReading(reading.hasLargeSwing());
        }

        return results;
    }

    public TreeMap<String, StateSummary> computeByTree() {
        TreeMap<String, StateSummary> results = new TreeMap<>();

            for (WeatherReading reading : readings) {
            String state = reading.getState();
            StateSummary summary = results.get(state);

            if (summary == null){
                summary = new StateSummary(state);
                results.put(state,summary);
            }

            summary.recordReading(reading.hasLargeSwing());

        }
        return results;
    }

    public static void main(String[] args) throws FileNotFoundException{
        WeatherReport sample = new WeatherReport();

        System.out.println("Readings loaded: " + sample.readings.size());

        for(WeatherReading reading : sample.readings){
            if (reading.hasLargeSwing()){
                System.out.println("Qualifies: " + reading.getState());
            }
        }

        String filename = "weather.txt";
        if (args.length > 0){
            filename = args[0];
        }

        WeatherReport fileReport = new WeatherReport(filename);
        System.out.println("File readings loaded: " + fileReport.readings.size());

        System.out.println("Sample list: " + sample.computeByList());
        System.out.println("File list: " + fileReport.computeByList());
        System.out.println("Sample tree: " + sample.computeByTree());
        System.out.println("File tree: " + fileReport.computeByTree());


        }


}

