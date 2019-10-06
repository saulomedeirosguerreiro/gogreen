package br.com.g4flex.service;

import java.util.ArrayList;
import java.util.List;

import br.com.g4flex.dao.PresentialCalledDao;
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
	
	public List<Object[]> getArrayOfArrayObject() { 
		List<Object[]> data = new ArrayList<>();
		List<PresentialCalled> lista = list();
		for (PresentialCalled presentialCalled : lista) {
			data.add(presentialCalled.toArray());
		}
		return data;
	}
	
	

}
