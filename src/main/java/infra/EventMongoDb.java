package infra;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import api.entities.Event;

public class EventMongoDb
{

	// @Override
	public DBCollection getAll()
	{
		MongoClient mongoClient = new MongoClient( new MongoClientURI( "mongodb://localhost:27017" ) );
		return mongoClient.getDB( "appWeb" ).getCollection( "event" );
	}

	public void loadEvent( List< Event > lesEvents )
	{
		int				id			= 0;
		MongoClient		mongoClient	= new MongoClient();
		DBCollection	collection	= mongoClient.getDB( "appWeb" ).getCollection( "event" );
		collection.remove( new BasicDBObject() );

		for( Event event : lesEvents )
			collection.insert( new BasicDBObject( event.id, event ) );

		mongoClient.close();
	}

	private List< Object > verification( Event unEvent )
	{
		String			heureDebut, heureFin, siteWeb, imageUrl;
		List< Object >	res	= new ArrayList<>();
		if( unEvent.siteWeb == null )
		{
			siteWeb = "";
		}
		else
		{
			siteWeb = unEvent.siteWeb;
		}
		res.add( siteWeb );
		if( unEvent.imageUrl == null )
		{
			imageUrl = "";
		}
		else
		{
			imageUrl = unEvent.imageUrl;
		}
		res.add( imageUrl );
		if( unEvent.heureDeb == null )
		{
			heureDebut = "";
		}
		else
		{
			heureDebut = unEvent.heureDeb;
		}
		res.add( heureDebut );
		if( unEvent.heureFin == null )
		{
			heureFin = "";
		}
		else
		{
			heureFin = unEvent.heureFin;
		}
		res.add( heureFin );

		return res;

	}
}
