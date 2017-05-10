package se.com.package1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import se.com.package1.Device.InstructionFormatCapability;
import se.com.package1.NoviceRepairer.NoviceRepairerExperienceLevel;

public class Database {

	/**
		* Catalog of all the Things that are connected to the system
		* @author hp
		*
		*/
	
		public static class ThingCatalog {
	
			HashMap<Integer, Thing> thingCatalogHM = new HashMap<Integer, Thing>();
	
			public ThingCatalog() {
	
			}
	
			public ThingCatalog(HashMap<Integer, Thing> thingCatalogHM, Thing lnkDevice) {
				super();
				thingCatalogHM = thingCatalogHM;
				this.lnkDevice = lnkDevice;
			}
	
			public Device getSuitableRepairDevice(InstructionFormatCapability instructionFormatCapability, Location location, NoviceRepairerExperienceLevel expLevel) {
				Device returnedDevice = null;
				for (HashMap.Entry<Integer, Thing> currentThing : thingCatalogHM.entrySet()) {
					if (	(currentThing.getValue() instanceof Device)  
							&& currentThing.getValue().getLnkLocation() != null && currentThing.getValue().getLnkLocation().equals(location)
							&& ( 
								((Device) currentThing.getValue()).getInstructionFormatCapability() == instructionFormatCapability
							|| ( ( InstructionFormatCapability.AR == instructionFormatCapability ) && ((Device) currentThing.getValue()).getInstructionFormatCapability() == instructionFormatCapability.VIDEO_AR)
							|| ( ( InstructionFormatCapability.VIDEO == instructionFormatCapability ) && ((Device) currentThing.getValue()).getInstructionFormatCapability() == instructionFormatCapability.VIDEO_AR)
							)
						) {
						
						// EXPERIENCE LEVEL < skillful -> AR -> HoloLens preferably
						if(NoviceRepairerExperienceLevel.SKILLFUL == expLevel  && ((Device) currentThing.getValue()) instanceof Phablet){
							returnedDevice = ((Device) currentThing.getValue());
						}else if(((Device) currentThing.getValue()) instanceof HoloLens) {
							returnedDevice = ((Device) currentThing.getValue());
						}
					}
				}
				return returnedDevice;
			}
	
			public Device getSuitableRepairDeviceExpert(InstructionFormatCapability instructionFormatCapability, Location location) {
				Device returnedDevice = null;
				for (HashMap.Entry<Integer, Thing> currentThing : thingCatalogHM.entrySet()) {
					if (	
							(currentThing.getValue() instanceof Device)  
							&& currentThing.getValue().getLnkLocation() != null && currentThing.getValue().getLnkLocation().equals(location)
							&& ( 
								((Device) currentThing.getValue()).getInstructionFormatCapability() == instructionFormatCapability
							|| ( ( InstructionFormatCapability.AR == instructionFormatCapability ) && ((Device) currentThing.getValue()).getInstructionFormatCapability() == instructionFormatCapability.VIDEO_AR)
							|| ( ( InstructionFormatCapability.VIDEO == instructionFormatCapability ) && ((Device) currentThing.getValue()).getInstructionFormatCapability() == instructionFormatCapability.VIDEO_AR)
							)
						) {
							returnedDevice = ((Device) currentThing.getValue());
					}
				}
				return returnedDevice;
			}
			
			public Thing getThing(int identifier) {
				return thingCatalogHM.get(identifier);
			}
			
			public ArrayList<Thing> getThingbyLocation(Location location) {
				ArrayList<Thing> returnedThing = new ArrayList<Thing>();
				for (HashMap.Entry<Integer, Thing> currentThing : thingCatalogHM.entrySet()) {
					if (currentThing.getValue().getLnkLocation() != null && currentThing.getValue().getLnkLocation().equals(location)) {
						returnedThing.add(currentThing.getValue());
					}
				}
				return returnedThing;
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

	/**
		* Catalog of all potential Repairers related to the system
		* @author hp
		*
		*/
		public static class RepairerCatalog {
	
			HashMap<Integer, Repairer> RepairerCatalogHM = new HashMap<Integer, Repairer>();
	
			public RepairerCatalog() {
	
			}
	
			public Repairer getRepairer(int repairerID) {
				return RepairerCatalogHM.get(repairerID);
			}
			
			public ArrayList<Repairer> getRepairerbyLocation(Location location) {
				
				ArrayList<Repairer> returnedRepairer = new ArrayList<Repairer>();
				for (HashMap.Entry<Integer, Repairer> currentRepairer : RepairerCatalogHM.entrySet()) {
					
					if (currentRepairer.getValue().getLnkLocation() != null && currentRepairer.getValue().getLnkLocation().equals(location)) {
						
						returnedRepairer.add(currentRepairer.getValue());
					}
				}
				return returnedRepairer;
			}
	
			public void addRepairer(Repairer repairer) {
				RepairerCatalogHM.putIfAbsent(repairer.getRepairerID(), repairer);
			}
	
			/**
			* @directed true
			* @supplierCardinality 1..*
			*/
	
			private se.com.package1.Repairer lnkRepairer;
		}

	/**
		*
		* @author hp
		* Catalog all the errors that may occur
		*/
		public static class ErrorCatalog {
	
			HashMap<Integer, ErrorMetadata> ErrorCatalogHM = new HashMap<Integer, ErrorMetadata>();
	
			public ErrorCatalog() {
	
			}
	
			public ErrorMetadata getError(int errorCode) {
				return ErrorCatalogHM.get(errorCode);
			}
	
			public void addError(ErrorMetadata error) {
				ErrorCatalogHM.putIfAbsent(error.getErrorCode(), error);
			}
	
			/**
			* @directed true
			* @supplierCardinality 1..*
			*/
	
			private se.com.package1.ErrorMetadata lnkErrorMetadata;
		}
}
