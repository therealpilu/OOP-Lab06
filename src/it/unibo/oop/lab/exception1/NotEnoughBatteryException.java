package it.unibo.oop.lab.exception1;

public class NotEnoughBatteryException extends IllegalStateException {
	
	private static final long serialVersionUID = 1L;
	private final double batteryLevel;
	private final double batteryRequired;
	
	public NotEnoughBatteryException(final double batteryLevel, final double required) {
		super();
		this.batteryLevel = batteryLevel;
		this.batteryRequired = required;
	}
	
	public String toString() {
		return "No enough battery for moving. Battery level is " + batteryLevel
		            + " battery required is " + batteryRequired;
	}
	
	public String getMessage() {
		return this.toString();
	}

}
