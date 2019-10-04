package br.com.g4flex.service;

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

}
