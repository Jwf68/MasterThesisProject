package se.com.package1;

import java.util.Date;

import se.com.package1.Device.InstructionFormatCapability;

/**
 * Information about the error than may occur
 * @author hp
 *
 */
public class ErrorMetadata {
	private int errorCode;
	private Date errorTime; //(time of day (complexity level of error))
	private String errorName;
//	private String difficultLevel; //of error task (NOVICE OR EXPERT)
	
	public enum ErrorDifficultyLevel {
		NOVICE, EXPERT
	};
	
	private ErrorDifficultyLevel errorDifficultyLevel;
	
	public ErrorMetadata (int errorCode, Date errorTime, String errorName, ErrorDifficultyLevel errorDifficultyLevel) {
		this.setErrorCode(errorCode);
		this.setErrorTime(errorTime);
		this.setErrorName(errorName);
		this.setErrorDifficultyLevel(errorDifficultyLevel);
	}
	
	
	@Override
	public String toString() {
		return "ErrorMetadata [errorCode=" + errorCode + ", errorTime=" + errorTime + ", errorName=" + errorName
				+ ", errorDifficultyLevel=" + errorDifficultyLevel + "]";
	}


	public ErrorDifficultyLevel getErrorDifficultyLevel() {
		return errorDifficultyLevel;
	}

	public void setErrorDifficultyLevel(ErrorDifficultyLevel errorDifficultyLevel) {
		this.errorDifficultyLevel = errorDifficultyLevel;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public Date getErrorTime() {
		return errorTime;
	}
	public void setErrorTime(Date errorTime) {
		this.errorTime = errorTime;
	}
	public String getErrorName() {
		return errorName;
	}
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
	

}
