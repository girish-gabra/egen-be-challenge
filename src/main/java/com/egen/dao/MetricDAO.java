package com.egen.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
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

	public List<Metric> getAllMetrics() {
		// TODO Auto-generated method stub
		Query<Metric> query = datastore.createQuery(Metric.class);
		List<Metric> allMetrics = query.asList();
		return allMetrics;
	}
	
	public List<Metric> getMetricsByRange(long fromTimeStamp,long toTimeStamp) {
		// TODO Auto-generated method stub
		
		Query<Metric> query = datastore.createQuery(Metric.class).field("timeStamp")
				.greaterThanOrEq(fromTimeStamp)
				.field("timeStamp").lessThanOrEq(toTimeStamp);
		List<Metric> metrics = query.asList();
		return metrics;
	}
}
