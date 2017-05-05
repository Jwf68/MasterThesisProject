package se.com.package1;


/**
 * Information about a instruction
 * @author hp
 *
 */
public abstract class Instruction {
	private String name;

	public Instruction(String name) {
		super();
		this.name = name;
	}

	public String getInstructionName() {
		return name;
	}

	public void setInstructionName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Instruction [name=" + name + "]";
	}
	
	
	
}
