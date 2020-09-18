package br.com.g4flex.service;

import java.util.ArrayList;
import java.util.List;

import br.com.g4flex.dao.PresentialCalledDao;
import br.com.g4flex.dto.FiltersDTO;
import br.com.g4flex.entity.PresentialCalled;

public class PresentialCalledService {
	
	private PresentialCalledDao presentialCalledDao;
	
	public PresentialCalledService() {
		presentialCalledDao = new PresentialCalledDao();
	}
	
	public void create(PresentialCalled presentialCalled) { 
		presentialCalledDao.save(presentialCalled);
	}
	
	public List<PresentialCalled> list() { 
		return presentialCalledDao.list();
	}
	
	public List<PresentialCalled> listWithPagination(int quantity, int numberOfPage) { 
		return presentialCalledDao.listWithPagination( quantity,  numberOfPage);
	}
	
	public List<PresentialCalled> findWithPagination(FiltersDTO filters, int quantity, int numberOfPage) {
		List<PresentialCalled> result= null;
		
		if(filters.getUserName()!=null && filters.getInitialDate()!=null && filters.getInitialDate()!=null ) {
			result = presentialCalledDao.findByUserNameAndActivityDate(filters, quantity,  numberOfPage);
		}else if(filters.getInitialDate()!=null && filters.getInitialDate()!=null) {
			result = presentialCalledDao.findByActivityDate(filters, quantity,  numberOfPage);			
		}else if(filters.getUserName()!=null &&  !filters.getUserName().isEmpty()) {
			result = presentialCalledDao.findByUserName(filters, quantity,  numberOfPage);			
		}
		
		return result;
	}

	public List<PresentialCalled> find(FiltersDTO filters) {
		List<PresentialCalled> result= null;
		
		if(filters.getUserName()!=null && filters.getInitialDate()!=null && filters.getInitialDate()!=null ) {
			result = presentialCalledDao.findByUserNameAndActivityDate(filters);
		}else if(filters.getInitialDate()!=null && filters.getInitialDate()!=null) {
			result = presentialCalledDao.findByActivityDate(filters);			
		}else if(filters.getUserName()!=null &&  !filters.getUserName().isEmpty()) {
			result = presentialCalledDao.findByUserName(filters);			
		}
		
		return result;
	}
	
	public int getQuantityPage(int quantity, int total) {
	 	return (int)Math.ceil((double)total/quantity);
	}
	
	public List<Object[]> getArrayOfArrayObject(FiltersDTO filters) { 
		List<Object[]> data = new ArrayList<>();
		List<PresentialCalled> lista = find(filters);
		for (PresentialCalled presentialCalled : lista) {
			data.add(presentialCalled.toArray());
		}
		return data;
	}
	
	

}
