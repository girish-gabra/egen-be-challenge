package com.egen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.model.Alert;
import com.egen.model.Metric;
import com.egen.services.AlertsService;
import com.egen.services.MetricsService;

@RestController
@RequestMapping(value="/alerts")
public class AlertsController {
	@Autowired
	private AlertsService service;
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public ResponseEntity<List<Alert>> getAllMetrics()
	{
		List<Alert> allAlerts = service.getAllAlerts();
		// check if there are no records
		if(allAlerts.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Alert>>(allAlerts,HttpStatus.OK);
	}
	
	@RequestMapping(value="/readByTimeRange", method = RequestMethod.GET)
	public ResponseEntity<List<Alert>> getAlertsByTimeRange(@RequestParam long fromTimeStamp, @RequestParam long toTimeStamp)
	{
		List<Alert> alerts = service.getAlertsByRange(fromTimeStamp, toTimeStamp);
		// check if there are no records
		if(alerts.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Alert>>(alerts,HttpStatus.OK);
	}
}
