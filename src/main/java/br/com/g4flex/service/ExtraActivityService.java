package br.com.g4flex.service;

import java.util.ArrayList;
import java.util.List;

import br.com.g4flex.dao.ExtraActivityDao;
import br.com.g4flex.dto.FiltersDTO;
import br.com.g4flex.entity.ExtraActivity;

public class ExtraActivityService {
	
	private ExtraActivityDao extraActivityDao;
	
	public ExtraActivityService() {
		extraActivityDao = new ExtraActivityDao();
	}
	
	public void create(ExtraActivity extraActivity) { 
		extraActivityDao.save(extraActivity);
	}
	
	public List<ExtraActivity> list() { 
		return extraActivityDao.list();
	}
	
	public List<ExtraActivity> listWithPagination(int quantity, int numberOfPage) { 
		return extraActivityDao.listWithPagination( quantity,  numberOfPage);
	}
	
	public List<ExtraActivity> findWithPagination(FiltersDTO filters,int quantity, int numberOfPage){
		
		List<ExtraActivity> result= null;
		
		if(filters.getUserName()!=null && filters.getInitialDate()!=null && filters.getInitialDate()!=null ) {
			result = extraActivityDao.findByUserNameAndActivityDate(filters, quantity,  numberOfPage);
		}else if(filters.getInitialDate()!=null && filters.getInitialDate()!=null) {
			result = extraActivityDao.findByActivityDate(filters, quantity,  numberOfPage);			
		}else if(filters.getUserName()!=null &&  !filters.getUserName().isEmpty()) {
			result = extraActivityDao.findByUserName(filters, quantity,  numberOfPage);			
		}
		
		return result;
	}
	
	public List<ExtraActivity> find(FiltersDTO filters){
		
		List<ExtraActivity> result= null;
		
		if(filters.getUserName()!=null && filters.getInitialDate()!=null && filters.getInitialDate()!=null ) {
			result = extraActivityDao.findByUserNameAndActivityDate(filters);
		}else if(filters.getInitialDate()!=null && filters.getInitialDate()!=null) {
			result = extraActivityDao.findByActivityDate(filters);			
		}else if(filters.getUserName()!=null &&  !filters.getUserName().isEmpty()) {
			result = extraActivityDao.findByUserName(filters);			
		}
		
		return result;
	}
	
	public int getQuantityPage(int quantity, int total) {
	 	return (int)Math.ceil(total/quantity);
	}
	
	
	public List<Object[]> getArrayOfArrayObject(FiltersDTO filters) { 
		List<Object[]> data = new ArrayList<>();
		List<ExtraActivity> lista = find(filters);
		for (ExtraActivity extraActivity : lista) {
			data.add(extraActivity.toArray());
		}
		return data;
	}

}
