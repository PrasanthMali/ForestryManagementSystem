package com.cg.fms.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.fms.dao.AdminDao;
import com.cg.fms.exception.AdminException;
import com.cg.fms.model.AdminModel;

@Service
public class AdminServiceImpl implements IAdminService {


	@Autowired
	private AdminDao adminRepo;
	
	@Autowired
	private EMParser parser;
	
	public AdminServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	

	public AdminServiceImpl(AdminDao adminRepo) {
		super();
		this.adminRepo = adminRepo;
		this.parser = new EMParser();
	}
	
	


	public AdminDao getAdminRepo() {
		return adminRepo;
	}



	public void setAdminRepo(AdminDao adminRepo) {
		this.adminRepo = adminRepo;
	}


	public EMParser getParser() {
		return parser;
	}



	public void setParser(EMParser parser) {
		this.parser = parser;
	}



	@Override
	public AdminModel getAdmin(String adminId) throws AdminException {
		if (!adminRepo.existsById(adminId))
			throw new AdminException("No Admin found for the given Id");
		return parser.parse(adminRepo.findById(adminId).orElse(null));
	}

	@Override
	public AdminModel addAdmin(AdminModel adminmodel) throws AdminException {
		if (adminmodel != null) {
			if (adminRepo.existsById(adminmodel.getAdminId())) {
				throw new AdminException("Admin with this id already exists");
			}

			adminmodel = parser.parse(adminRepo.save(parser.parse(adminmodel)));
		}

		return adminmodel;
	}



	@Override
	public void deleteAdmin(String adminId) {
		adminRepo.deleteById(adminId);
	}

	@Override
	public List<AdminModel> getAllAdmins() {
		return adminRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}
	

//	@Transactional
//	@Override
//	public AdminModel updateAdmin(String adminId, AdminModel adminModel) throws AdminException {
//		if(adminModel != null) {
//			if (!adminDao.existsById(adminId)) {
//				throw new AdminException("Admin Not present in DB.");
//			}
//			adminModel = parser.parse((adminDao.save(parser.parse(adminModel))));
//		}
//		return adminModel;
//	}

	@Transactional
	@Override
	public AdminModel updateAdmin(String adminId, AdminModel adminModel) throws AdminException {
		if (adminModel != null) {
			if (adminRepo.existsById(adminModel.getAdminId())) {
				adminModel = parser.parse(adminRepo.save(parser.parse(adminModel)));
			}
			
		}
		return adminModel;
	}
}