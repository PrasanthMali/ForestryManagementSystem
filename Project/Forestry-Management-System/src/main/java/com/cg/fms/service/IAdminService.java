package com.cg.fms.service;

import java.util.List;

import com.cg.fms.exception.AdminException;
import com.cg.fms.model.AdminModel;

public interface IAdminService {

	public AdminModel getAdmin(String adminId) throws AdminException;

	public AdminModel addAdmin(AdminModel adminmodel)throws AdminException;
	
	public void deleteAdmin(String adminId) throws AdminException;

	public List<AdminModel> getAllAdmins()throws AdminException;

	public AdminModel updateAdmin(String adminId, AdminModel admin) throws AdminException;







	

	
}