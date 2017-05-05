
package se.com.package1;

import java.util.HashMap;

/**
 * General Information about Repairers in which can populate the RepairerCatalog
* @directed true
*/

public abstract class Repairer extends Person {
	
	private int repairerID;
	private HashMap<Integer, Device> repairerDevices = new HashMap<Integer, Device> ();

	

	public Repairer(String name, int repairerID, Location lnkLocation, Device lnkDevice) {
		super(name);
		this.repairerID = repairerID;
		this.lnkLocation = lnkLocation;
		addRepairerDevice(lnkDevice);
	}
	
	public boolean hasDevice() {
		if(repairerDevices.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	public void addRepairerDevice(Device device){
		repairerDevices.putIfAbsent(device.getIdentifier(), device);
	}
	
	public Device getRepairerDevice(int repairerDeviceID){
		return repairerDevices.get(repairerDeviceID);
	}
	
	public String toStringDevices () {
		String returnString = null;
		for (HashMap.Entry<Integer, Device> device : repairerDevices.entrySet()) {
			returnString = device.getValue().toString();
		}
		return returnString;
	}
	
	public void setLocationToAllDevices(Location location) {
		for (HashMap.Entry<Integer, Device> device : repairerDevices.entrySet()) {
			device.getValue().setLnkLocation(location);
		}
	}
	
	@Override
	public String toString() {
		return "Repairer [repairerID=" + repairerID + ", lnkLocation=" + lnkLocation + ", lnkDevice=" + lnkDevice + "]";
	}


	public se.com.package1.Location getLnkLocation() {
		return lnkLocation;
	}
	public void setLnkLocation(se.com.package1.Location lnkLocation) {
		this.lnkLocation = lnkLocation;
	}
	
	public int getRepairerID() {
		return repairerID;
	}
	public void setRepairerID(int repairerID) {
		this.repairerID = repairerID;
	}
	
	
	/**
	 * @directed true
	 * @label Has location
	 * @supplierCardinality 1 
	 */

	private se.com.package1.Location lnkLocation;
	/**
	 * @directed true
	 * @label Current device
	 * @supplierCardinality 1..*
	 */

	private se.com.package1.Device lnkDevice;

}
