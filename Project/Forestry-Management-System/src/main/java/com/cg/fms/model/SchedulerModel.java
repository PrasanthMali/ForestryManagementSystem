package com.cg.fms.model;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class SchedulerModel {
	
	

	private String schedulerId;
	
	@NotEmpty(message="schedulerName  cannot be empty")
	@NotNull(message="schedulerName cannot be omitted")
	private String schedulerName;
	
	@Pattern(regexp="[1-9][0-9]{9}", message="contact number is expected to be 10 digits and should not start with 0")
	@NotNull(message="contact number cannot be omitted")
	private String schedulerContact;

	@NotEmpty(message="truckNumber  cannot be empty")
	@NotNull(message="truckNumber cannot be omitted")
	private String truckNumber;
	
	
	private String order;
	
	
	private String contract;
	
	public SchedulerModel() {
		
	}

	public SchedulerModel(String schedulerId,
			@NotEmpty(message = "schedulerName  cannot be empty") @NotNull(message = "schedulerName cannot be omitted") String schedulerName,
			@Pattern(regexp = "[1-9][0-9]{9}", message = "contact number is expected to be 10 digits and should not start with 0") @NotNull(message = "contact number cannot be omitted") String schedulerContact,
			@NotEmpty(message = "truckNumber  cannot be empty") @NotNull(message = "truckNumber cannot be omitted") String truckNumber) {
		super();
		this.schedulerId = schedulerId;
		this.schedulerName = schedulerName;
		this.schedulerContact = schedulerContact;
		this.truckNumber = truckNumber;
//		this.order = order;
//		this.contract = contract;
	}

	public String getSchedulerId() {
		return schedulerId;
	}

	public void setSchedulerId(String schedulerId) {
		this.schedulerId = schedulerId;
	}

	public String getSchedulerName() {
		return schedulerName;
	}

	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}

	public String getSchedulerContact() {
		return schedulerContact;
	}

	public void setSchedulerContact(String schedulerContact) {
		this.schedulerContact = schedulerContact;
	}

	public String getTruckNumber() {
		return truckNumber;
	}

	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contract == null) ? 0 : contract.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((schedulerContact == null) ? 0 : schedulerContact.hashCode());
		result = prime * result + ((schedulerId == null) ? 0 : schedulerId.hashCode());
		result = prime * result + ((schedulerName == null) ? 0 : schedulerName.hashCode());
		result = prime * result + ((truckNumber == null) ? 0 : truckNumber.hashCode());
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
		SchedulerModel other = (SchedulerModel) obj;
		if (contract == null) {
			if (other.contract != null)
				return false;
		} else if (!contract.equals(other.contract))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (schedulerContact == null) {
			if (other.schedulerContact != null)
				return false;
		} else if (!schedulerContact.equals(other.schedulerContact))
			return false;
		if (schedulerId == null) {
			if (other.schedulerId != null)
				return false;
		} else if (!schedulerId.equals(other.schedulerId))
			return false;
		if (schedulerName == null) {
			if (other.schedulerName != null)
				return false;
		} else if (!schedulerName.equals(other.schedulerName))
			return false;
		if (truckNumber == null) {
			if (other.truckNumber != null)
				return false;
		} else if (!truckNumber.equals(other.truckNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchedulerModel [schedulerId=" + schedulerId + ", schedulerName=" + schedulerName + ", schedulerContact="
				+ schedulerContact + ", truckNumber=" + truckNumber + ", order=" + order + ", contract=" + contract
				+ "]";
	}
	
	
}