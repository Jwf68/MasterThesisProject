package se.com.package1;

import java.util.HashMap;


/**
 * 
 * @author hp
 * Catalog all the errors that may occur
 */
public class ErrorCatalog {
	
	
	HashMap<Integer, ErrorMetadata> ErrorCatalogHM = new HashMap<Integer, ErrorMetadata> ();
	
	public ErrorCatalog() {
		
	}

	public ErrorMetadata getError(int errorCode){
		return ErrorCatalogHM.get(errorCode);
	}
	
	public void addError(ErrorMetadata error){
		ErrorCatalogHM.putIfAbsent(error.getErrorCode(), error);
	}
	

	
	/**
	 * @directed true
	 * @supplierCardinality 1..*
	 */
	
	private se.com.package1.ErrorMetadata lnkErrorMetadata;
}
