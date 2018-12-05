package infra.database;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import api.Database;

public class MongoDatabaseClient implements Database
{

	private MongoClient		client;
	private MongoDatabase	root;

	private MongoDatabaseClient()
	{
		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register( "api.entities" ).build();

		CodecRegistry codecRegistry = CodecRegistries.fromRegistries( MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders( pojoCodecProvider ) );

		MongoClientOptions options = MongoClientOptions.builder().codecRegistry( codecRegistry ).build();

		this.client	= new MongoClient( new ServerAddress( host, port ), options );
		this.root	= this.client.getDatabase( name );
	}

	private static MongoDatabaseClient INSTANCE = new MongoDatabaseClient();

	public static MongoDatabaseClient getInstance()
	{
		return INSTANCE;
	}

	public MongoClient getClient()
	{
		return client;
	}

	public MongoDatabase getRoot()
	{
		return root;
	}

}
