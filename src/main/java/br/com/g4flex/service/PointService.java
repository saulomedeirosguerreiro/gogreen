package br.com.g4flex.service;

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

}
