package br.com.g4flex.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.g4flex.entity.User;

public class UserDao {
	public void save(User user) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();

		manager.close();
	}

	public User find(String email) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createQuery("SELECT u FROM User u where u.email = :paramEmail ");
		query.setParameter("paramEmail", email);
		User user = null;
		try {
			user = (User) query.getSingleResult();
		} catch (NoResultException e) {
		}
		manager.getTransaction().commit();
		return user;
	}

	public User find(String email, String password) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager
				.createQuery("SELECT u FROM User u where u.email = :paramEmail and u.password = :paramPassword ");
		query.setParameter("paramEmail", email);
		query.setParameter("paramPassword", password);
		User user = null;
		try {
			user = (User) query.getSingleResult();
		} catch (NoResultException e) {
		}
		manager.getTransaction().commit();
		return user;
	}

	public List<User> list() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("User.findAll");
		List<User> Users = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return Users;
	}
}
