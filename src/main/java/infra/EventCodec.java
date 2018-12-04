package infra;

import java.io.IOException;
import java.util.UUID;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.entities.Event;


public class EventCodec implements CollectibleCodec< Event >
{
	private Codec< Document > documentCodec;

	public EventCodec()
	{
		this.documentCodec = new DocumentCodec();
	}

	public EventCodec( Codec< Document > documentCodec )
	{
		this.documentCodec = documentCodec;
	}

	@Override
	public void encode( BsonWriter writer, Event value, EncoderContext encoderContext )
	{
		try
		{
			this.documentCodec.encode( writer, Document.parse( new ObjectMapper().writeValueAsString( value ) ),
					encoderContext );
		}
		catch( JsonProcessingException e )
		{
			e.printStackTrace();
		}
	}

	@Override
	public Class< Event > getEncoderClass()
	{
		return Event.class;
	}

	@Override
	public Event decode( BsonReader reader, DecoderContext decoderContext )
	{
		Document document = documentCodec.decode( reader, decoderContext );

		ObjectMapper	mapper	= new ObjectMapper();
		Event			event	= null;

		try
		{
			event = mapper.readValue( document.toJson(), Event.class );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

		return event;
	}

	@Override
	public Event generateIdIfAbsentFromDocument( Event event )
	{
		if( !documentHasId( event ) ) event.id = UUID.randomUUID().toString();

		return event;
	}

	@Override
	public boolean documentHasId( Event event )
	{
		return event.id != null;
	}

	@Override
	public BsonValue getDocumentId( Event event )
	{
		if( !documentHasId( event ) ) throw new IllegalStateException( "The event does not contain an _id" );

		return new BsonString( event.id );
	}

}
