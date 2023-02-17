package Elections;

import java.io.Serializable;
//import java.time.LocalDate;
import java.util.Arrays;
import java.util.InputMismatchException;
//import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import Elections.Kalpi.KalpiType;

public class Elections implements Serializable {


	private static final long serialVersionUID = 1L;

	private Vector<Kalpi> kalpies;
	private Set<Voter> voters;
	private Vector<Party> parties;
	private Vector<Candidate> candidates;
	private Vector<String> votes;

	private int numOfKalpies;
	private int numOfVoters;
	private int numOfParties;
	private int numOfCandidates;
	private int numOfVotes;
	private int year;
	private int month;
	private String elected;

	public Elections() {
		this.kalpies = new Vector<Kalpi>();
		this.voters = new Set<Voter>();
		this.parties = new Vector<Party>();
		this.candidates = new Vector<Candidate>();
		this.votes = new Vector<String>();
		this.numOfKalpies = 0;
		this.numOfVoters = 0;
		this.numOfParties = 0;
		this.numOfCandidates = 0;
		this.numOfVotes = 0;
		this.year = 0;
		this.month = 0;
		this.elected = "";
	}
	//copyCons
	public Elections(Elections other) throws Exception {
		for (int i = 0; i < other.kalpies.size(); i++) {
			this.kalpies.add(i, new Kalpi(other.kalpies.elementAt(i)));
		}
		for (int i = 0; i < other.voters.size; i++) {
			this.voters.add(new Voter(other.voters.collection.elementAt(i)));
		}
		for (int i = 0; i < other.parties.size(); i++) {
			this.parties.add(i, new Party(other.parties.elementAt(i)));
		}
		for (int i = 0; i < other.candidates.size(); i++) {
			this.candidates.add(i, new Candidate(other.candidates.elementAt(i)));
		}
		for (int i = 0; i < other.votes.size(); i++) {
			this.votes.add(i, new String(other.votes.elementAt(i)));
		}
		this.kalpies = other.kalpies;
		this.voters = other.voters;
		this.parties = other.parties;
		this.candidates = other.candidates;
		this.votes = other.votes;

		this.numOfCandidates = other.numOfCandidates;
		this.numOfKalpies = other.numOfKalpies;
		this.numOfParties = other.numOfParties;
		this.numOfVoters = other.numOfVoters;
		this.numOfVotes = other.numOfVotes;
		this.year = other.year;
		this.month = other.month;
		this.elected = other.elected;

	}
	//add Kalpi//
	public boolean addKalpi(Kalpi k1) throws Exception {
		if(kalpies.contains(k1)) {
			return false;
		}
		kalpies.add(new Kalpi(k1));
		this.numOfKalpies++;
		return true;
	}
	//add Party//
	public boolean addParty(Party p1) {
		if (parties.contains(p1)) {
			return false;	
		}
		parties.add(new Party(p1));
		this.numOfParties++;
		//p1.setNumOfCandidates(p1.getNumOfCandidatesPerParty());
		return true;
	}
	//add Voter//
	public boolean addVoter(Voter v1) throws Exception {
		//LocalDate localDate = LocalDate.now();
		int year = getYear();
		if (year - v1.getBirthYear() < 18) {
			return false;
		}
		if (this.voters.contains(v1)) {
			return false;
		}
		if (year - v1.getBirthYear() >= 22) {
			if (v1 instanceof Candidate) {
				this.voters.add(new Candidate((Candidate)v1));
				this.numOfVoters++;
				addToKalpi(v1);
				//	if (numOfCandidates * numOfParties < 120) {
				this.candidates.add(new Candidate((Candidate) v1));
				this.numOfCandidates++;
				addToParty((Candidate) v1);
				//				}else {
				//					System.out.println("The Knesset is full");
				//				}
				return true;
			}
		}
		if (v1 instanceof CoronaVoter) {
			this.voters.add(new CoronaVoter((CoronaVoter)v1));
			this.numOfVoters++;
			addToKalpi(v1);
			return true;
		}
		if (v1 instanceof SoldierVoter) {
			this.voters.add(new SoldierVoter((SoldierVoter) v1));
			this.numOfVoters++;
			addToKalpi(v1);
			return true;
		}
		this.voters.add(new Voter(v1));
		this.numOfVoters++;
		addToKalpi(v1);
		return true;
	}
	// Check which Kalpi type suits the Voter//
	public boolean addToKalpi(Voter v1) throws Exception {
		Vector<Kalpi> kalpiVoter = new Vector<Kalpi>();
		//LocalDate localDate = LocalDate.now();
		boolean suit = v1.getHasSuit();
		boolean isolation = v1.getIsIsolation();
		int birthYear = v1.getBirthYear();
		int year = getYear();

		if (year - birthYear <= 21) {
			if (isolation == true) {
				if (suit == true) {
					kalpiVoter = getKalpiByType(KalpiType.values()[3]);
					for (int i = 0; i < this.numOfKalpies; i++) {
						if (v1.getKalpiID() == kalpiVoter.elementAt(i).getKalpiId()) {
							Kalpi k1 = kalpiVoter.elementAt(i);
							return addToKalpiVoters(k1, v1);
						}
					}
				}else {
					return false;
				}

			}else {
				kalpiVoter = getKalpiByType(KalpiType.values()[1]);
				for (int i = 0; i <this.numOfKalpies; i++) {
					if (v1.getKalpiID() == kalpiVoter.elementAt(i).getKalpiId()) {///testtt
						Kalpi k1 = kalpiVoter.elementAt(i);
						return addToKalpiVoters(k1, v1);
					}
				}
			}

		}else {
			if (isolation == true) {
				if (suit == true) {
					kalpiVoter = getKalpiByType(KalpiType.values()[0]);
					for (int i = 0; i < numOfKalpies; i++) {
						if (v1.getKalpiID() == kalpiVoter.elementAt(i).getKalpiId()) {
							Kalpi k1 = kalpiVoter.elementAt(i);
							return addToKalpiVoters(k1, v1);
						}
					}

				}else {
					return false;
				}
			}else {
				kalpiVoter = getKalpiByType(KalpiType.values()[2]);
				for (int i = 0; i < numOfKalpies; i++) {
					if (v1.getKalpiID() == kalpiVoter.elementAt(i).getKalpiId()) {
						Kalpi k1 = kalpiVoter.elementAt(i);
						return addToKalpiVoters(k1, v1);
					}
				}

			}

		}
		return false;
	}
	// add Voter to a specific Kalpi//
	public boolean addToKalpiVoters(Kalpi kalpiVoter, Voter v1) throws Exception  {
		Set<Voter> votersArray = new Set<Voter>();
		// index for voters
		for (int i = 0; i < this.numOfVoters; i++) // loop for all voters and checksId
		{
			if (voters.collection.elementAt(i).getKalpiID() == kalpiVoter.getKalpiId()) {
				votersArray.add(voters.collection.elementAt(i));
			}
		}
		kalpiVoter.setKalpiVoters(votersArray);
		kalpiVoter.setNumOfVoters(1);

		return true;
	}
	// add Candidate to Party//
	public boolean addToParty(Candidate c1) {// TEST
		for (int i = 0; i < this.numOfParties; i++) {
			if (c1.getPartyName().equals(parties.elementAt(i).getPartyName())) {
				return addCandidateToParty(this.parties.elementAt(i), c1);
			}
		}
		return false;
	}
	// add Candidate to a specific Party//
	private boolean addCandidateToParty(Party p1, Candidate c1) {
		//Candidate[] candidateArray = new Candidate[p1.getMAX_NUM_OF_CANDIDATES_PER_PARTY()];
		Vector<Candidate> candidateArray = new Vector<Candidate>();
		int counter = 0;
		for (int i = 0; i < this.numOfCandidates; i++) {
			if (candidates.elementAt(i).getPartyName().equals(p1.getPartyName())) {
				candidateArray.add(counter,candidates.elementAt(i));
				counter++;
			}
		}
		p1.setPartyCandidates(candidateArray);
		p1.setNumOfCandidates(1);
		return true;
	}
	//add Votes//
	public void addVote(String partyName) {
		this.votes.add(this.numOfVotes, partyName);
		this.numOfVotes++;	
	}
	//add Votes so a specific Kalpi//
	public boolean AddVotesToKalpi(Kalpi k1, Database db) {
		int indexKalpiVotes = 0;
		String partyName="";
		Vector<String> kalpiVotes = new Vector<String>();
		for (int i = 0; i < numOfVotes; i++) {
			String[] tempsplit = votes.elementAt(i).split(" ");// SPLIT
			Vector<String> vec = new Vector<String>();
			vec.addAll(Arrays.asList(tempsplit));
			int kalpiID=Integer.parseInt(vec.elementAt(vec.size() - 1));
			if(k1.getKalpiId()==kalpiID) {
				if(vec.size() > 2) {
					partyName = vec.elementAt(0)+ " " + vec.elementAt(1);
				}else {
					partyName = vec.elementAt(0);
				}
				kalpiVotes.add(indexKalpiVotes, partyName);
				indexKalpiVotes++;
				db.insertVotesToKalpi(k1, getYear(), getMonth());
			}
		}
		k1.setNumOfVotes(indexKalpiVotes);
		k1.setVotesInKalpi(kalpiVotes);
		return true;
	}
	// GET SET//
	public Vector<Kalpi> getKalpies() {
		return this.kalpies;
	}

	public void setKalpies(Vector<Kalpi> kalpies)
	{
		this.kalpies = kalpies;
	}
	public Set<Voter> getVoters() {
		return this.voters;
	}

	public void setVoters(Set<Voter> voters)
	{
		this.voters = voters;
	}
	public Vector<Party> getParties() {
		return this.parties;
	}

	public void setParties(Vector<Party> parties)
	{
		this.parties = parties;
	}

	public Vector<Candidate> getCandidates() {
		return this.candidates;
	}

	public void setCandidates(Vector<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Vector<String> getVotes() {
		return this.votes;
	}
	public int getNumOfKalpies() {
		return numOfKalpies;
	}
	public int getNumOfVoters() {
		return numOfVoters;
	}
	public int getNumOfParties() {
		return numOfParties;
	}
	public int getNumOfCandidates() {
		return numOfCandidates;
	}
	public int getNumOfVotes() {
		return numOfVotes;
	}	

	public void setNumOfVotes(int numOfVotes)
	{
		this.numOfVotes = numOfVotes;
	}


	public void setNumOfVoters(int i) {
		this.numOfVoters += i;
	}

	public void setNumOfCandidates(int i) {
		this.numOfCandidates += i;
	}
	public void setNumOfParties(int i) {
		this.numOfParties += i;
	}

	public void setNumOfKalpies(int i) {
		this.numOfKalpies += i;
	}

	public double getPercentOfVoters() {
		if (this.getNumOfVotes() == 0) {
			return 0;
		}
		double percentage = (double)this.getNumOfVotes() / this.getNumOfVoters() * 100;
		return percentage;
	}	
	// case 5//
	public void showAllKalpies() {
		for (int i = 0; i < this.numOfKalpies; i++) {
			System.out.println(this.kalpies.elementAt(i).toString());
		}
	}
	// case 6//
	public void showAllVoters() {
		for (int i = 0; i < this.numOfVoters; i++) {	
			System.out.println(
					this.voters.collection.elementAt(i).toString() + 
					(this.voters.collection.elementAt(i).getIsIsolation() == true
					? (this.voters.collection.elementAt(i).getHasSuit() == true
					? this.voters.collection.elementAt(i).getName() + 
							" has suit and can come to the Corona Kalpi \r\n"
							: this.voters.collection.elementAt(i).getName()
							+ " is isolated and does not have a suit, "
							+ " He/She have the right to vote but cannot come to the Corona Kalpi \r\n"):
							"----------------------------------"));
		}
	}
	// case 7//
	public void showAllParties() {
		for (int i = 0; i < this.numOfParties; i++) {
			System.out.println(this.parties.elementAt(i).toString());
		}
	}
	// Methods
	public String getKalpiAdressByid(int id) {
		String location = "";
		for (int i = 0; i < this.numOfKalpies; i++) {
			if (kalpies.elementAt(i).getKalpiId() == id) {
				location = kalpies.elementAt(i).getKalpiLocation();
			}
		}
		return location;
	}
	public Vector<Kalpi> getKalpiByType(KalpiType type) {
		int numOfSameTypeKalpi = 0;
		Vector<Kalpi> temp = new Vector<Kalpi>();
		for (int i = 0; i < this.numOfKalpies; i++) {
			if (kalpies.elementAt(i).getType() == type) {
				temp.add(numOfSameTypeKalpi, kalpies.elementAt(i));
				numOfSameTypeKalpi++;
			}
		}
		return temp;
	}
	public int getKalpiIdByIndex(int kalpiID) {
		for (int i = 0; i < this.numOfKalpies; i++) {
			if (kalpies.elementAt(i).getKalpiId() == kalpiID) {
				return i;
			}
		}
		return 0;
	}
	//Voter
	public boolean updateVotersFromFile(Vector<Voter> voterList) {
		this.voters.clear();
		for (int i = 0; i < voterList.size(); i++) {
			this.voters.collection.add(voterList.elementAt(i));
		}
		//this.voters.collection.addAll(voterList);
		return true;
	}
	//kalpies
	public boolean updateKalpiesFromFile(Vector<Kalpi> kalpiesList) {
		this.kalpies.clear();
		for (int i = 0; i < kalpiesList.size(); i++) {
			this.kalpies.add(kalpiesList.elementAt(i));
		}
		return true;
	}
	//parties
	public boolean updatePartiesFromFile(Vector<Party> partiesList) {
		this.parties.clear();
		for (int i = 0; i < partiesList.size(); i++) {
			this.parties.add(partiesList.elementAt(i));
		}

		return true;
	}
	// case 8
	public String printPartiesName(Scanner vote,int kalpiId) throws InputMismatchException {
		int num = 0;
		boolean isValid = false;
		do {
			while(!isValid) {
				try {
					System.out.println("Select party you want to vote");
					for (int i = 1; i <= getNumOfParties(); i++) {
						System.out.println(i + " - " + parties.elementAt(i - 1).getPartyName());
					}
					num = vote.nextInt();
					isValid = true;
					if (num < 1 || num > getNumOfParties()) {
						System.out.println("Wrong number try again..");
						System.out.println();
					}
				}catch(InputMismatchException e) {
					System.out.println("You didn't enter a number ot the number is too big");
					vote.next();
				}
			}
		} while (num < 1 || num > getNumOfParties());
		String partyName = parties.elementAt(num - 1).getPartyName() + " " + kalpiId;
		return partyName;
	}
	public void showAllVotes() {
		for (int i = 0; i < this.numOfVotes; i++) {
			System.out.println(this.votes.elementAt(i));
		}
	}
	public int getYear() {

		return this.year;
	}

	public int getMonth() {

		return this.month;
	}
	public String getDate()
	{
		return this.month + "/" + this.year;
	}
	public void setYear(int year) {

		this.year = year;
	}
	public void setMonth(int month) {

		this.month = month;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Election Date: " + getDate() + "\r\n");
		sb.append("Elected Party: " + getElectedFromDB() + "\r\n");
		sb.append("Number of Kalpies: " + getNumOfKalpies() + "\r\n");
		sb.append("Kalpies List: \r\n");
		for (int i = 0; i < getNumOfKalpies(); i++) {
			sb.append(kalpies.get(i).toString());
			sb.append("\r\n");
		}
		sb.append("Number of Parties: " + getNumOfParties() + "\r\n");
		sb.append("Parties List: \r\n");
		for (int i = 0; i < getNumOfParties(); i++) {
			sb.append(parties.get(i).toString());
			sb.append("\r\n");
		}
		sb.append("Number of Voters: " + getNumOfVoters() + "\r\n");
		sb.append("Number of Votes: " + getNumOfVotes() + "\r\n");
		sb.append("Percentage Of Votes: " + getPercentOfVoters() + "\r\n");
		sb.append("----------------------------------");
		return sb.toString();
	}
	public String getElected() {
		if(this.numOfParties != 0) {
			Party partyWithMostVotes = getParties().elementAt(0);
			String partyName;
			for (int i = 0; i < getNumOfParties(); i++) {
				if (getParties().elementAt(i).getNumOfVotes() > partyWithMostVotes.getNumOfVotes()) {
					partyWithMostVotes = getParties().elementAt(i);
				}
			}
			partyName = partyWithMostVotes.getPartyName();		
			return partyName;
		}
		return null;
	}
	public void setElectedParty(String elected) {
		this.elected = elected;
	}

	public String getElectedFromDB()
	{
		setElectedParty(getElected());
		return this.elected;
	}


}