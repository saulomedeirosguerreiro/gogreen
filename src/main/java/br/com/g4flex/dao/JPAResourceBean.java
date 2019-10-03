package br.com.g4flex.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAResourceBean {
	
	private static EntityManagerFactory emFactory;
	
	public static EntityManagerFactory getEMF() {
		if (emFactory == null) {
			emFactory = Persistence.createEntityManagerFactory("gogreen");
		}
		return emFactory;
	}
	
	public static EntityManager getEntityManager() {
		return getEMF().createEntityManager();
	}
	
}
