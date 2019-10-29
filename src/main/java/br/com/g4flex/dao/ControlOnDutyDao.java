package br.com.g4flex.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.g4flex.entity.ControlOnDuty;

public class ControlOnDutyDao {
	public void save(ControlOnDuty controlOnDuty) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		manager.persist(controlOnDuty);
		manager.getTransaction().commit();

		manager.close();
	}

	public List<ControlOnDuty> list() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ControlOnDuty.findAll");
		List<ControlOnDuty> listOfControlOnDuty = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfControlOnDuty;
	}
	
	public double getAmount() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ControlOnDuty.countAll");
		Object result = query.getSingleResult();
		double amount =  result != null ?  Double.parseDouble(result.toString()) : 0.0;
		manager.getTransaction().commit();

		manager.close();
		return amount;
	}
	
	
	
	public List<ControlOnDuty> listWithPagination(int quantity, int numberOfPage){
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ControlOnDuty.findAll");
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<ControlOnDuty> listOfControlOnDuty = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfControlOnDuty;
		
	}
}
