package utilitarios;

/**
 *
 * @author Hemerson e Jefferson
 */
import java.util.Comparator;
import modelo.Pessoa;

public class ComparaNome implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Pessoa) {
            Pessoa c1 = (Pessoa) o1;
            Pessoa c2 = (Pessoa) o2;
            if (c1.getNome().compareTo(c2.getNome()) == 0) {
                return c1.getId() - (c2.getId());
            } else {
                return c1.getNome().compareTo(c2.getNome());
            }
        }
        return 0;
    }
}
