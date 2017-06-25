package com.egen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.dao.MetricDAO;
import com.egen.model.Metric;

@Service
public class MetricsService {
	
	@Autowired
	private MetricDAO metricDAO;
	
	public void createMetric(Metric metric)
	{
		// do something
		System.out.println("Timestamp: "+metric.getTimeStamp());
		System.out.println("value: "+metric.getValue());
		metricDAO.createMetric(metric);
	}
}
