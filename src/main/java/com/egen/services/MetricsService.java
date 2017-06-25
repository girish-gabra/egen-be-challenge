package com.egen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.dao.MetricDAO;
import com.egen.model.Metric;

@Service
public class MetricsService {
	
	@Autowired
	private MetricDAO metricDAO;
	
	public void createMetric(Metric metric)
	{	// save this metric by calling DAO method
		metricDAO.createMetric(metric);
	}

	public List<Metric> getAllMetrics() {
		// TODO Auto-generated method stub
		
		List<Metric> allMetrics = metricDAO.getAllMetrics();
		
		return allMetrics;
	}
	
	public List<Metric> getMetricsByRange(long fromTimeStamp,long toTimeStamp) {
		// TODO Auto-generated method stub
		
		List<Metric> metrics = metricDAO.getMetricsByRange(fromTimeStamp,toTimeStamp);
		return metrics;
	}
}
