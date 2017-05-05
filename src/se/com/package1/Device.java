
package se.com.package1;

/**
 * The device is a thing that is used to aid the repairer in the maintenance process
 * THere are three different types of devices which is seperated with their capability of maintenance formats
 * 
 * @author hp
 *
 */
public class Device extends Thing {
	
	public Device (Location lnkLocation, int identifier, String model, String manufacturer) { 
		super(lnkLocation, identifier, model, manufacturer);
	}
	
	@Override
	public String toString() {
		return super.toString() + " Device [instructionFormatCapability=" + instructionFormatCapability + "]" ;
	}

	public enum InstructionFormatCapability {
		AR, VIDEO, TEXT, VIDEO_AR
	};
	
	private InstructionFormatCapability instructionFormatCapability;

	public InstructionFormatCapability getInstructionFormatCapability() {
		return instructionFormatCapability;
	}

	public void setInstructionFormatCapability(InstructionFormatCapability instructionFormatCapability) {
		this.instructionFormatCapability = instructionFormatCapability;
	}
	
}
