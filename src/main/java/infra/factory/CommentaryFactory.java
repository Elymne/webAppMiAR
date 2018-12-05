package infra.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.Factory;
import api.entities.Commentary;
import infra.database.collection.CommentaryCollection;

public class CommentaryFactory implements Factory< Commentary >
{

	@Autowired
	CommentaryCollection commentaryMongoDb;

	@Override
	public List<Commentary> getAll()
	{
		return commentaryMongoDb.getAll();
	}
}
