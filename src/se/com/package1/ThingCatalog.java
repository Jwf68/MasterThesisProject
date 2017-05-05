package se.com.package1;

import java.util.HashMap;

import se.com.package1.Device.InstructionFormatCapability;

/**
 * Catalog of all the Things that are connected to the system
 * @author hp
 *
 */

public class ThingCatalog {

	HashMap<Integer, Thing> thingCatalogHM = new HashMap<Integer, Thing>();
	
	public ThingCatalog () {
		
	}
	
	public ThingCatalog(HashMap<Integer, Thing> thingCatalogHM, Thing lnkDevice) {
		super();
		thingCatalogHM = thingCatalogHM;
		this.lnkDevice = lnkDevice;
	}

	public Device getSuitableRepairDevice(InstructionFormatCapability instructionFormatCapability) {
		Device returnedDevice = null;
		for (HashMap.Entry<Integer, Thing> currentThing : thingCatalogHM.entrySet()) {
		    if( (currentThing.getValue() instanceof Device) && 
		    		((Device)currentThing.getValue()).getInstructionFormatCapability() == instructionFormatCapability ){
		    	returnedDevice = ((Device)currentThing.getValue());
		    }
		}
		return returnedDevice;
	}

	public Thing getThing(int identifier) {
		return thingCatalogHM.get(identifier);
	}



	public void addThing(Thing thing) {
		thingCatalogHM.putIfAbsent(thing.getIdentifier(), thing);
	}


	public se.com.package1.Thing getLnkDevice() {
		return lnkDevice;
	}



	public void setLnkDevice(se.com.package1.Thing lnkDevice) {
		this.lnkDevice = lnkDevice;
	}



	/**
	 * @directed true
	 * @supplierCardinality 1..*
	 */
	
	private se.com.package1.Thing lnkDevice;
}
