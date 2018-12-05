package infra;

import api.Factory;
import api.entities.Commentary;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;

public class CommentaryFactory implements Factory {

    mongoDb mongoDb = new mongoDb();

    @Override
    public List<Commentary> getAll() {
        DBCollection resFromRepo = mongoDb.getAllCommentary();
        List<Commentary> res = commentaryBuild(resFromRepo);
        return res;
    }

    public List<Commentary> commentaryBuild(DBCollection objectDB) {
        List<Commentary> lesCommentaires = new ArrayList<>();
        DBObject obj;
        Commentary commentaire;
        DBCursor cursor = objectDB.find();
        while (cursor.hasNext()) {
            obj = cursor.next();
            commentaire = new Commentary();
            commentaire.id = (obj.get("_id").toString());
            commentaire.auteur = (obj.get("auteur").toString());
            commentaire.message = (obj.get("message").toString());
            commentaire.idref = (obj.get("idRef").toString());
            lesCommentaires.add(commentaire);
        }
        return lesCommentaires;
    }

}
