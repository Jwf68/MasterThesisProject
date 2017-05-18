package se.com.package1;

/**
 * A NFC tag reader is used by the repairers to identify themself with a tag to the system
 * @author hp
 * @stereotype User agent
 */
public class NFCtagReader extends Resources {
	
	public NFCtagReader (Location lnkLocation, int identifier, String model, String manufacturer) {
		super(lnkLocation, identifier, model, manufacturer);
	}

	@Override
	public String toString() {
		return "NFCtagReader getModel()=" + getModel() + ", getManufacturer()="
				+ getManufacturer() + ", getLnkLocation()=" + getLnkLocation() + "\n getIdentifier()=" + getIdentifier();
	}
	
	
}
