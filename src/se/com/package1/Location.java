package se.com.package1;

/**
 * The location of Repairers and Things to provide the system with data about if the repairer and a thing is inside the contextual boundaries.
 * TO know if the repairer is nearby the machine in which the error has occurred and what things/devices are available nearby to this location
 * @author hp
 *
 */
public class Location {
	private int longitude, latitude;
	
	public Location(int longitude, int latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Location [longitude=" + longitude + ", latitude=" + latitude + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + latitude;
		result = prime * result + longitude;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (latitude != other.latitude)
			return false;
		if (longitude != other.longitude)
			return false;
		return true;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	
	
}
