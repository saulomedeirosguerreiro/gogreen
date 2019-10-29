package br.com.g4flex.service;

import java.util.ArrayList;
import java.util.List;

import br.com.g4flex.dao.ExtraActivityDao;
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
	
	public int getQuantityPage(int quantity) {
		double total  = extraActivityDao.getAmount();
	 	return (int)Math.ceil(total/quantity);
	}
	
	
	public List<Object[]> getArrayOfArrayObject() { 
		List<Object[]> data = new ArrayList<>();
		List<ExtraActivity> lista = list();
		for (ExtraActivity extraActivity : lista) {
			data.add(extraActivity.toArray());
		}
		return data;
	}

}
