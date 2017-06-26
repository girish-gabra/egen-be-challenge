package com.egen.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.egen.model.Alert;
import com.egen.model.Metric;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration	
public class MorphiaAutoConfiguration {
	
	
	@Autowired
	private MongoClient mongoClient;
	
	@Bean
	public Datastore datastore()
	{
		Morphia morphia = new Morphia();
		morphia.map(Metric.class);	// map Metric class to morphia
		morphia.map(Alert.class);	// map Alert class to morphia
		Datastore datastore = morphia.createDatastore(mongoClient, "egen");
		return datastore;
	}
}
