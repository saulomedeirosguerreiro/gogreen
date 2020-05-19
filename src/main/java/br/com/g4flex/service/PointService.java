package br.com.g4flex.service;

import java.util.ArrayList;
import java.util.List;

import br.com.g4flex.dao.PointDao;
import br.com.g4flex.dto.FiltersDTO;
import br.com.g4flex.entity.Point;
import br.com.g4flex.entity.User;

public class PointService {
	
	private PointDao pointDao;
	
	public PointService() {
		pointDao = new PointDao();
	}
	
	public void create(Point point) { 
		pointDao.save(point);
	}
	
	public List<Point> list() { 
		return pointDao.list();
	}
	
	public List<Point> listWithPagination(int quantity, int numberOfPage) { 
		return pointDao.listWithPagination( quantity,  numberOfPage);
	}
	
	public Point findTodayPoint(User user) { 
		return pointDao.find(user);
	}

	public void update(Point point) {
		pointDao.update(point);
	}
	
	public List<Point> findWithPagination(FiltersDTO filters, int quantity, int numberOfPage) {
		List<Point> result= null;
		
		if(filters.getUserName()!=null && filters.getInitialDate()!=null && filters.getInitialDate()!=null ) {
			result = pointDao.findByUserNameAndActivityDate(filters, quantity,  numberOfPage);
		}else if(filters.getInitialDate()!=null && filters.getInitialDate()!=null) {
			result = pointDao.findByActivityDate(filters, quantity,  numberOfPage);			
		}else if(filters.getUserName()!=null &&  !filters.getUserName().isEmpty()) {
			result = pointDao.findByUserName(filters, quantity,  numberOfPage);			
		}
		
		return result;
	}

	public List<Point> find(FiltersDTO filters) {
		List<Point> result= null;
		
		if(filters.getUserName()!=null && filters.getInitialDate()!=null && filters.getInitialDate()!=null ) {
			result = pointDao.findByUserNameAndActivityDate(filters);
		}else if(filters.getInitialDate()!=null && filters.getInitialDate()!=null) {
			result = pointDao.findByActivityDate(filters);			
		}else if(filters.getUserName()!=null &&  !filters.getUserName().isEmpty()) {
			result = pointDao.findByUserName(filters);			
		}
		
		return result;
	}
	
	public int getQuantityPage(int quantity, int total) {
	 	return (int)Math.ceil(total/quantity);
	}
	
	public List<Object[]> getArrayOfArrayObject(FiltersDTO filters) { 
		List<Object[]> data = new ArrayList<>();
		List<Point> lista = find(filters);
		for (Point point : lista) {
			data.add(point.toArray());
		}
		return data;
	}

}
