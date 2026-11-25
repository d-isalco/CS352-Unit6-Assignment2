/**
 * @author John Cigas
 *
 * Stores low and high temperature data for a city/state 
 */
public class Temperature {
	private String city;
	private String state;
	private int low;
	private int high;

	
	/* Add appropriate instance attributes, constructors, and accessor/mutator methods */
	public Temperature(String city, String state, int low, int high){
		this.city = city;
		this.state = state;
		this.low = low;
		this.high = high;
	}

	//getter methods
	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getLow() {
		return low;
	}

	public int getHigh() {
		return high;
	}

	//setter methods
	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	//Differential method
	public int Differential() {
		return Math.abs(high - low);
	}
}//Final Commit
