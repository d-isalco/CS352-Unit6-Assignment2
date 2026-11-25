public class StateSummary {
    private final String state;
    private int totalReports;
    private int qualifyingReports;

    public StateSummary(String state){
        this.state = state;
    }
    
    public String getState(){
        return state;
    }

    public void recordReading(boolean qualifies){
        totalReports++;
        if (qualifies){
            qualifyingReports++;
        }
    }


    public double getPercentage(){
        if (totalReports == 0){
            return 0.0;
        }
        return 100.0 * qualifyingReports / totalReports;
    }

    @Override 
    public String toString() {
        double percentage = getPercentage();
        return String.format("%s: %d/%d (%.2f%%)",
            state, qualifyingReports, totalReports, percentage);
    }


}
