
package se.com.package1;

/**
 * Information about the current error in the EC
 * @author hp
 *
 */

public class CurrentECGoal {


	

	/**
	 * @directed true
	 * @supplierCardinality 1 
	 */
	se.com.package1.ErrorMetadata lnkErrorMetadata;
	/**
	 * @directed true
	 * @supplierCardinality 1 
	 */
	
	private se.com.package1.Thing lnkThing;
	private int errorCode;
	private int thingWithErrorID;
	
	public CurrentECGoal() {
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public String toString() {
		return "CurrentError [errorCode=" + errorCode + ", thingWithErrorID=" + thingWithErrorID + "]";
	}


	public int getThingWithError() {
		return thingWithErrorID;
	}

	public void setThingWithError(int thingWithError) {
		this.thingWithErrorID = thingWithError;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
