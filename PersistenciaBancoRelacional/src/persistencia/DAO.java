/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

/**
 *
 * @author hlscarlin
 */
public interface DAO {
    public String conecta();
    public String desconecta();
    public String inserir(Object o);
    public String excluir(int id);
    public String atualizar(int id,Object o);
    public String localizar(int id);
    public String localizar(String nome);
    public String primeiro();
    public String ultimo();
    public String proximo(int id);
    public String anterior(int id);
    public String nroObjetos(); 
    public String setID();
    public String limpaBanco();
}
