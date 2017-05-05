package se.com.package1;


/**
 * The Video Repair instruction container
 * @author hp
 *
 */
public class VideoRepairInstruction extends Instruction {
	private String repairInstruction = "VIDEO";

	public VideoRepairInstruction(String repairInstruction, String name) {
		super(name);
		this.repairInstruction = repairInstruction;
	}

	public String getRepairInstruction() {
		return repairInstruction;
	}

	public void setRepairInstruction(String repairInstruction) {
		this.repairInstruction = repairInstruction;
	}

	@Override
	public String toString() {
		return "VideoRepairInstruction [repairInstruction=" + repairInstruction + "]";
	}
	
	
}
