package Elections;

import java.io.Serializable;

public class Candidate extends Voter implements Serializable {


	private static final long serialVersionUID = 1L;
	protected String PartyName;

	public Candidate(String name, int id, int birthYear, boolean isIsolation, boolean hasSuit ,int KalpiID,int numOfDaysSick ,String PartyName) {
		super(name, id, birthYear, isIsolation, hasSuit, KalpiID, numOfDaysSick);
		this.PartyName = PartyName;
	}
	public Candidate(Candidate other) {
		super(other);
		this.PartyName = other.PartyName;
	}
	public String getPartyName() {
		return PartyName;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Candidate))
			return false;
		
		if (!super.equals(obj))
			return false;
		
		Candidate temp = (Candidate)obj;
		return id == temp.id;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (this.isIsolation == true && this.hasSuit == true) {
			sb.append("Number of days sick: " + this.numOfDaysSick + "\n");
		}
		sb.append("Party name of candidate: " + this.PartyName + "\n");
		return super.toString() + sb.toString();
	}
}