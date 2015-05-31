/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;

/**
 *
 * @author Hemerson
 */
public interface DAO {

    public String incluir(Object o);

    public String excluir(int id);

    public String atualizar(int id, Object o);

    public Object localizar(int id);

    public List localizar(String nome);

    public Object anterior(int id);

    public Object proximo(int id);

    public Object primeiro();

    public Object ultimo();

    public List lista();

    public String limpa();

    public int nroObjetos();

    public int maiorID();
}
