package br.com.g4flex.service;

import java.util.ArrayList;
import java.util.List;

import br.com.g4flex.dao.PointDao;
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
	
	public Point findTodayPoint(User user) { 
		return pointDao.find(user);
	}

	public void update(Point point) {
		pointDao.update(point);
	}
	
	public List<Object[]> getArrayOfArrayObject() { 
		List<Object[]> data = new ArrayList<>();
		List<Point> lista = list();
		for (Point point : lista) {
			data.add(point.toArray());
		}
		return data;
	}

}
