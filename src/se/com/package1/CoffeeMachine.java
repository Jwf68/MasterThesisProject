package se.com.package1;

/**
 * The appliance in which a error has occurred to in this scenario
 * @author hp
 * @stereotype User agent
 */
public class CoffeeMachine extends Appliance {
	
	public CoffeeMachine(Location lnkLocation, int identifier, String model, String manufacturer, int serialNumber,
			int firmwareVersion) {
		
		super(lnkLocation, identifier, model, manufacturer, serialNumber, firmwareVersion);
		
		this.setLnkLocation(lnkLocation);
		this.setIdentifier(identifier); 
		this.setModel(model); 
		this.setManufacturer(manufacturer); 
		this.setSerialNumber(serialNumber); 
		this.setFirmwareVersion(firmwareVersion);
	}

	@Override
	public String toString() {
		return "CoffeeMachine "+" getSerialNumber()=" + getSerialNumber()
				+ ", getFirmwareVersion()=" + getFirmwareVersion() + ", getModel()=" + getModel()
				+ ", getManufacturer()=" + getManufacturer() + "\n getLnkLocation()=" + getLnkLocation()
				+ ", getIdentifier()=" + getIdentifier() ;
	}


}
