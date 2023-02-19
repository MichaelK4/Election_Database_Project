package Elections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

public class Kalpi implements Serializable{
	public enum KalpiType {CoronaKalpi, ArmyKalpi, NormalKalpi, ArmyCoronaKalpi};

	private static int ID = 1000;

	private KalpiType type;
	private int KalpiId = 0;
	private String KalpiLocation;
	private Set<Voter> kalpiVoters;
	private Vector<String> votesInKalpi;
	private int numOfVotersPerKalpi;
	private int numOfVotes;

	public Kalpi(KalpiType type, String kalpiLocation) {
		this.type = type;
		this.KalpiId = ID++;
		this.KalpiLocation = kalpiLocation;
		this.kalpiVoters = new Set<Voter>();	
		this.votesInKalpi = new Vector<String>();
		this.numOfVotersPerKalpi = 0;
		this.numOfVotes = 0;
	}
	// copyCons
	public Kalpi(Kalpi other) throws Exception {
		Iterator<? extends Voter> iter = other.kalpiVoters.iterator();
		while(iter.hasNext()) {
			this.kalpiVoters.add((Voter) iter);
		}

		for (int i = 0; i < other.numOfVotes; i++) {
			this.votesInKalpi.add(i, "");
		}
		this.numOfVotersPerKalpi = other.numOfVotersPerKalpi;
		this.KalpiId = other.KalpiId;
		this.KalpiLocation = other.KalpiLocation;
		this.type = other.type;
		this.numOfVotes = other.numOfVotes;
	}
	// GET//
	public KalpiType getType() {
		return type;
	}
	public int getKalpiId() {
		return KalpiId;
	}
	public String getKalpiLocation() {
		return KalpiLocation;
	}
	public Set<Voter> getVoters() {
		return kalpiVoters;
	}
	public int getNumOfVoters() {
		return numOfVotersPerKalpi;
	}
	public int getNumOfVotes() {
		return numOfVotes;
	}
	public Vector<String> getVotesInKalpi() {
		return votesInKalpi;
	}
	// SET//
	public void setVotesInKalpi(Vector<String> votesInKalpi) {
		this.votesInKalpi = votesInKalpi;
	}
	public void setNumOfVotes(int numOfVotes) {// case 8
		this.numOfVotes = numOfVotes;
	}
	public void setNumOfVoters(int raise) {
		this.numOfVotersPerKalpi += raise;
	}
	public void setKalpiVoters(Set<Voter> votersArray) {
		this.kalpiVoters = votersArray;	
	}
	// Methods//
	public String TypeString(KalpiType type) {
		String typeOfKalpi;
		if (type == KalpiType.NormalKalpi) {
			typeOfKalpi = "Normal Kalpi";
			return typeOfKalpi;
		}
		if (type == KalpiType.CoronaKalpi) {
			typeOfKalpi = "Corona Kalpi";
			return typeOfKalpi;
		}
		if (type == KalpiType.ArmyKalpi) {
			typeOfKalpi = "Army Kalpi";
			return typeOfKalpi;
		}
		if (type == KalpiType.ArmyCoronaKalpi) {
			typeOfKalpi = "Army Corona Kalpi";
			return typeOfKalpi;
		}
		return null;
	}
	public double getPercentOfVoters() {
		double percentage = 100.0 * this.getNumOfVotes() / this.getNumOfVoters();
		return percentage;
	}
	private String convertFromBooleanToString(boolean isSomething) {
		if (isSomething == true) {
			return "True";
		}else {
			return "False";
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Kalpi))
			return false;
		
		if (!super.equals(obj))
			return false;
		
		Kalpi temp = (Kalpi)obj;
		return KalpiId == temp.KalpiId;
	}
	// toString//
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("----------------------------------\r\n");
		sb.append("Kalpi ID: " + getKalpiId() + "\r\n");
		sb.append("Kalpi address: " + getKalpiLocation() + "\r\n");
		sb.append("Kalpi type: " + TypeString(this.getType()) + "\r\n");
		sb.append("Number of voters: " + this.getNumOfVoters() + "\r\n");
		sb.append("Voters List: \r\n");
		sb.append("----------------------------------\r\n");
		for (int i = 0; i < this.numOfVotersPerKalpi; i++) {
			sb.append("Voter Name: " + kalpiVoters.collection.elementAt(i).getName() + "\r\n");
			sb.append("Voter ID: "+ kalpiVoters.collection.elementAt(i).getId() + "\r\n");
			sb.append("Voter Birth Year: " + kalpiVoters.collection.elementAt(i).getBirthYear() + "\r\n");
			if (kalpiVoters.collection.elementAt(i).getIsIsolation() == true && kalpiVoters.collection.elementAt(i).getHasSuit() == true) { 
				sb.append("Voter Number of days sick: " + (kalpiVoters.collection.elementAt(i)).getNumOfDaysSick() + "\r\n");
			}
			if (kalpiVoters.collection.elementAt(i) instanceof SoldierVoter) {
				sb.append("Is Carring weapon? " + convertFromBooleanToString(((SoldierVoter) kalpiVoters.collection.elementAt(i)).getIsCarryWeapon())); 
				sb.append("\r\n");
			}
			if (kalpiVoters.collection.elementAt(i) instanceof Candidate) {
				sb.append("Party name of candidate: " + ((Candidate)kalpiVoters.collection.elementAt(i)).getPartyName());
				sb.append("\r\n");
			}
			sb.append("\r\n");
		}
		sb.append("----------------------------------");
		return sb.toString();
	}
}