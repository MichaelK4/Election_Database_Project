package Elections;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Vector;

public class Party implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public enum flag {LEFT, MIDDLE, RIGHT};

	private LocalDate date;
	private String partyName;
	private flag type;
	private int numOfCandidatesPerParty;
	private Vector<Candidate> partyCandidates;
	private int numOfVotes;
	private int year, month, day;

	public Party(String partyName, flag type, int year, int month, int day) {
		this.type = type;
		this.partyName = partyName;
		this.year = year;
		this.month = month;
		this.day = day;
		this.date = LocalDate.of(year, month, day);
		this.partyCandidates = new Vector<Candidate>();
		this.numOfCandidatesPerParty = 0;
		this.numOfVotes = 0;
	}
	//copyCons//
	public Party(Party other) {
		this.partyName = other.partyName;
		this.partyCandidates = other.partyCandidates;
		for (int i = 0; i < other.numOfCandidatesPerParty; i++) {
			this.partyCandidates.add(i, other.partyCandidates.elementAt(i));
		}
		this.numOfCandidatesPerParty = other.numOfCandidatesPerParty;
		this.year = other.year;
		this.month = other.month;
		this.day = other.day;
		this.date = other.date;
		this.type = other.type;
		this.numOfVotes = other.numOfVotes;
	}
	//GET//
	public int getNumOfCandidatesPerParty() {
		return numOfCandidatesPerParty;
	}
	public LocalDate getDate() {
		return date;
	}
	public String getPartyName() {
		return this.partyName;
	}
	public flag getType() {
		return type;
	}
	public int getNumOfVotes()
	{
		return this.numOfVotes;
	}
	public Vector<Candidate> getCandidates() {
		return partyCandidates;
	}
	//SET//
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setNumOfVotes()
	{
		this.numOfVotes++;
	}
	
	public void setNumOfVotesDB(int numOfVotes)
	{
		this.numOfVotes = numOfVotes;
	}
	
	public void setNumOfCandidates(int up) {
		this.numOfCandidatesPerParty += up;
	}
	public void setPartyCandidates(Vector<Candidate> candidateArray) {
		this.partyCandidates = candidateArray;
	}
	//Methods//
	public String TypeString(flag type) {
		String typeOfKalpi;
		if (type == flag.RIGHT) {
			typeOfKalpi = "Right";
			return typeOfKalpi;
		}
		if (type == flag.MIDDLE) {
			typeOfKalpi = "Middle";
			return typeOfKalpi;
		}
		if (type == flag.LEFT) {
			typeOfKalpi = "Left";
			return typeOfKalpi;
		}
		return null;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Party))
			return false;
		
		if (!super.equals(obj))
			return false;
		
		Party temp = (Party)obj;
		return partyName == temp.partyName;
	}
    //toString
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("----------------------------------\r\n");
		sb.append("Party Name: " + this.partyName + "\r\n");
		sb.append("Date of creation: " + this.date + "\r\n");
		sb.append("Party flag: " + TypeString(this.getType()) + "\r\n");
		sb.append("Number of votes: " + getNumOfVotes() + "\r\n");
		sb.append("Number of candidates: " + this.numOfCandidatesPerParty + "\r\n");
		sb.append("\n");
		sb.append("Candidates List: \r\n");
		for (int i = 0; i < getNumOfCandidatesPerParty(); i++) {
			sb.append("Candidate Name: " + this.partyCandidates.elementAt(i).getName() + "\r\n");	
			sb.append("Candidate ID: " + this.partyCandidates.elementAt(i).getId() + "\r\n");
			sb.append("Candidate Birth Year: " + this.partyCandidates.elementAt(i).getBirthYear() + "\r\n");
			sb.append("Candidate's number in Party: " + (i + 1) + "\r\n");
			sb.append("\n");	
		}
		sb.append("----------------------------------");
		return sb.toString();
	}
	public int getDay() {
		return this.day;
	}
	public int getMonth() {
		
		return this.month;
	}
	public int getYear() {
		return this.year;
	}
}