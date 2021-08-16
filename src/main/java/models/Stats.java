package models;

public class Stats {

	private String country;
	private String state;
	private int latestCases;
	private int change;
	public int getChange() {
		return change;
	}
	public void setChange(int change) {
		this.change = change;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		if(state.equals("")){
			this.state = "--";
		}
		else
			this.state = state;
	}
	public int getLatestCases() {
		return latestCases;
	}
	public void setLatestCases(int latestCases) {
		this.latestCases = latestCases;
	}
	@Override
	public String toString() {
		return "Stats [country=" + country + ", state=" + state + ", latestCases=" + latestCases + "]";
	}
	public Stats(){
		
	}
}
