package com.egen.dao;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.egen.model.Metric;

@Repository
public class MetricDAO {

	@Autowired	
	private Datastore datastore;
	
	public void createMetric(Metric metric)
	{
		datastore.save(metric);	
	}
}
