package Elections;

import java.io.Serializable;

public class CoronaVoter extends Voter implements Serializable{

	


	public CoronaVoter(String name, int id, int birthYear, boolean isIsolation, boolean hasSuit, int KalpiID,int numOfDaysSick) {
		super(name, id, birthYear, isIsolation, hasSuit, KalpiID,numOfDaysSick);
	}

	public CoronaVoter(CoronaVoter other) {
		super(other);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CoronaVoter))
			return false;
		
		if (!super.equals(obj))
			return false;
		
		CoronaVoter temp = (CoronaVoter)obj;
		return id == temp.id;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Number of days sick: " + this.numOfDaysSick + "\n");
		return super.toString() + sb.toString();
	}	
}
