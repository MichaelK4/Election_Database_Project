package Elections;

import java.io.Serializable;

public class Voter implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected int id;
	protected int birthYear;
	protected int KalpiID;
	protected boolean isIsolation = false;
	protected boolean hasSuit = false;
	protected boolean wantToVote;
	protected int numOfDaysSick;
	
	public Voter(String name, int id , int birthYear, boolean isIsolation,boolean hasSuit, int KalpiID, int numOfDaysSick) {
		this.name = name;
		this.id = id;
		this.birthYear = birthYear;
		this.KalpiID = KalpiID;
		this.isIsolation = isIsolation;
		this.hasSuit = hasSuit;
		this.wantToVote = false;
	    this.numOfDaysSick = numOfDaysSick;
	}
	//copyCons//
	public Voter(Voter other) {
		this.KalpiID = other.KalpiID;
		this.name = other.name;
		this.birthYear = other.birthYear;
		this.isIsolation = other.isIsolation;
		this.hasSuit = other.hasSuit;
		this.id = other.id;
		this.wantToVote = other.wantToVote;
		this.numOfDaysSick = other.numOfDaysSick;
	} 
	//GET SET//
	public String getName() {
		return name;
	}
	public boolean getHasSuit() {
		return hasSuit;
	}
	public int getId() {
		return id;
	}
	public int getNumOfDaysSick() {
		return this.numOfDaysSick;
	}
	public int getKalpiID() {
		return this.KalpiID;
	}
	public boolean getIsIsolation() {
		return isIsolation;
	}
	public int getBirthYear() {
		return birthYear;
	}	
	public void setKalpiID(int KalpiID) {
		this.KalpiID = KalpiID;
	}
	public void setWantToVote(boolean wantToVote) {
		this.wantToVote = wantToVote;
	}
	//Methods//	
	private String convertFromBooleanToString(boolean isIsolation) {
		if (isIsolation == true) {
			return "True";
		}else {
			return "False";
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Voter))
			return false;
		
		if (!super.equals(obj))
			return false;
		
		Voter temp = (Voter)obj;
		return id == temp.id;
	}
	//toString//    
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("----------------------------------\r\n");
		sb.append("Name: " + this.name + "\r\n");
		sb.append("ID: " + this.id + "\r\n");
		sb.append("Birth Year: " + this.birthYear + "\r\n");
		sb.append(this.name + " is isolated? " + convertFromBooleanToString(this.isIsolation) + "\r\n");
		sb.append("Has suit? " + convertFromBooleanToString(this.getHasSuit()));
		sb.append("\n");
		sb.append("Kalpi ID: " + this.KalpiID);
		sb.append("\n");
		return sb.toString();
	}
}