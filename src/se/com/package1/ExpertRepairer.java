package se.com.package1;
/**
 * A repairer that is capable of handling complex (high difficulty level) errors and using text based maintenance instructions
 * @author hp
 *
 */
public class ExpertRepairer extends Repairer {

	private se.com.package1.Schedule lnkSchedule = new Schedule(false);
	/**
	 * @directed true
	 */
	private String serviceProvider;
	
	
	public ExpertRepairer(String name, int repairerID, String serviceProvider, boolean working, Location lnkLocation, Device lnkDevice) {
		super(name, repairerID, lnkLocation, lnkDevice);
		this.serviceProvider = serviceProvider;
		
		lnkSchedule.setWorking(working);
	}
	
	
	@Override
	public String toString() {
		return "ExpertRepairer [serviceProvider=" + serviceProvider + ", working=" + isWorking() + "]";
	}


	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public boolean isWorking() {
		return lnkSchedule.isWorking();
	}

}
