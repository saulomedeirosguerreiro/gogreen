package br.com.g4flex.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.g4flex.dto.FiltersDTO;
import br.com.g4flex.entity.Point;
import br.com.g4flex.entity.User;
import br.com.g4flex.utils.DateUtil;

public class PointDao {
	public void save(Point point) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		manager.persist(point);
		manager.getTransaction().commit();

		manager.close();
	}
	
	public void update(Point point) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		manager.merge(point);
		manager.getTransaction().commit();

		manager.close();
	}
	
	public Point find(User user) {
		String today = DateUtil.getTodayText();
		EntityManager manager = JPAResourceBean.getEntityManager();
		manager.getTransaction().begin();
		Query query = manager.createQuery("SELECT p FROM Point p where p.user = :paramUser and p.date = :paramDate AND p.checkInHour BETWEEN :paramStartHour AND :paramEndHour ");
		query.setParameter("paramUser", user);
		query.setParameter("paramStartHour",DateUtil.stringToDate("00:00:00", DateUtil.PATTERN_HOUR));
		query.setParameter("paramEndHour",DateUtil.stringToDate("23:59:59", DateUtil.PATTERN_HOUR));
		query.setParameter("paramDate", DateUtil.stringToDate(today, DateUtil.PATTERN_DATE));
		Point point = null;
		try {
			point = (Point) query.getSingleResult();
		} catch (NoResultException e) {
		}
		manager.getTransaction().commit();
		return point;
	}

	public List<Point> list() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Point.findAll");
		List<Point> listOfPoint = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfPoint;
	}
	
	public double getAmount() {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Point.countAll");
		Object result = query.getSingleResult();
		double amount =  result != null ?  Double.parseDouble(result.toString()) : 0.0;
		manager.getTransaction().commit();

		manager.close();
		return amount;
	}
	
	

	
	public List<Point> listWithPagination(int quantity, int numberOfPage) {
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Point.findAll");
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<Point> listOfPoint = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfPoint;
	}

	public List<Point> findByUserNameAndActivityDate(FiltersDTO filters, int quantity, int numberOfPage) {
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Point.findByUserNameAndActivityDate");
		query.setParameter("userName", "%" + filters.getUserName().toLowerCase() + "%");
		query.setParameter("initialDate", filters.getInitialDate());
		query.setParameter("finalDate", filters.getFinalDate());
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<Point> listOfPoint = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfPoint;
	}
	
	public List<Point> findByActivityDate(FiltersDTO filters, int quantity, int numberOfPage) {
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Point.findByActivityDate");
		query.setParameter("initialDate", filters.getInitialDate());
		query.setParameter("finalDate", filters.getFinalDate());
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<Point> listOfPoint = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfPoint;
	}
	
	public List<Point> findByUserName(FiltersDTO filters, int quantity, int numberOfPage) {
		int offset = (numberOfPage - 1) * quantity;
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Point.findByUserName");
		query.setParameter("userName", "%" + filters.getUserName().toLowerCase() + "%");
		query.setFirstResult(offset);
		query.setMaxResults(quantity);
		List<Point> listOfPoint = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfPoint;
	}
	
	public List<Point> findByUserNameAndActivityDate(FiltersDTO filters) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Point.findByUserNameAndActivityDate");
		query.setParameter("userName", "%" + filters.getUserName().toLowerCase() + "%");
		query.setParameter("initialDate", filters.getInitialDate());
		query.setParameter("finalDate", filters.getFinalDate());
		List<Point> listOfPoint = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfPoint;
	}
	
	public List<Point> findByActivityDate(FiltersDTO filters) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Point.findByActivityDate");
		query.setParameter("initialDate", filters.getInitialDate());
		query.setParameter("finalDate", filters.getFinalDate());
		List<Point> listOfPoint = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfPoint;
	}
	
	public List<Point> findByUserName(FiltersDTO filters) {
		EntityManager manager = JPAResourceBean.getEntityManager();

		manager.getTransaction().begin();
		Query query = manager.createNamedQuery("Point.findByUserName");
		query.setParameter("userName", "%" + filters.getUserName().toLowerCase() + "%");
		List<Point> listOfPoint = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		return listOfPoint;
	}

}
