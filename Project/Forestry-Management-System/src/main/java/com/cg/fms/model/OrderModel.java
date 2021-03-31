package com.cg.fms.model;

import java.util.Set;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class OrderModel {
	
	private String orderNumber;

	@NotEmpty(message="deliveryPlace  cannot be empty")
	@NotNull(message="deliveryPlace cannot be omitted")
	private String deliveryPlace;
	
	@NotEmpty(message="deliveryDate  cannot be empty")
	@NotNull(message="deliveryDate cannot be omitted")
	private String deliveryDate;

	@NotEmpty(message="quantity  cannot be empty")
	@NotNull(message="quantity cannot be omitted")
	private String quantity;
	

	private String scheduler;

	
	private String customer;


	
	@OneToMany(mappedBy="order")
	private Set<ProductModel> products;
	
	public OrderModel() {
		
	}

	public OrderModel(String orderNumber,
			@NotEmpty(message = "deliveryPlace  cannot be empty") @NotNull(message = "deliveryPlace cannot be omitted") String deliveryPlace,
			@NotEmpty(message = "deliveryDate  cannot be empty") @NotNull(message = "deliveryDate cannot be omitted") String deliveryDate,
			@NotEmpty(message = "quantity  cannot be empty") @NotNull(message = "quantity cannot be omitted") String quantity,
			String scheduler, String customer) {
		super();
		this.orderNumber = orderNumber;
		this.deliveryPlace = deliveryPlace;
		this.deliveryDate = deliveryDate;
		this.quantity = quantity;
		this.scheduler = scheduler;
		this.customer = customer;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDeliveryPlace() {
		return deliveryPlace;
	}

	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getScheduler() {
		return scheduler;
	}

	public void setScheduler(String scheduler) {
		this.scheduler = scheduler;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Set<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductModel> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((deliveryPlace == null) ? 0 : deliveryPlace.hashCode());
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((scheduler == null) ? 0 : scheduler.hashCode());
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
		OrderModel other = (OrderModel) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (deliveryPlace == null) {
			if (other.deliveryPlace != null)
				return false;
		} else if (!deliveryPlace.equals(other.deliveryPlace))
			return false;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (scheduler == null) {
			if (other.scheduler != null)
				return false;
		} else if (!scheduler.equals(other.scheduler))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderModel [orderNumber=" + orderNumber + ", deliveryPlace=" + deliveryPlace + ", deliveryDate="
				+ deliveryDate + ", quantity=" + quantity + ", scheduler=" + scheduler + ", customer=" + customer
				+ ", product=" + products + "]";
	}
	
	
	
	
	
	
}