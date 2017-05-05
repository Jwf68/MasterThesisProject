
package se.com.package1;

public abstract class Thing {

	/**
	* General Information about things in which can populate the ThingCatalog
	* @directed true
	* @label Has location
	* @supplierCardinality 1
	*/

	private se.com.package1.Location lnkLocation;
	private int identifier;
	private String model;
	private String manufacturer;

	public Thing(Location lnkLocation, int identifier, String model, String manufacturer) {
		this.lnkLocation = lnkLocation;
		this.identifier = identifier;
		this.model = model;
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "Thing [lnkLocation=" + lnkLocation + ", identifier=" + identifier + ", model=" + model
				+ ", manufacturer=" + manufacturer + "]";
	}
	
	

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public se.com.package1.Location getLnkLocation() {
		return lnkLocation;
	}

	public void setLnkLocation(se.com.package1.Location lnkLocation) {
		this.lnkLocation = lnkLocation;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

}
