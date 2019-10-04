package br.com.g4flex.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
}
