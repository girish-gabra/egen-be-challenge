package com.egen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.egen.dao.AlertDAO;
import com.egen.model.Alert;

@Service
@Configurable
public class AlertService {

	@Autowired
	private AlertDAO alertDAO;
	
	public void createAlert(long timeStamp,int value,String type)
	{
		System.out.println(timeStamp+" "+value+" "+type);
		Alert alert = new Alert();
		alert.setTimeStamp(timeStamp);
		alert.setValue(value);
		alert.setType(type);
		if(alertDAO==null){
			alertDAO = new AlertDAO();
		}
		alertDAO.createAlert(alert);
	}
}
