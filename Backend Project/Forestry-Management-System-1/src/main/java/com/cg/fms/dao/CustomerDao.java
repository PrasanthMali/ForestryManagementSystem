package com.cg.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fms.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer,String>{

}
