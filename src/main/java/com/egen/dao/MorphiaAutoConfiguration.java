package com.egen.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

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
		Datastore datastore=null;
		try 
		{
			Properties properties = new Properties();
			String propFileName = "db.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if(inputStream!=null){
				
					properties.load(inputStream);
				
			}
			String DB_NAME = properties.getProperty("DB_NAME");
			Morphia morphia = new Morphia();
			morphia.map(Metric.class);	// map Metric class to morphia
			morphia.map(Alert.class);	// map Alert class to morphia
			datastore = morphia.createDatastore(mongoClient, DB_NAME);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return datastore;
	}
}
