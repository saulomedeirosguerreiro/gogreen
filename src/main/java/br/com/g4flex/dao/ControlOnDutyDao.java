package br.com.g4flex.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
}
