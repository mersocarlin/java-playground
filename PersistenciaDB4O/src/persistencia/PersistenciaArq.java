package persistencia;

/**
 *
 * @author Hemerson e Jefferson
 */
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.File;

public class PersistenciaArq {

    ObjectContainer db;
    ObjectSet result;

    public PersistenciaArq(String banco) {
        db = Db4o.openFile("arquivos/" + banco + ".db");
        result = null;
    }

    public void setObjectSet(modelo.Pessoa pessoa) {
        result = db.get(pessoa);
    }

    public void limpar(String banco) {
        db.close();
        File f = new File("arquivos/" + banco + ".db");
        f.delete();
        db = Db4o.openFile("arquivos/" + banco + ".db");
    }
}
