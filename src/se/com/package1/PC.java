package se.com.package1;
/**
 * A maintenance device that is capable of providing TEXT based maintenance guidance to the repairer
 * @author hp
 *
 */
public class PC extends Device {

	public PC (Location lnkLocation, int identifier, String model, String manufacturer) {
		super(lnkLocation, identifier, model, manufacturer);
		setInstructionFormatCapability(InstructionFormatCapability.TEXT);
	}
	
}
