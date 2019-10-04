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
}
