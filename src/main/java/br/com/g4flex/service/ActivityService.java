package br.com.g4flex.service;

import java.util.ArrayList;
import java.util.List;

import br.com.g4flex.dao.ActivityDao;
import br.com.g4flex.entity.Activity;

public class ActivityService {
	
	private ActivityDao activityDao;
	
	public ActivityService() {
		activityDao = new ActivityDao();
	}
	
	public void create(Activity activity) { 
		activityDao.save(activity);
	}
	
	public List<Activity> list() { 
		return activityDao.list();
	}
	
	public List<Object[]> getArrayOfArrayObject() { 
		List<Object[]> data = new ArrayList<>();
		List<Activity> lista = list();
		for (Activity activity : lista) {
			data.add(activity.toArray());
		}
		return data;
	}

}
