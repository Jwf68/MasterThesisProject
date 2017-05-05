
package se.com.package1;

/**
 * Information about the current error in the EC
 * @author hp
 *
 */

public class CurrentError {

	private int errorCode;
	private int thingWithErrorID;
	
	public CurrentError(int errorCode, int thingWithError) {
		super();
		this.errorCode = errorCode;
		this.thingWithErrorID = thingWithError;
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
