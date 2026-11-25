public class WeatherReading {
    private final String state;
    private final boolean largeSwing;

    public WeatherReading(String State, int high, int low){
        this.state = state;
        this.largeSwing = (high - low) >= 15;
    }
    
    public String getState(){
        return state;
    }

    public boolean hasLargeSwing(){
        return largeSwing;
    }


}//END OF WEATHER-READING-CLASS
