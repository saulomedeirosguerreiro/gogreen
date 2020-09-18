package br.com.g4flex.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.g4flex.dto.FiltersDTO;
import br.com.g4flex.entity.Client;

public class ClientDao {
	public void save(Client client) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		manager.persist(client);
		manager.getTransaction().commit();

		manager.close();
	}
	
	public void update(Client client) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		manager.merge(client);
		manager.getTransaction().commit();

		manager.close();
	}

	public List<Client> list() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Client.findAll");
		List<Client> listOfClient = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfClient;
	}
	
	public double getAmount() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Client.countAll");
		Object result = query.getSingleResult();
		double amount =  result != null ?  Double.parseDouble(result.toString()) : 0.0;
		manager.getTransaction().commit();

		manager.close();
		return amount;
	}
	
	
	
	public List<Client> listWithPagination(int quantity, int numberOfPage){
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Client.findAll");
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<Client> listOfClient = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfClient;
		
	}
	
	
	public List<Client> findByClientName(FiltersDTO filters, int quantity, int numberOfPage) {
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Client.findByClient");
		query.setParameter("clientName", "%" + filters.getClientName().toLowerCase() + "%");
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<Client> listOfClient = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfClient;
	}
	

	
	public List<Client> findByClientName(FiltersDTO filters) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Client.findByClient");
		query.setParameter("clientName", "%" + filters.getClientName().toLowerCase() + "%");
		List<Client> listOfClient = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfClient;
	}
}
