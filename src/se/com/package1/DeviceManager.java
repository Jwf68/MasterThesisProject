package se.com.package1;

import java.util.Date;

import se.com.package1.Database.ErrorCatalog;
import se.com.package1.Database.RepairerCatalog;
import se.com.package1.Database.ThingCatalog;
import se.com.package1.ErrorMetadata.ErrorDifficultyLevel;
import se.com.ui.MainWindow;

/**
 * Scans continuously after devices and stores them in the database.  
 * @author hp
 *
 */
public class DeviceManager {
	DocFactoryCommunicator lnkDocFactoryCommunicator;
	ErrorCatalog lnkErrorCatalog;
	ThingCatalog lnkThingCatalog;
	RepairerCatalog lnkRepairerCatalog;
	CurrentECGoal lnkError;
	 
	public DeviceManager(RepairerCatalog lnkRepairerCatalog, ErrorCatalog lnkErrorCatalog, ThingCatalog lnkThingCatalog, DocFactoryCommunicator lnkDocFactoryCommunicator, CurrentECGoal lnkError) {
		this.lnkDocFactoryCommunicator = lnkDocFactoryCommunicator;
		this.lnkRepairerCatalog = lnkRepairerCatalog;
		this.lnkErrorCatalog = lnkErrorCatalog;
		this.lnkThingCatalog = lnkThingCatalog;
		this.lnkError = lnkError;
	}
	// Initiate context (populate catalogs with context related information)

	public void initiateDatabase () {

		// Phablet BERITS
		Location locationP1 = null;
		int identifierP1 = 5;
		String modelP1 = "Galaxy S4";
		String manufacturerP1 = "Samsung";
		Phablet phabletBerit = new Phablet(locationP1, identifierP1, modelP1, manufacturerP1);
		lnkThingCatalog.addThing(phabletBerit);

		// Phablet GUNNAR
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
		NoviceRepairer repairerBerit = new NoviceRepairer("Berit", errorCodeExperience, repairerID, locationBerit,
				deviceBerit);
		lnkRepairerCatalog.addRepairer(repairerBerit);

		// REPAIRER GUNNAR EXPERT
		String nameGunnar = "Gunnar";
		int repairerIDGunnar = 0;
		String serviceProvideGunnar = "Nisses Repairers";
		Location locationGunnar = null;
		Device deviceGunnar = phabletGunnar;
		ExpertRepairer repairerGunnar = new ExpertRepairer(nameGunnar, repairerIDGunnar, serviceProvideGunnar,
				locationGunnar, deviceGunnar);
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
		NFCtagReader breakRoomReader = new NFCtagReader(locationReader, identifierReader, modelReader,
				manufacturerReader);
		lnkThingCatalog.addThing(breakRoomReader);

		// HOLOLENS
		Location locationHL = new Location(1, 1);
		int identifierHL = 3;
		String modelHL = "ALPHA";
		String manufacturerHL = "MICROSOFT";
		HoloLens hololens = new HoloLens(locationHL, identifierHL, modelHL, manufacturerHL);
		lnkThingCatalog.addThing(hololens);

		// PC
		Location locationPC = null;
		int identifierPC = 8;
		String modelPC = "Yoga";
		String manufacturerPC = "Lenovo";
		PC pc = new PC(locationPC, identifierPC, modelPC, manufacturerPC);
		lnkThingCatalog.addThing(pc);
		repairerGunnar.addRepairerDevice(pc);

		// ERROR
		int errorCode = 4421;
		Date errorTime = new Date();
		boolean errorUrgent = false;
		String errorName = "Dispenser screw broken";
		ErrorDifficultyLevel errorDifficultyLevel = ErrorDifficultyLevel.NOVICE;
		ErrorMetadata currentErrorMetaData = new ErrorMetadata(errorCode, errorTime, errorName, errorDifficultyLevel, errorUrgent);
		lnkErrorCatalog.addError(currentErrorMetaData);

		// ERROR RELATION
		int thingWithError = coffeeMachine.getIdentifier();
		int errorCodeForThing = currentErrorMetaData.getErrorCode();
		lnkError.setErrorCode(errorCodeForThing);
		lnkError.setThingWithError(thingWithError);
	}
}