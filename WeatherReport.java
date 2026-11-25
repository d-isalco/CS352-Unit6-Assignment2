import java.io.File;
import java.util.Collections;
import java.util.Scanner;
import java.util.Comparator;
import java.util.LinkedList;
import java.io.FileNotFoundException;
/**
 * @author John Cigas
 * David Montenegro 25 November 2020
 * CS352-Unit 6 Assignment 2
 */
public class WeatherReport {
    private LinkedList<Temperature> temperatures;
        
    // Constructor to create a WeatherReport object with a list of temperatures
    public WeatherReport() {
        temperatures = new LinkedList<Temperature>();

        temperatures.add(new Temperature("Tampa", "FL", 59, 71));
        temperatures.add(new Temperature("Miami", "FL", 70, 82));
        temperatures.add(new Temperature("Chicago", "IL", 20, 30));
        temperatures.add(new Temperature("Denver", "CO", 12, 40));
        temperatures.add(new Temperature("Seattle", "WA", 28, 42));
        temperatures.add(new Temperature("Orlando", "FL", 57, 72));
        temperatures.add(new Temperature("Phoenix", "AZ", 45, 69));
        temperatures.add(new Temperature("Atlanta", "GA", 36, 48));
        temperatures.add(new Temperature("Los Angeles", "CA", 45, 63));
    }
        
    public WeatherReport(String fileName) throws FileNotFoundException{
        temperatures = new LinkedList<Temperature>();

        Scanner input = new Scanner(new File(fileName));

        if (input.hasNextLine()) {
            String header = input.nextLine();
            System.out.println("Header: " + header);
        }

        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] data = line.split(",");

            String city = data[1];
            String state = data[10];

            int high = Integer.parseInt(data[5]);
            int low = Integer.parseInt(data[6]);

            Temperature temp = new Temperature(city, state, low, high);
            temperatures.add(temp);
        }

        input.close();
    }

    public void displayTemperatures() {
        for (Temperature temp : temperatures) {
            System.out.println(temp.getCity() + ", " +
                temp.getState() + " | Low: " +
                temp.getLow() + " | High: " + 
                temp.getHigh());
        }
    }

    public int getRecordCount(){
		return temperatures.size();
	}


    public boolean isSortedByCity(){
        Temperature previous = null;

        for (Temperature current : temperatures){
            if (previous != null){
                if (previous.getCity().compareTo(current.getCity()) > 0 ){
                    return false;
                }
            }
            previous = current;                

        }
        return true;
    }

    public boolean isSortedByHigh() {
        Temperature previous = null;

        for(Temperature current : temperatures){
            if (previous != null){
                if(previous.getHigh() > current.getHigh()){
                    return false;
                }
            }           
            previous = current;    
        }
        return true;
    }

    //Determines whether to sort by city or high teperature
    public void sortWithCollections(String by){
        if(by.equalsIgnoreCase("City")){
            Collections.sort(temperatures, new SortByCity());
        }
        else if(by.equalsIgnoreCase("High")){
            Collections.sort(temperatures, new SortByHigh());
        }
    }

    //Determines whether to sort by city or high teperature
    public void sortWithMerge(String by){
        Comparator<Temperature> comparator;

        if (by.equalsIgnoreCase("City")){
            comparator = new SortByCity();
        }
        else if (by.equalsIgnoreCase("High")){
            comparator = new SortByHigh();
        }
        else{
            System.out.println("Invalid sorting option.");
            return;
        }

        //Sort the LikedList using our recursive merge sort
        temperatures = mergeSort(temperatures, comparator);
    }

    //Recursive merge sort helper method
    private LinkedList<Temperature> mergeSort(
        LinkedList<Temperature> list, 
        Comparator<Temperature> comparator){
            
        //Base case: A list of 0 or 1 is already sorted
        if(list.size() <= 1 ){
            return list;
        }

        //Find the middle of the list
        int middle = list.size() / 2;

        //Create two empty LinkedLists
        LinkedList<Temperature> left = new LinkedList<>();
        LinkedList<Temperature> right = new LinkedList<>();

        //Divide the original list into two halves
        int index = 0;
        
        for(Temperature temp : list){
            if (index < middle){
                left.add(temp);
            }
            else {
                right.add(temp);
            }
            index++;
        }
            
        //Recursively process the left and right halves
        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);
            
        //Merge the two sorted halves and return the final result
        return merge(left, right, comparator);
    }    

    private LinkedList<Temperature> merge(
            LinkedList<Temperature> left,
            LinkedList<Temperature> right,
            Comparator<Temperature> comparator){
        
        //Create an empty list for the sorted results
        LinkedList<Temperature> merged = new LinkedList<>();

        //Compare the list objects from both list
        while (!left.isEmpty() && !right.isEmpty()) {

            if (comparator.compare(
                left.getFirst(), right.getFirst()) <=0){
                    merged.add(left.removeFirst());
                }
            else{
                    merged.add(right.removeFirst());
                }
        }
        
        //Add any remaining objects from the left list
        while (!left.isEmpty()) {
            merged.add(left.removeFirst());            
        }

        //Add any remaining object from the right list
        while (!right.isEmpty()) {
            merged.add(right.removeFirst());            
        }
    
        return merged;
    }
}


/*
 * Comparator to sort by the city stored in a Temperature object
 */
class SortByCity implements Comparator<Temperature> {
    // Used for sorting in ascending order of City
    public int compare(Temperature t1, Temperature t2)
    {
        return t1.getCity().compareTo(t2.getCity());
    }
}

/*
 * Comparator to sort by the high temperature stored in a Temperature object
 */
class SortByHigh implements Comparator<Temperature> {
    // Used for sorting in ascending order of high temperature
    public int compare(Temperature t1, Temperature t2)
    {
        return t1.getHigh() - t2.getHigh();
    }

}//Final committ