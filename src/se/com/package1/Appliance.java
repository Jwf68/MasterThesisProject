package se.com.package1;

/**
 * Information about a Appliance which is a special thing
 * A appliance has special properties that the thing does not have 
 * @author hp
 *
 */
public class Appliance extends Thing {
	
	private int serialNumber;
	private int firmwareVersion;
	
	
	
	public Appliance(Location lnkLocation, int identifier, String model, String manufacturer, int serialNumber,
			int firmwareVersion) {
		
		super(lnkLocation, identifier, model, manufacturer);
		
		serialNumber = serialNumber;
		firmwareVersion = firmwareVersion;
	}
	
	@Override
	public String toString() {
		return "Appliance [serialNumber=" + serialNumber + ", firmwareVersion=" + firmwareVersion + "]";
	}

	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getFirmwareVersion() {
		return firmwareVersion;
	}
	public void setFirmwareVersion(int firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}
	
	
	
}
