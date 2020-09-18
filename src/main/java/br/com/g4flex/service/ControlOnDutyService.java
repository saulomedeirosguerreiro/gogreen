package br.com.g4flex.service;

import java.util.ArrayList;
import java.util.List;

import br.com.g4flex.dao.ControlOnDutyDao;
import br.com.g4flex.dto.FiltersDTO;
import br.com.g4flex.entity.ControlOnDuty;

public class ControlOnDutyService {
	
	private ControlOnDutyDao controlOnDutyDao;
	
	public ControlOnDutyService() {
		controlOnDutyDao = new ControlOnDutyDao();
	}
	
	public void create(ControlOnDuty controlOnDuty) { 
		controlOnDutyDao.save(controlOnDuty);
	}
	
	public List<ControlOnDuty> list() { 
		return controlOnDutyDao.list();
	}
	
	public List<ControlOnDuty> listWithPagination(int quantity, int numberOfPage) { 
		return controlOnDutyDao.listWithPagination(quantity, numberOfPage);
	}
	
	public List<ControlOnDuty> findWithPagination(FiltersDTO filters, int quantity, int numberOfPage) {
		List<ControlOnDuty> result= null;
		
		if(filters.getUserName()!=null && filters.getInitialDate()!=null && filters.getInitialDate()!=null ) {
			result = controlOnDutyDao.findByUserNameAndActivityDate(filters, quantity,  numberOfPage);
		}else if(filters.getInitialDate()!=null && filters.getInitialDate()!=null) {
			result = controlOnDutyDao.findByActivityDate(filters, quantity,  numberOfPage);			
		}else if(filters.getUserName()!=null &&  !filters.getUserName().isEmpty()) {
			result = controlOnDutyDao.findByUserName(filters, quantity,  numberOfPage);			
		}
		
		return result;
	}

	public List<ControlOnDuty> find(FiltersDTO filters) {
		List<ControlOnDuty> result= null;
		
		if(filters.getUserName()!=null && filters.getInitialDate()!=null && filters.getInitialDate()!=null ) {
			result = controlOnDutyDao.findByUserNameAndActivityDate(filters);
		}else if(filters.getInitialDate()!=null && filters.getInitialDate()!=null) {
			result = controlOnDutyDao.findByActivityDate(filters);			
		}else if(filters.getUserName()!=null &&  !filters.getUserName().isEmpty()) {
			result = controlOnDutyDao.findByUserName(filters);			
		}
		
		return result;
	}
	
	public int getQuantityPage(int quantity, int total) {
	 	return (int)Math.ceil((double)total/quantity);
	}
	
	public List<Object[]> getArrayOfArrayObject(FiltersDTO filters) { 
		List<Object[]> data = new ArrayList<>();
		List<ControlOnDuty> lista = find(filters);
		for (ControlOnDuty controlOnDuty : lista) {
			data.add(controlOnDuty.toArray());
		}
		return data;
	}

}
