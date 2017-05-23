package se.com.package1;

import java.util.ArrayList;

import se.com.package1.Database.ErrorCatalog;
import se.com.package1.Database.RepairerCatalog;
import se.com.package1.Database.ThingCatalog;
import se.com.package1.Device.InstructionFormatCapability;
import se.com.ui.ECWindow;
import se.com.ui.MainWindow;
import se.com.ui.PdfViewer;
import se.com.ui.VideoPlayer;


/**
 * @stereotype Controller
 */

public class GoalManager {

	/**
	 * @directed true
	 * @label Store AR instruction
	 * @supplierCardinality 1
	 */
	private ArRepairInstruction lnkArRepairInstruction;
	/**
	 * @directed true
	 * @label Store Video Instruction
	 * @supplierCardinality 1
	 */
	private VideoRepairInstruction lnkVideoRepairInstruction;
	/**
	 * @directed true
	 * @label Store Repair instruction
	 * @supplierCardinality 1
	 */
	private TextRepairInstruction lnkTextRepairInstruction;
	/**
	 * @directed true
	 * @label Current repairer
	 * @supplierCardinality 1
	 */
	private Repairer lnkCurrentRepairer;
	/**
	 * @directed true
	 * @label Current goal
	 * @supplierCardinality 1
	 */
	private CurrentECGoal lnkError;
	/**
	 * @directed true
	 * @label Find thing
	 * @supplierCardinality 1
	 */
	private ThingCatalog lnkThingCatalog;
	/**
	 * @directed true
	 * @label Fetch repair instructions
	 * @supplierCardinality 1
	 */
	private DocFactoryCommunicator lnkDocFactoryCommunicator;
	/**
	 * @directed true
	 * @label Find repairer
	 * @supplierCardinality 1
	 */
	private RepairerCatalog lnkRepairerCatalog;
	/**
	 * @directed true
	 * @label Find error
	 * @supplierCardinality 1
	 */
	private ErrorCatalog lnkErrorCatalog;
	/**
	 * @directed true
	 * @label Store current instruction
	 * @supplierCardinality 1
	 */
	private MainWindow mainWindow;
	private ECWindow aECWindow;
	
	private DeviceManager aPopulateDatabase;

	public GoalManager(MainWindow mw, ECWindow aECWindow) {
		this.mainWindow = mw;
		this.aECWindow = aECWindow;
		this.lnkRepairerCatalog = new se.com.package1.Database.RepairerCatalog();
		this.lnkErrorCatalog = new se.com.package1.Database.ErrorCatalog();
		this.lnkThingCatalog = new se.com.package1.Database.ThingCatalog();
		this.lnkDocFactoryCommunicator = new DocFactoryCommunicator();
		this.lnkError = new CurrentECGoal();

		this.aPopulateDatabase = new DeviceManager(lnkRepairerCatalog, lnkErrorCatalog, lnkThingCatalog,
				lnkDocFactoryCommunicator, lnkError);
		aPopulateDatabase.initiateDatabase();
	}

	// BERITS ID 2
	// GUNNARS ID 7
	public void startECForming(int repairersTagID) {
		ErrorMetadata currentError = lnkErrorCatalog.getError(lnkError.getErrorCode());
		
		//EC IS FORMED
		printEvent("There is an error in the coffee machine");
		
		//Determine if urgent
		if (currentError.isErrorUrgent()) {
			printEvent("Coffee machine display: \n BLINK SCREEN IN RED AND NOTIFY ALL NEARBY REPAIRERS ABOUT ERROR \n" + currentError.toString());
			printToECOutput("Ec goal is to repair the error: \n" + currentError.toString() );
		}else{
			printEvent("The error is not urgent and the system will only print out the error to the Coffee machine display");
//			printEvent("Coffee machine display: \n" + currentError.toString());
			printToECOutput("Ec goal is: Repair the coffee machine: \n" + currentError.toString() );
		}
		
		//GET DEVICES WITH SAME LOCATION AS COFFEE MACHINE 
		ArrayList<Thing> thingwithlocationList = lnkThingCatalog.getThingbyLocation( lnkThingCatalog.getThing(lnkError.getThingWithError()).getLnkLocation() );	
		String strDevices ="";
		for (Thing thing : thingwithlocationList) {
			strDevices += thing.toString() + "\n";
		}
		printToECOutput("Detected nearby devices: \n" + strDevices);
				
		// Scenario starts
		lnkCurrentRepairer = lnkRepairerCatalog
				.getRepairer(((NFCtag) lnkThingCatalog.getThing(repairersTagID)).getRepairerID());
		lnkCurrentRepairer.setLnkLocation(new Location(1, 1));
		printEvent(lnkCurrentRepairer.getName()
				+ " arrives into the break room and is identified through a NFC tag reader");
		
		if (lnkCurrentRepairer.hasDevice()) {
			lnkCurrentRepairer.setLocationToAllDevices(lnkCurrentRepairer.getLnkLocation());
		}

		//GET Repairer WITH SAME LOCATION AS COFFEE MACHINE 
		ArrayList<Repairer> repairerwithlocationList =	lnkRepairerCatalog.getRepairerbyLocation( lnkThingCatalog.getThing(lnkError.getThingWithError()).getLnkLocation() );	
		String strRepairers ="";
		for (Repairer repairer : repairerwithlocationList) {
			strRepairers += repairer.toString() + "\n";
		}
		printToECOutput("Detected nearby repairers: \n" + strRepairers);
		
		
//		printEvent("Tag reader: " + lnkCurrentRepairer.toString());
		printToECOutput("Repairer identified for repair scenario: \n " + lnkCurrentRepairer.toString());
		

		// compare error difficulty (NOVICE) with repairer expertise level
		// (NOVICE)
		if (currentError.getErrorDifficultyLevel() == ErrorMetadata.ErrorDifficultyLevel.NOVICE
				&& lnkCurrentRepairer instanceof NoviceRepairer) {
			
			//TODO: JÄMFÖR EXPERIENCE LEVEL OF MULTIPLE REPAIRERS
			

//			NOVICE REPAIRER USE CASE
			InstructionFormatCapability requiredInstructionFormatCapability = InstructionFormatCapability.AR;
			repairCase(requiredInstructionFormatCapability, currentError);

			// System fetches the suitable content (instructions) from
			// DocFactory and stores it into the corresponding container
			lnkArRepairInstruction = lnkDocFactoryCommunicator.fetchARInstructions(lnkError.getErrorCode());

			printEvent(lnkCurrentRepairer.getName() + " follows the maintenance instructions: "
					+ lnkArRepairInstruction.getRepairInstruction());
			
			printEvent("After the successful repair of the coffee machine, "+((NoviceRepairer)lnkCurrentRepairer).increaseExperience());

			// TODO: THE DEVICE HAS RUNTIME ISSUES?
		}
		// compare error difficulty (EXPERT) with repairer expertise level
		// (EXPERT)
		else if (lnkCurrentRepairer instanceof ExpertRepairer) {
			// EXPERT REPAIRER USE CASE
			InstructionFormatCapability requiredInstructionFormatCapability = InstructionFormatCapability.TEXT;
			repairCase(requiredInstructionFormatCapability, currentError);

			// System fetches the suitable content (instructions) from
			// DocFactory and stores it into the corresponding container
			lnkTextRepairInstruction = lnkDocFactoryCommunicator.fetchTextInstructions(lnkError.getErrorCode());

			printEvent(lnkCurrentRepairer.getName() + " follows the maintenance instructions on the device "
					+ lnkTextRepairInstruction.getRepairInstruction());
		} else {
			printEvent("The person does not have to sufficient expertise level to be able to repair the coffee machine");

		}
	}

	private void repairCase(InstructionFormatCapability requiredInstructionFormatCapability, ErrorMetadata currentError) {
		printEvent("The system has concluded that " + lnkCurrentRepairer.getName()
		+ " is of a sufficient expertise level" + " to be able to repair the coffee machine");
		
		
		printEvent(lnkCurrentRepairer.getName()
				+ " gets notified through the coffee machines display that there is an error");
//		printEvent("Coffee machine display: \n" + currentError.toString());

		printEvent(lnkCurrentRepairer.getName() + " notifies the system through a NFC tag reader that "+ lnkCurrentRepairer.getName() +" wants to repair the machine");
		
		
		// COMPARE DEVICE CAPABILITY (AR) WITH THE ERROR DIFFICULTY LEVEL
		// (NOVICE) AND RETURN SUITABLE DEVICE FOR REPAIRER
		Device compatibleDevice = null;
		if(lnkCurrentRepairer instanceof NoviceRepairer){
			compatibleDevice = lnkThingCatalog.getSuitableRepairDevice(requiredInstructionFormatCapability, 
					lnkThingCatalog.getThing(lnkError.getThingWithError()).getLnkLocation(), ((NoviceRepairer)lnkCurrentRepairer).getExperienceLevel());
		}else if (lnkCurrentRepairer instanceof ExpertRepairer){
			compatibleDevice = lnkThingCatalog.getSuitableRepairDeviceExpert(requiredInstructionFormatCapability, 
					lnkThingCatalog.getThing(lnkError.getThingWithError()).getLnkLocation());
		}

		printEvent(lnkCurrentRepairer.getName()
				+ " gets notified through the coffee machines display to use the nearby "
				+ compatibleDevice.getClass().getSimpleName() 
				+ " and follow the instructions in " + requiredInstructionFormatCapability.toString());
		
//		printEvent("Coffee machine display: \n Follow the instruction type " + requiredInstructionFormatCapability.toString()
//						+ " use the nearby device: " + compatibleDevice + " and scanning the QRcode");
		
		printToECOutput("Selected nearby device to use: \n"+ compatibleDevice);
		
		
		printEvent(lnkCurrentRepairer.getName() +" uses the "+ compatibleDevice.getClass().getSimpleName() 
				+" scans the QRcode and the instructions is fetched by the "+ compatibleDevice.getClass().getSimpleName() 
				+" and "+lnkCurrentRepairer.getName()+" is logged on");
		// add device link to repairer
		lnkCurrentRepairer.addRepairerDevice(compatibleDevice);
		showInstructions(requiredInstructionFormatCapability);
	}

	private void showInstructions(InstructionFormatCapability instructionType) {
		switch (instructionType) {
		case AR:
			showArVideo();
			break;
		case TEXT:
			showPdf();
			break;
		default:
			break;
		}
	}

	private void showPdf() {
		Thread arThread = new Thread() {
			public void run() {
				PdfViewer aPdfViewer = new PdfViewer();
				aPdfViewer.showPdf("C:/Users/hp/Google Drive/Interaction Technology/Project/Manual/Manual.pdf");
			}
		};
		arThread.start();
	}

	private void showArVideo() {
		Thread arThread = new Thread() {
			public void run() {
				VideoPlayer aVideoPlayer = new VideoPlayer();
				aVideoPlayer.start(
						"C:\\Users\\hp\\Google Drive\\Interaction Technology\\Project\\Film\\Org\\20170425_111823_HoloLens.mp4");
			}
		};
		arThread.start();
	}

	private void printEvent(String event) {
		mainWindow.populateEventLog(event);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
			// Do nothing
		}
	}

	private void printToECOutput(String event) {
		aECWindow.populateECOutputLog(event);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
			// Do nothing
		}
	}
}
