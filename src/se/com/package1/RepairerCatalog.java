package se.com.package1;

import java.util.HashMap;

/**
 * Catalog of all potential Repairers related to the system
 * @author hp
 *
 */
public class RepairerCatalog {

	HashMap<Integer, Repairer> RepairerCatalogHM = new HashMap<Integer, Repairer> ();
	
	public RepairerCatalog() {
		
	}

	public Repairer getRepairer(int repairerID){
		return RepairerCatalogHM.get(repairerID);
	}
	
	public void addRepairer(Repairer repairer){
		RepairerCatalogHM.putIfAbsent(repairer.getRepairerID(), repairer);
	}
	
	/**
	 * @directed true
	 * @supplierCardinality 1..*
	 */
	
	private se.com.package1.Repairer lnkRepairer;
}
