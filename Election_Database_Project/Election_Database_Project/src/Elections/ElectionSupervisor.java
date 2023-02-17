package Elections;

import java.util.Vector;

public class ElectionSupervisor {
	private int numOfElections;
	private Vector<Elections> allElections;
	
	public ElectionSupervisor()
	{
		this.numOfElections = 0;
		this.allElections = new Vector<Elections>();
	}
	
	
	
	public boolean addElection(Elections e1)
	{
		if(allElections.contains(e1))
			return false;
		allElections.add(e1);
		this.numOfElections++;
		return true;
	}
	
	public Vector<Elections> getAllElections()
	{
		return this.allElections;
	}
	
	public void setElections(Vector<Elections> elections)
	{
		this.allElections = elections;
	}
	
	public int getNumOfElections()
	{
		return this.numOfElections;
	}

	public void setNumOfElectionsUp()
	{
		this.numOfElections++;
	}
	
	public void setNumOfElections(int num)
	{
		this.numOfElections = num;
	}
	
	
	public void getPastElectionData(Vector<Elections> allElections, int currentElection,Database db) throws Exception {
		Elections pastElection = allElections.elementAt(currentElection - 1);
		Elections e1 = allElections.elementAt(currentElection);
		
		int numOfParties = pastElection.getNumOfParties();
		int numOfKalpies = pastElection.getNumOfKalpies();
		int numOfVoters = pastElection.getNumOfVoters();
		boolean add = false;
		
		for (int i = 0; i < numOfParties; i++) {
			Party p1 = pastElection.getParties().elementAt(i);
			Party p2 = new Party(p1.getPartyName(), p1.getType(), 
					p1.getYear(), p1.getMonth(), p1.getDay());
			add = e1.addParty(p2);
			if(add)
				db.partyInsert(p2, e1);
		}
		
		for (int i = 0; i < numOfKalpies; i++) {
			Kalpi k1 = pastElection.getKalpies().elementAt(i);
			Kalpi k2 = new Kalpi(k1.getType(), k1.getKalpiLocation(), k1.getKalpiId());
			add = e1.addKalpi(k2);
			if(add)
				db.insertKalpi(k2, e1);
		}
		
		for (int i = 0; i < numOfVoters; i++) {
			Voter v1 = pastElection.getVoters().collection.elementAt(i);
			add = e1.addVoter(v1);
			if(add)
				db.voterInsert(v1, e1);
		}
		
	}
	
	
}