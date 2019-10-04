package br.com.g4flex.service;

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

}
