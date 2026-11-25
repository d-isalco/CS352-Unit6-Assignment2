import java.io.FileNotFoundException;
/**
 * @author John Cigas
 * David Montenegro
 * 25 November 2020
 * CS352-Unit 6 Assignment 2
 */
public class WeatherDriver {

	public static void main(String[] args) {
		System.out.println("Starting simulation");

		WeatherReport report = new WeatherReport();
		report.displayTemperatures();

		
		System.out.println("Test list sorted by City? "+
			report.isSortedByCity());

		System.out.println("Test list sorted by high? " +
			report.isSortedByHigh());
		
		
		System.out.println("\nSorting test list by City...");

		//Sort the LinkedList alphabetically
		report.sortWithCollections("City");

		//Display the sorted list
		report.displayTemperatures();

		//Verify the sorting worked
		System.out.println("Sorted by City? "+
			report.isSortedByCity());
		
		System.out.println("\nSorting test list by High...");

		//Create a new report with the original nine cities
		WeatherReport highReport = new WeatherReport();

		//Sort the LinkedList by high temp
		highReport.sortWithCollections("High");

		//Display the sorted temp
		highReport.displayTemperatures();

		//Verify the sorting worked
		System.out.println("Sorted by High? " +
			highReport.isSortedByHigh());

		System.out.println("\nTesting Merge Sort by City...");

		//Create a new report with the original nine cities
		WeatherReport mergeCityReport = new WeatherReport();

		//verify the list is not sorted before the test
		System.out.print("Before Merge Sort: " +
			mergeCityReport.isSortedByCity()+"\n");

		//Sort using custom merge sort
		mergeCityReport.sortWithMerge("City");

		//Display the sorted records
		mergeCityReport.displayTemperatures();

		//verify the results
		System.out.println("After Merge Sort: " + 
			mergeCityReport.isSortedByCity() + "\n");

		 System.out.println("\nTesting Merge Sort by High...");

		 //Create a new report with the original nine cities
		 WeatherReport mergeHighReport = new WeatherReport();

		 //Check if the list is sorted before merge sort
		 System.out.println("Before Merge Sort: "+
			mergeHighReport.isSortedByHigh());

		//Sort by high temparature using merge sort
		mergeHighReport.sortWithMerge("High");

		//Display the sorted records
		mergeHighReport.displayTemperatures();

		//verify that the sorting worked
		System.out.println("After Merger Sort: " + 
			mergeHighReport.isSortedByHigh() + "\n");

		try{
			WeatherReport fileReport = new WeatherReport("weather.txt");

			System.out.println("File loaded successfully");
			System.out.println("Total weather records: " + 
				fileReport.getRecordCount());

			System.out.println("File sorted by City? " + 
				fileReport.isSortedByCity());
			
			System.out.println("File sorted by High? " +
				fileReport.isSortedByHigh());

			System.out.println("\nPreparing performance tests...");

			//FileReport will be sued for Collections sort by City

			//Create three additional reports with identical data
			WeatherReport collectionsHighFile = new WeatherReport("weather.txt");
			WeatherReport mergeCityFile = new WeatherReport("weather.txt");
			WeatherReport mergeHighFile = new WeatherReport("weather.txt");

			//Verify all four lists have the same records
			System.out.println("Collections City " + fileReport.getRecordCount());
			System.out.println("Collections high: " + collectionsHighFile.getRecordCount());
			System.out.println("Merge City: " + mergeCityFile.getRecordCount());
			System.out.println("Merge High: " + mergeHighFile.getRecordCount());

			System.out.println("\nChecking original sorting order...");
			System.out.println("Collections City initially sorted? "+
				fileReport.isSortedByCity());
			System.out.println("Collections High initially sorted? "+
				collectionsHighFile.isSortedByHigh());
			System.out.println("Merge City initially sorted? "+
				mergeCityFile.isSortedByCity());
			System.out.println("Merge High initially sorted? "+
				mergeHighFile.isSortedByHigh());

			System.out.println("\nPerformance Test 1: Collections Sort by City");

			//Record the starting time
			long startTime = System.nanoTime();

			//Sort the LinkedList by City
			fileReport.sortWithCollections("City");

			//Record the ending time
			long endTime = System.nanoTime();

			//Calculate execution time in seconds
			double executionTime = (endTime - startTime) / 1_000_000_000.0;

			//Display the resuls
			System.out.println("Execution Time: " +
				executionTime + " seconds");
			
			//Verify the LinkedList was sorted correctly
			System.out.println("Sorted by City: " + 
			fileReport.isSortedByCity());

			System.out.println("\nPerformance Test 2: Collections Sort by High");

			//Record the starting time
			long startTimeHigh = System.nanoTime();

			//Sort the LinkedList by High Temperature
			collectionsHighFile.sortWithCollections("High");

			//Record the ending Time
			long endTimeHigh = System.nanoTime();

			//Calculate execution time in seconds
			double executionTimeHigh = 
				(endTimeHigh - startTimeHigh) / 1_000_000_000.0;

			//Display the execution time
			System.out.println("Execution Time: " +
				executionTimeHigh + " seconds");

			//Verify the LinkedList was sorted correctly
			System.out.println("Sorted by High? " + 
				collectionsHighFile.isSortedByHigh());

			System.out.println("\nPerformance Test 3: Merge Sort by City");

			//Record the starting time
			long startTimeMergeCity = System.nanoTime();

			//Sort the LinkedList using custon Merge sort
			mergeCityFile.sortWithMerge("City");

			//Record the ending time
			long endTimeMergeCity = System.nanoTime();

			//Calculate execution time in seconds
			double executionTimeMergeCity = 
				(endTimeMergeCity - startTimeMergeCity) / 1_000_000_000.0;
			
			//Display the execution time
			System.out.println("Execution Time: " + 
				executionTimeMergeCity + " seconds");

			//Verify the LinkedList was sorted correctly
			System.out.println("Sorted by City? " + 
				mergeCityFile.isSortedByCity());

			System.out.println("\nPerformance Test 4: Merge Sort by High");

			//Record the starting time
			long startTimeMergeHigh = System.nanoTime();

			//Sort the LinkedList using custon Merge sort
			mergeHighFile.sortWithMerge("High");

			//Record the ending time
			long endTimeMergeHigh = System.nanoTime();

			//Calculate execution time in seconds
			double executionTimeMergeHigh = 
				(endTimeMergeHigh - startTimeMergeHigh) / 1_000_000_000.0;
			
			//Display the execution time
			System.out.println("Execution Time: " + 
				executionTimeMergeHigh + " seconds");

			//Verify the LinkedList was sorted correctly
			System.out.println("Sorted by High? " + 
				mergeHighReport.isSortedByHigh());

		}

		catch (FileNotFoundException e){
			System.out.println("Cannot find weather.txt");
		}

		/*
		String fileName = "weather.txt";
		try { 
			WeatherReport wr = new WeatherReport(fileName);
			System.out.println(wr.isSortedByCity());			
		} 
		catch (FileNotFoundException e ) {
			System.out.println("Can't find file " + fileName); 
		}
		catch (Exception e) {
			System.out.println("Other error: " + e.getMessage());
		}
		*/
		System.out.println("Finished simulation");
	}

}//Final Commit
