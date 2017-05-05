package se.com.package1;

/**
 * The AR Repair instruction container
 * @author hp
 *
 */

public class ArRepairInstruction extends Instruction {
	private String repairInstruction = "AR";

	
	public ArRepairInstruction(String repairInstruction, String name) {
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
		return "ArRepairInstruction [repairInstruction=" + repairInstruction + "]";
	}
	
	
}
