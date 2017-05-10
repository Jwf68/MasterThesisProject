package se.com.package1;
/**
 * A repairer that is capable of handling complex (high difficulty level) errors and using text based maintenance instructions
 * @author hp
 *
 */
public class ExpertRepairer extends Repairer {

	
	/**
	 * @directed true
	 */
	private String serviceProvider;
	
	
	public ExpertRepairer(String name, int repairerID, String serviceProvider, Location lnkLocation, Device lnkDevice) {
		super(name, repairerID, lnkLocation, lnkDevice);
		this.serviceProvider = serviceProvider;
		
	}


	@Override
	public String toString() {
		return "ExpertRepairer [serviceProvider=" + serviceProvider
				+ ", toStringDevices()= " + toStringDevices() 
				+ ", getLnkLocation()=" + getLnkLocation() + ", getRepairerID()=" + getRepairerID() + ", getName()="
				+ getName();
	}



	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
}
