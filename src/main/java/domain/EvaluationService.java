package domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import api.entities.Evaluation;
import infra.database.collection.EvaluationCollection;

public class EvaluationService
{
	@Autowired
	EventService events;

	@Autowired
	UserService users;

	@Autowired
	EvaluationCollection collection;

	public List< Evaluation > getByEventId( String recordid )
	{
		return collection.findByEventId( recordid );
	}

	public void addEvaluation( Evaluation evaluation )
	{
		collection.insert( evaluation );
	}

	public boolean isValidEvaluation( Evaluation evaluation )
	{
		boolean res = false;
		if( evaluation.evaluation >= 1 || evaluation.evaluation <= 5 )
		{
			res = true;
		}
		for( Evaluation evaluationTest : getByEventId( evaluation.eventId ) )
		{
			if( evaluationTest.userId.equals( evaluation.userId ) )
			{
				res = false;
			}
		}
		return res;
	}

	public double getAverageEvaluation( String id )
	{
		List< Evaluation >	evaluations	= this.getByEventId( id );
		double				average		= 0;

		if( evaluations.size() > 0 )
		{
			for( Evaluation evaluation : evaluations )
			{
				average += evaluation.evaluation;
			}
			average = average / evaluations.size();
		}
		return average;
	}
}
