package se.com.package1;

/**
 * Responsible to handle all the communication with DocFactory to provide the system with the instructions to the current error.
 * @author hp
 *
 */
public class DocFactoryCommunicator {
	
	public DocFactoryCommunicator() {
		// TODO Auto-generated constructor stub
	}
	
	public ArRepairInstruction fetchARInstructions (int errorCode) {
		return new ArRepairInstruction(" AR INSTRUCTION: EXCHANGE SCREW ", "AR");
	}
	
	public VideoRepairInstruction fetchVideoInstructions (int errorCode) {
		return new VideoRepairInstruction(" VIDEO INSTRUCTION: EXCHANGE SCREW ", "VIDEO");
	}
	
	public TextRepairInstruction fetchTextInstructions (int errorCode) {
		return new TextRepairInstruction(" TEXT INSTRUCTION: EXCHANGE SCREW ", "TEXT");
	}
	
}
