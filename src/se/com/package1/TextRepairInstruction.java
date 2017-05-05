package se.com.package1;


/**
 * The Text Repair instruction container
 * @author hp
 *
 */
public class TextRepairInstruction extends Instruction {
	private String repairInstruction = "TEXT";

	public TextRepairInstruction(String repairInstruction, String name) {
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
		return "TextRepairInstruction [repairInstruction=" + repairInstruction + "]";
	}
	
}
