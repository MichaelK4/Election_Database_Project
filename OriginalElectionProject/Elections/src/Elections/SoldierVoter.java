package Elections;

import java.io.Serializable;

public class SoldierVoter  extends Voter implements Serializable{

	
   protected boolean isCarryWeapon;
	public SoldierVoter(String name, int id, int birthYear, boolean isIsolation, boolean hasSuit, int KalpiID,int numOfDaysSick ,boolean isCarryWeapon) {
		super(name, id, birthYear, isIsolation, hasSuit, KalpiID, numOfDaysSick);
		this.isCarryWeapon = isCarryWeapon;
	}
	//CopyCons
	public SoldierVoter(SoldierVoter other) {
		super(other);
		this.isCarryWeapon = other.isCarryWeapon;
	}
	//GET//
	public boolean getIsCarryWeapon() {
		return this.isCarryWeapon;
	}
	//Equals//
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SoldierVoter))
			return false;
		
		if (!super.equals(obj))
			return false;
		
		SoldierVoter temp = (SoldierVoter)obj;
		return id == temp.id;
	}
	//Methods//
	private String convertFromBooleanToString(boolean isCarryWeapon) {
		if (isCarryWeapon == true) {
			return "True";
		}else {
			return "False";
		}
	}
	//toString//
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Is Carring Weapon? " + convertFromBooleanToString(getIsCarryWeapon()));
		sb.append("\n");
		if (isIsolation == true && hasSuit == true) {
			sb.append("Number of days sick: " + this.numOfDaysSick + "\n");
		}
		return super.toString() + sb.toString();
	}
}