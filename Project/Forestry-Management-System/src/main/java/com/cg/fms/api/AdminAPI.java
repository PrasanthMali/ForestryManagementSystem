package com.cg.fms.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.fms.exception.AdminException;
import com.cg.fms.model.AdminModel;
import com.cg.fms.service.IAdminService;

@RestController
@RequestMapping(path="/admins")
public class AdminAPI {
	
	
	@Autowired
	private IAdminService adminService;
	
	
	@PostMapping("/addadmin")
	public ResponseEntity<AdminModel> createAdmin(@RequestBody AdminModel adminModel) throws AdminException {
		adminModel = adminService.addAdmin(adminModel);
		return new ResponseEntity<>(adminModel, HttpStatus.CREATED);
	}
	
	//update
//	@Transactional
//	@PutMapping("/updateadmin")
//	public ResponseEntity<AdminModel> updateAccount(@RequestBody AdminModel admin) throws AdminException{
//		admin =adminService.save(admin);
//		return ResponseEntity.ok(admin);
//		
//	}
	@Transactional
	@PutMapping("/updateadmin/{adminId}")
	public ResponseEntity<AdminModel> updateAdmin(@RequestBody AdminModel admin,@PathVariable("adminId")String adminId) throws AdminException{
		admin = adminService.updateAdmin(admin.getAdminId(),admin);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
//	@PutMapping("/updateadmin/{adminId}")
//		public ResponseEntity<AdminModel> updateAdmin(
//				@PathVariable("adminId") String adminId,
//				@RequestBody @Valid AdminModel adminModel,
//				BindingResult result)  throws AdminException {
//			
//			if (result.hasErrors()) {
//				throw new AdminException("Not Created");
//			}
//			
//			return ResponseEntity.ok(adminService.updateAdmin(adminModel, adminId));
//		}
	
	
	
	//display all admin
	@GetMapping("/getalladmins")
	public ResponseEntity<List<AdminModel>> getAll() throws AdminException{
		return ResponseEntity.ok(adminService.getAllAdmins());
	}
	
	@GetMapping("/getadmin/{adminId}")
	public ResponseEntity<AdminModel> getByAdmin(@PathVariable("adminId") String adminId) throws AdminException{
		return ResponseEntity.ok(adminService.getAdmin(adminId));
	}
	
	@DeleteMapping("/deleteadmin/{adminId}")
	public ResponseEntity<String> deleteAdmin(@PathVariable("adminId") String adminId) throws AdminException {
		ResponseEntity<String> response = null;
		AdminModel admin = adminService.getAdmin(adminId);
		if (admin == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			adminService.deleteAdmin(adminId);
			response = new ResponseEntity<>("Admin is deleted successsfully", HttpStatus.OK);
		}
		return response;
	}
	
}