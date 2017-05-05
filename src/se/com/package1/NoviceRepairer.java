package se.com.package1;

import java.util.HashSet;
/**
 * A repairer that is capable of handling errors by using AR and Video maintenance instructions based on the experience he possesses
 * @author hp
 *
 */
public class NoviceRepairer extends Repairer {
	private HashSet<Integer> experienceHs = new HashSet<Integer>(); // (Similar repair task before)
	
	public NoviceRepairer(String name, int repairerID, int experience, Location lnkLocation, Device lnkDevice) {
		super(name, repairerID, lnkLocation, lnkDevice);
		this.setExperienceHs(experience);
	}
	
	@Override
	public String toString() {
		return "NoviceRepairer " + this.getName() +  " [repairerID=" + experienceHs + ", lnkLocation=" + getLnkLocation() + ", lnkDevice=" + toStringDevices() + "]";
	}



	public Boolean isExperience(int experience) {
		return experienceHs.contains(experience);
	}

	public void setExperienceHs(int experience) {
		this.experienceHs.add(experience);
	}



	
}
