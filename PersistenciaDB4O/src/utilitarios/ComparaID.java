package utilitarios;

/**
 *
 * @author Hemerson e Jefferson
 */
import java.util.Comparator;
import modelo.Pessoa;

public class ComparaID implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Pessoa) {
            Pessoa c1 = (Pessoa) o1;
            Pessoa c2 = (Pessoa) o2;
            return (c1.getId() - c2.getId());
        }
        return 0;
    }
}
