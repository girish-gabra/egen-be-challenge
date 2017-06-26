package com.egen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.egen.dao.AlertDAO;
import com.egen.model.Alert;
import com.egen.model.Metric;

@Service
@Configurable
public class AlertsService {

	@Autowired
	private AlertDAO alertDAO;

	public List<Alert> getAllAlerts() {
		// TODO Auto-generated method stub
		List<Alert> allAlerts = alertDAO.getAllAlerts();
		return allAlerts;
	}

	public List<Alert> getAlertsByRange(long fromTimeStamp,long toTimeStamp) {
		// TODO Auto-generated method stub
		
		List<Alert> alerts = alertDAO.getAlertsByRange(fromTimeStamp, toTimeStamp);
		return alerts;
	}	
}
