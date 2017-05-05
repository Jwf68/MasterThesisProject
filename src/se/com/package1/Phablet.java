package se.com.package1;

/**
 * A maintenance device that is capable of providing Video and AR based maintenance guidance to the repairer
 * @author hp
 *
 */
public class Phablet extends Device {
	public Phablet (Location lnkLocation, int identifier, String model, String manufacturer) {
		super(lnkLocation, identifier, model, manufacturer);

		setInstructionFormatCapability(InstructionFormatCapability.VIDEO_AR);
	}


}
