package se.com.package1;

import java.util.Date;

import se.com.package1.Device.InstructionFormatCapability;
import se.com.package1.ErrorMetadata.ErrorDifficultyLevel;
import se.com.ui.MainWindow;

/**
 * @stereotype Controller
 * Implements controller patters and directs the flow of the logic
 *  Repair sequencer contains all the major logic for the application
 */

public class RepairSequenser {
	
	
	
	/**
	 * @directed true
	 * @label Store AR instruction
	 * @supplierCardinality 1 
	 */
	
	private se.com.package1.ArRepairInstruction lnkArRepairInstruction;
	/**
	 * @directed true
	 * @label Store Video Instruction
	 * @supplierCardinality 1 
	 */
	
	private se.com.package1.VideoRepairInstruction lnkVideoRepairInstruction;
	/**
	 * @directed true
	 * @label Store Repair instruction
	 * @supplierCardinality 1 
	 */
	
	private se.com.package1.TextRepairInstruction lnkTextRepairInstruction;
	/**
	 * @directed true
	 * @label Current repairer
	 * @supplierCardinality 1 
	 */
	
	private se.com.package1.Repairer lnkCurrentRepairer;
	/**
	 * @directed true
	 * @label Current error
	 * @supplierCardinality 1 
	 */
	
	private se.com.package1.CurrentError lnkError;
	/**
	 * @directed true
	 * @label Find thing
	 * @supplierCardinality 1 
	 */
	
	private se.com.package1.ThingCatalog lnkThingCatalog;
	/**
	 * @directed true
	 * @label Fetch repair instructions
	 * @supplierCardinality 1 
	 */
	
	private se.com.package1.DocFactoryCommunicator lnkDocFactoryCommunicator;
	/**
	 * @directed true
	 * @label Find repairer
	 * @supplierCardinality 1
	 */
	
	private se.com.package1.RepairerCatalog lnkRepairerCatalog;
	/**
	 * @directed true
	 * @label Find error
	 * @supplierCardinality 1
	 */
	
	private se.com.package1.ErrorCatalog lnkErrorCatalog;
	/**
	 * @directed true
	 * @label Store current instruction
	 * @supplierCardinality 1
	 */
	private MainWindow mainWindow;
	
	public RepairSequenser(MainWindow mw) {
		this.mainWindow = mw;
		// TODO Auto-generated constructor stub
	}
	
	private void initiateInfrastructure() {
		lnkRepairerCatalog = new RepairerCatalog();
		lnkErrorCatalog = new ErrorCatalog();
		lnkThingCatalog = new ThingCatalog();
		lnkDocFactoryCommunicator = new DocFactoryCommunicator();
	}
	
	public void scenarioStart () {
		//Initiate context (populate catalogs with context related information)
		initiateInfrastructure();
		initiateContext();
		
		//BERITS ID 2
		// GUNNARS ID 7
		int repairersTagID = 2;
		
		//Scenario starts
		lnkCurrentRepairer = lnkRepairerCatalog.getRepairer( ((NFCtag)lnkThingCatalog.getThing(repairersTagID)).getRepairerID());
		lnkCurrentRepairer.setLnkLocation(new Location(1, 1));
		printEvent(lnkCurrentRepairer.getName() +" arrives into the break room and is identified through a NFC tag reader");

		if(lnkCurrentRepairer.hasDevice()){
			lnkCurrentRepairer.setLocationToAllDevices(lnkCurrentRepairer.getLnkLocation());
		}
		
		printEvent("Tag reader: " +  lnkCurrentRepairer.toString());

		
		printEvent(lnkCurrentRepairer.getName() +" gets notified through the coffee machines display that there is an error");
		ErrorMetadata currentError = lnkErrorCatalog.getError(lnkError.getErrorCode());
		printEvent("Coffee machine display: "+currentError.toString());
		
		printEvent(lnkCurrentRepairer.getName() +" wants to repair the machine");
		
		// compare error difficulty (NOVICE) with repairer expertise level (NOVICE)
		if (currentError.getErrorDifficultyLevel() == ErrorMetadata.ErrorDifficultyLevel.NOVICE && lnkCurrentRepairer instanceof NoviceRepairer) {
			InstructionFormatCapability requiredInstructionFormatCapability = InstructionFormatCapability.AR;
			repairCase(requiredInstructionFormatCapability);
			
			//System fetches the suitable content (instructions) from DocFactory and stores it into the corresponding container
			lnkArRepairInstruction = lnkDocFactoryCommunicator.fetchARInstructions(lnkError.getErrorCode());
			
			printEvent(lnkCurrentRepairer.getName() + " follows the maintenance instructions on the device " + lnkArRepairInstruction.getRepairInstruction());
			
			// TODO: THE DEVICE HAS RUNTIME ISSUES?
		}
		// compare error difficulty (EXPERT) with repairer expertise level (EXPERT)
		else if (lnkCurrentRepairer instanceof ExpertRepairer) { 
			//EXPERT REPAIRER USE CASE
			InstructionFormatCapability requiredInstructionFormatCapability = InstructionFormatCapability.TEXT;
			repairCase(requiredInstructionFormatCapability);
			
			//System fetches the suitable content (instructions) from DocFactory and stores it into the corresponding container
			lnkTextRepairInstruction = lnkDocFactoryCommunicator.fetchTextInstructions(lnkError.getErrorCode());
			
			printEvent(lnkCurrentRepairer.getName() + " follows the maintenance instructions on the device " + lnkTextRepairInstruction.getRepairInstruction());
			
		}else{
			printEvent("The user does not have to sufficient expertise level to be able to repair the coffee machine");

		}
	}

	private void repairCase(InstructionFormatCapability requiredInstructionFormatCapability) {
		//NOVICE REPAIRER USE CASE
		printEvent("The system has concluded that "+lnkCurrentRepairer.getName()+" is of a sufficient expertise level"
				+ " to be able to repair the coffee machine");
		
		// COMPARE DEVICE CAPABILITY (AR) WITH THE ERROR DIFFICULTY LEVEL (NOVICE) AND RETURN SUITABLE DEVICE FOR REPAIRER
		Device compatibleDevice = lnkThingCatalog.getSuitableRepairDevice(requiredInstructionFormatCapability);
		
		
		printEvent(lnkCurrentRepairer.getName() +" gets notified through the coffee machines display to use the nearby device");
		printEvent("Coffee machine display: Follow the instruction type "+ requiredInstructionFormatCapability.toString() + " use the nearby device: " + 	compatibleDevice.getClass().getName().toString()
				+ " " + compatibleDevice.toString() + " and scanning the QRcode");	
		
		printEvent("The device scans the QRcode and the instructions is fetched by the device and the user is logged on");
		// add device link to repairer
		lnkCurrentRepairer.addRepairerDevice(compatibleDevice);
	}
	
	private void initiateContext(){
		
		//Phablet BERITS
		Location locationP1 = null;
		int identifierP1 = 5;
		String modelP1 = "Galaxy S4";
		String manufacturerP1 = "Samsung";
		Phablet phabletBerit = new Phablet(locationP1, identifierP1, modelP1, manufacturerP1);
		lnkThingCatalog.addThing(phabletBerit);

		
		//Phablet GUNNAR
		Location locationP2 = null;
		int identifierP2 = 6;
		String modelP2 = "Iphone S5";
		String manufacturerP2 = "Apple";
		Phablet phabletGunnar = new Phablet(locationP2, identifierP2, modelP2, manufacturerP2);
		lnkThingCatalog.addThing(phabletGunnar);

		// REPAIRER BERIT NOVICE
		int errorCodeExperience = 8484;
		int repairerID = 42;
		Location locationBerit = null;
		Device deviceBerit = phabletBerit;
		NoviceRepairer repairerBerit = new NoviceRepairer("Berit", errorCodeExperience, repairerID, locationBerit, deviceBerit);
		lnkRepairerCatalog.addRepairer(repairerBerit);
		
		// REPAIRER GUNNAR EXPERT
		String nameGunnar = "Gunnar";
		int repairerIDGunnar = 0;
		String serviceProvideGunnar = "Nisses Repairers";
		boolean workingGunnar = true;
		Location locationGunnar = null;
		Device deviceGunnar = phabletGunnar;
		ExpertRepairer repairerGunnar = new ExpertRepairer(nameGunnar, repairerIDGunnar, serviceProvideGunnar, workingGunnar, locationGunnar, deviceGunnar);
		lnkRepairerCatalog.addRepairer(repairerGunnar);

		
		// COFFEE MACHINE
		Location locationCM = new Location(1, 1);
		int identifierCM = 1;
		String modelCM = "Fierra12";
		String manufacturerCM = "Selecta";
		int serialNumberCM = 2389893;
		int firmwareVersionCM = 1112;
		CoffeeMachine coffeeMachine = new CoffeeMachine(locationCM, identifierCM, modelCM, manufacturerCM, 
				serialNumberCM, firmwareVersionCM);
		lnkThingCatalog.addThing(coffeeMachine);
		
		// BERITS TAG
		Location locationTag = null;
		int identifierTag = 2;
		String modelTag = "simpleBlue";
		String manufacturerTag = "tagCompany123";
		NFCtag beritsTag = new NFCtag(locationTag, identifierTag, modelTag, manufacturerTag);
		beritsTag.setRepairerID(repairerBerit.getRepairerID());
		lnkThingCatalog.addThing(beritsTag);
		
		// GUNNARS TAG
		Location locationTag2 = null;
		int identifierTag2 = 7;
		String modelTag2 = "simpleOrange";
		String manufacturerTag2 = "tagCompany123";
		NFCtag GunnarsTag = new NFCtag(locationTag2, identifierTag2, modelTag2, manufacturerTag2);
		GunnarsTag.setRepairerID(repairerGunnar.getRepairerID());
		lnkThingCatalog.addThing(GunnarsTag);
		
		// TAGREADER
		Location locationReader = new Location(1, 1);
		int identifierReader = 4;
		String modelReader = "tagreaderDelux";
		String manufacturerReader = "tagREADERCompany123";
		NFCtagReader breakRoomReader = new NFCtagReader(locationReader, identifierReader, modelReader, manufacturerReader);
		lnkThingCatalog.addThing(breakRoomReader);
		
		// HOLOLENS
		Location locationHL = new Location(1, 1);
		int identifierHL = 3;
		String modelHL = "ALPHA";
		String manufacturerHL = "MICROSOFT";
		HoloLens hololens = new HoloLens(locationHL, identifierHL, modelHL, manufacturerHL);
		lnkThingCatalog.addThing(hololens);
		
		// PC
		Location locationPC = new Location(1, 1);
		int identifierPC = 8;
		String modelPC = "Yoga";
		String manufacturerPC = "Lenovo";
		PC pc = new PC(locationPC, identifierPC, modelPC, manufacturerPC);
		lnkThingCatalog.addThing(pc);
		
		// ERROR
		int errorCode = 4421;
		Date errorTime = new Date();
		String errorName = "Dispenser screw broken";
		ErrorDifficultyLevel errorDifficultyLevel = ErrorDifficultyLevel.NOVICE;
		ErrorMetadata currentErrorMetaData = new ErrorMetadata(errorCode, errorTime, errorName, errorDifficultyLevel);
		lnkErrorCatalog.addError(currentErrorMetaData);
		
		// ERROR RELATION
		int thingWithError = coffeeMachine.getIdentifier();
		int errorCodeForThing = currentErrorMetaData.getErrorCode();
		lnkError = new CurrentError(errorCodeForThing, thingWithError);
		
		
	}
	
	

	private void printEvent (String event) {
		System.out.println(event);
		mainWindow.populateEventLog(event);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ie){
				// Do nothing 
		}
	}
}
