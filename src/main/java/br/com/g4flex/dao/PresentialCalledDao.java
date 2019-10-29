package br.com.g4flex.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.g4flex.entity.PresentialCalled;

public class PresentialCalledDao {
	public void save(PresentialCalled presentialCalled) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		manager.persist(presentialCalled);
		manager.getTransaction().commit();

		manager.close();
	}

	public List<PresentialCalled> list() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("PresentialCalled.findAll");
		List<PresentialCalled> listOfPresentialCalled = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfPresentialCalled;
	}
	
	public double getAmount() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("PresentialCalled.countAll");
		Object result = query.getSingleResult();
		double amount =  result != null ?  Double.parseDouble(result.toString()) : 0.0;
		manager.getTransaction().commit();

		manager.close();
		return amount;
	}
	
	
	public List<PresentialCalled> listWithPagination(int quantity, int numberOfPage) {
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();
		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("PresentialCalled.findAll");
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<PresentialCalled> listOfPresentialCalled = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfPresentialCalled;
	}
}
