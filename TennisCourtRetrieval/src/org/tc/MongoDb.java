package org.tc;

import java.io.IOException;
import java.net.UnknownHostException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class MongoDb {

	static DB db;
	static DBCollection collection;
	static {

		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// or
		
		db = mongoClient.getDB("locations");
		collection = db.getCollection("addresses");
	}

	public static void writeToDb(String name, String streetAddress,
			String addressLocality, String addressRegion, String postalCode,
			String nationality, String map) {
		BasicDBObject doc = new BasicDBObject("name", name)
				.append("streetAddress", streetAddress)
				.append("addressLocality", addressLocality)
				.append("addressRegion", addressRegion)
				.append("postalCode", postalCode)
				.append("nationality", nationality).append("map", map);
		collection.insert(doc);
	}

	public static void writeToDb(CourtDetails courtDetails, Address address,
			String map) {

		Court court = new Court(map, courtDetails, address);
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(court);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(json);
		
		DBObject dbObject = (DBObject)JSON.parse(json);
		collection.insert(dbObject);

	}
}
