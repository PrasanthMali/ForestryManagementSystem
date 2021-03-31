package com.cg.fms.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="scheduler_details")
public class Scheduler {
	@Id
	@Column(name="scheduler_id")
	private String schedulerId;
	
	@Column(name="scheduler_Name", nullable=false)
	private String schedulerName;
	
	@Column(name="scheduler_contact", nullable=false)
	private String schedulerContact;

	@Column(name="truck_number", nullable=false)
	private String truckNumber;
	
	@OneToMany(mappedBy="scheduler")
	private Set<Order> orders;
	
	@OneToMany(mappedBy="scheduler")
	private Set<Contract> contracts;
	
	public Scheduler() {
		
	}

	public Scheduler(String schedulerId, String schedulerName, String schedulerContact, String truckNumber) {
		super();
		this.schedulerId = schedulerId;
		this.schedulerName = schedulerName;
		this.schedulerContact = schedulerContact;
		this.truckNumber = truckNumber;
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

	public Set<Order> getOrder() {
		return orders;
	}

	public void setOrder(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Contract> getContract() {
		return contracts;
	}

	public void setContract(Set<Contract> contracts) {
		this.contracts = contracts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contracts == null) ? 0 : contracts.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
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
		Scheduler other = (Scheduler) obj;
		if (contracts == null) {
			if (other.contracts != null)
				return false;
		} else if (!contracts.equals(other.contracts))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
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
		return "Scheduler [schedulerId=" + schedulerId + ", schedulerName=" + schedulerName + ", schedulerContact="
				+ schedulerContact + ", truckNumber=" + truckNumber + ", order=" + orders + ", contract=" + contracts
				+ "]";
	}
	
	
	
	
}