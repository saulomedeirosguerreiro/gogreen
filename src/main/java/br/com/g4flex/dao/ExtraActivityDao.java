package br.com.g4flex.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.g4flex.dto.FiltersDTO;
import br.com.g4flex.entity.ExtraActivity;

public class ExtraActivityDao {
	public void save(ExtraActivity extraActivity) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		manager.persist(extraActivity);
		manager.getTransaction().commit();

		manager.close();
	}

	public List<ExtraActivity> list() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ExtraActivity.findAll");
		List<ExtraActivity> listOfExtraActivity = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfExtraActivity;
	}
	
	public double getAmount() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ExtraActivity.countAll");
		Object result = query.getSingleResult();
		double amount =  result != null ?  Double.parseDouble(result.toString()) : 0.0;
		manager.getTransaction().commit();

		manager.close();
		return amount;
	}
	
	public List<ExtraActivity> listWithPagination(int quantity, int numberOfPage) {
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ExtraActivity.findAll");
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<ExtraActivity> listOfExtraActivity = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfExtraActivity;
	}

	public List<ExtraActivity> findByUserNameAndActivityDate(FiltersDTO filters, int quantity, int numberOfPage) {
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ExtraActivity.findByUserNameAndActivityDate");
		query.setParameter("userName", "%" + filters.getUserName().toLowerCase() + "%");
		query.setParameter("initialDate", filters.getInitialDate());
		query.setParameter("finalDate", filters.getFinalDate());
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<ExtraActivity> listOfExtraActivity = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfExtraActivity;
	}
	
	public List<ExtraActivity> findByActivityDate(FiltersDTO filters, int quantity, int numberOfPage) {
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ExtraActivity.findByActivityDate");
		query.setParameter("initialDate", filters.getInitialDate());
		query.setParameter("finalDate", filters.getFinalDate());
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<ExtraActivity> listOfExtraActivity = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfExtraActivity;
	}
	
	public List<ExtraActivity> findByUserName(FiltersDTO filters, int quantity, int numberOfPage) {
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ExtraActivity.findByUserName");
		query.setParameter("userName", "%" + filters.getUserName().toLowerCase() + "%");
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<ExtraActivity> listOfExtraActivity = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfExtraActivity;
	}
	
	public List<ExtraActivity> findByUserNameAndActivityDate(FiltersDTO filters) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ExtraActivity.findByUserNameAndActivityDate");
		query.setParameter("userName", "%" + filters.getUserName().toLowerCase() + "%");
		query.setParameter("initialDate", filters.getInitialDate());
		query.setParameter("finalDate", filters.getFinalDate());
		List<ExtraActivity> listOfExtraActivity = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfExtraActivity;
	}
	
	public List<ExtraActivity> findByActivityDate(FiltersDTO filters) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ExtraActivity.findByActivityDate");
		query.setParameter("initialDate", filters.getInitialDate());
		query.setParameter("finalDate", filters.getFinalDate());
		List<ExtraActivity> listOfExtraActivity = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfExtraActivity;
	}
	
	public List<ExtraActivity> findByUserName(FiltersDTO filters) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("ExtraActivity.findByUserName");
		query.setParameter("userName", "%" + filters.getUserName().toLowerCase() + "%");
		List<ExtraActivity> listOfExtraActivity = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfExtraActivity;
	}

	
	
}
