package com.cg.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Contract;
@Repository
public interface ContractDao extends JpaRepository<Contract, String> {

}
