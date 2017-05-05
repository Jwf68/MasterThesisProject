package se.com.package1;

/**
 * A thing used by the repairers to identify themself to the system
 * @author hp
 *
 */
public class NFCtag extends Thing {
	private int repairerID;

	public NFCtag (Location lnkLocation, int identifier, String model, String manufacturer) { 
		super(lnkLocation, identifier, model, manufacturer);
	}
	
	@Override
	public String toString() {
		return "NFCtag [repairerID=" + repairerID + "]";
	}

	public int getRepairerID() {
		return repairerID;
	}

	public void setRepairerID(int repairerID) {
		this.repairerID = repairerID;
	}
	
	
}
