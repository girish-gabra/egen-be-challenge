package com.egen.dao;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;

import com.egen.model.Alert;
import com.egen.model.Metric;

@Repository
@Configurable
public class AlertDAO {

	@Autowired	
	private Datastore datastore;
	
	public void createAlert(Alert alert)
	{
		datastore.save(alert);
	}
	
	public List<Alert> getAllAlerts() {
		// TODO Auto-generated method stub
		Query<Alert> query = datastore.createQuery(Alert.class);
		List<Alert> allAlerts = query.asList();
		return allAlerts;
	}
	
	public List<Alert> getAlertsByRange(long fromTimeStamp,long toTimeStamp) {
		// TODO Auto-generated method stub
		
		Query<Alert> query = datastore.createQuery(Alert.class).field("timeStamp")
				.greaterThanOrEq(fromTimeStamp)
				.field("timeStamp").lessThanOrEq(toTimeStamp);
		List<Alert> alerts = query.asList();
		return alerts;
	}
}
