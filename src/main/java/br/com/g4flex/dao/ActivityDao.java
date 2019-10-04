package br.com.g4flex.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.g4flex.entity.Activity;
import br.com.g4flex.entity.ExtraActivity;

public class ActivityDao {
	public void save(Activity activity) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		manager.persist(activity);
		manager.getTransaction().commit();

		manager.close();
	}

	public List<Activity> list() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Activity.findAll");
		List<Activity> listOfActivity = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfActivity;
	}
}
