package br.com.g4flex.service;

import java.util.ArrayList;
import java.util.List;

import br.com.g4flex.dao.ControlOnDutyDao;
import br.com.g4flex.entity.ControlOnDuty;

public class ControlOnDutyService {
	
	private ControlOnDutyDao ControlOnDutyDao;
	
	public ControlOnDutyService() {
		ControlOnDutyDao = new ControlOnDutyDao();
	}
	
	public void create(ControlOnDuty controlOnDuty) { 
		ControlOnDutyDao.save(controlOnDuty);
	}
	
	public List<ControlOnDuty> list() { 
		return ControlOnDutyDao.list();
	}
	
	public List<ControlOnDuty> listWithPagination(int quantity, int numberOfPage) { 
		return ControlOnDutyDao.listWithPagination(quantity, numberOfPage);
	}
	
	public int getQuantityPage(int quantity) {
		double total  = ControlOnDutyDao.getAmount();
	 	return (int)Math.ceil(total/quantity);
	}
	
	public List<Object[]> getArrayOfArrayObject() { 
		List<Object[]> data = new ArrayList<>();
		List<ControlOnDuty> lista = list();
		for (ControlOnDuty controlOnDuty : lista) {
			data.add(controlOnDuty.toArray());
		}
		return data;
	}

}
