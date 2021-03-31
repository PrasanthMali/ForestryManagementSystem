package com.cg.fms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.LandDao;
import com.cg.fms.entity.Land;
import com.cg.fms.exception.LandException;
import com.cg.fms.model.LandModel;

@Service
public class LandServiceImpl implements ILandService {

	@Autowired
	private LandDao landDao;
	
	@Autowired
	private EMParser parser;
	
	public LandServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public LandServiceImpl(LandDao landDao) {
		
		
		super();
		this.landDao= landDao;
		this.parser =new EMParser();
	}

	@Override
	public LandModel getLand(String landId) throws LandException {
		if (!landDao.existsById(landId))
			throw new LandException("No land found for the given number");
		return parser.parse(landDao.findById(landId).get());
	}

	@Override
	public LandModel addLand(LandModel land) throws LandException {
		if (land!= null) {
			if (landDao.existsById(land.getLandId())) {
				throw new LandException("land with this number already exists");
			}

			land = parser.parse(landDao.save(parser.parse(land)));
		}

		return land;
	}

	@Override
	public Land updateLand(Land land) throws LandException {
		if ( land!= null) {
			if (landDao.existsById(land.getLandId())) {
				
				land = landDao.save(land);
			}
			else {
				throw new LandException("land with this doesnot exisit");
			}

			
		}

		return land;
	}

	@Override
	public List<Land> getAllLands() {
		return landDao.findAll();
	}

	@Override
	public void removeLandDetails(String surveyNumber) throws LandException {
		landDao.deleteById(surveyNumber);
		
	}

	


}