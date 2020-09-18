package br.com.g4flex.service;

import java.util.ArrayList;
import java.util.List;

import br.com.g4flex.dao.ClientDao;
import br.com.g4flex.dto.FiltersDTO;
import br.com.g4flex.entity.Client;

public class ClientService {
	
	private ClientDao clientDao;
	
	public ClientService() {
		clientDao = new ClientDao();
	}
	
	public void create(Client client) { 
		clientDao.save(client);
	}
	
	public void update(Client client) { 
		clientDao.update(client);
	}
	
	public List<Client> list() { 
		return clientDao.list();
	}
	
	public List<Client> listWithPagination(int quantity, int numberOfPage) { 
		return clientDao.listWithPagination(quantity, numberOfPage);
	}
	
	public List<Client> findWithPagination(FiltersDTO filters, int quantity, int numberOfPage) {
		List<Client> result= null;
		
					
		if(filters.getClientName()!=null &&  filters.getClientName().trim().isEmpty()) {
			result = clientDao.listWithPagination( quantity,  numberOfPage);	
		}else if(filters.getClientName()!=null) {
			result = clientDao.findByClientName(filters, quantity,  numberOfPage);			
			
		}
		
		return result;
	}

	public List<Client> find(FiltersDTO filters) {
		List<Client> result= null;
		
		if(filters.getClientName()!=null &&  filters.getClientName().trim().isEmpty()) {
			result = clientDao.list();	
		}else if(filters.getClientName()!=null) {
			result = clientDao.findByClientName(filters);			
		}
		
		return result;
	}
	
	public int getQuantityPage(int quantity, int total) {
		return (int)Math.ceil((double)total/quantity);			
	}
	
	public List<Object[]> getArrayOfArrayObject(FiltersDTO filters) { 
		List<Object[]> data = new ArrayList<>();
		List<Client> lista = find(filters);
		for (Client client : lista) {
			data.add(client.toArray());
		}
		return data;
	}

}
