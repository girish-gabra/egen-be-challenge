package com.egen.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
		morphia.map(Metric.class);
		for (String str : mongoClient.listDatabaseNames())
		{
			System.out.println(str);
		}
		Datastore datastore = morphia.createDatastore(mongoClient, "egen");
		/*Metric metric = new Metric();
		metric.setTimeStamp("1498374");
		metric.setValue(150);
		datastore.save(metric);*/
		//DBCollection collection =  datastore.getCollection(Metric.class);
		//System.out.println(collection.getCount());
		//Query<Metric> q = datastore.createQuery(Metric.class);
		//List<Metric> qlist = q.asList();
		//System.out.println(qlist.size());
		return datastore;
	}
}
