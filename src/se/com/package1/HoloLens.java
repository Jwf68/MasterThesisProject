package se.com.package1;
/**
 * A maintenance device that is capable of providing AR based maintenance guidance to the repairer
 * @author hp
 *
 */
public class HoloLens extends Device {
	
	public HoloLens (Location lnkLocation, int identifier, String model, String manufacturer) {
		super(lnkLocation, identifier, model, manufacturer);

		setInstructionFormatCapability(InstructionFormatCapability.AR);
	}
	
	
}
