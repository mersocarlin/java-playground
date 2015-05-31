/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import banco.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pessoa;

/**
 *
 * @author Hemerson
 */
public class DAOPessoa implements DAO {

    PreparedStatement statementSQL = null;
    public Connection con = null;
    public String nomeTabela = "pessoa";

    public DAOPessoa() {
        con = ConexaoBD.criarConexao();
    }

    public String incluir(Object o) {
        Pessoa pessoa = new Pessoa((Pessoa) o);
        String Sql = "insert into " + nomeTabela + " (id, nome, telefone) values (?,?,?);";
        try {
            statementSQL = con.prepareStatement(Sql);
            statementSQL.setInt(1, pessoa.getId());
            statementSQL.setString(2, pessoa.getNome());
            statementSQL.setString(3, pessoa.getTelefone());
            statementSQL.execute();
            return "Pessoa inserida com sucesso!";
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
            return "Erro na insercao";
        }
    }

    public String excluir(int id) {
        String Sql = "delete from " + nomeTabela + " where id = " + id + ";";
        try {
            statementSQL = con.prepareStatement(Sql);
            statementSQL.execute();
            return "Pessoa excluida com sucesso!";
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
            return "Erro na exclusao!";
        }
    }

    public String atualizar(int id, Object o) {
        Pessoa pessoa = new Pessoa((Pessoa) o);
        String Sql = "update " + nomeTabela + " set nome = ?, telefone = ? where id = " + id + ";";
        try {
            statementSQL = con.prepareStatement(Sql);
            statementSQL.setString(1, pessoa.getNome());
            statementSQL.setString(2, pessoa.getTelefone());
            statementSQL.execute();
            return "Pessoa atualizada com sucesso!";
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
            return "Erro na atualizacao!";
        }
    }

    public Object localizar(int id) {
        Pessoa pessoa = new Pessoa();
        try {
            statementSQL = con.prepareStatement("select * from " + nomeTabela + " where Id=?;");
            statementSQL.setInt(1, id);
            ResultSet resultSet = statementSQL.executeQuery();
            while (resultSet.next()) {
                pessoa.setId(resultSet.getInt("id"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setTelefone(resultSet.getString("telefone"));
            }
            return pessoa;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List localizar(String nome) {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        try {
            String Sql = "select * from " + nomeTabela + " where nome like '%" + nome + "%';";
            statementSQL = con.prepareStatement(Sql);
            ResultSet resultSet = statementSQL.executeQuery();
            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(resultSet.getInt("id"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setTelefone(resultSet.getString("telefone"));
                lista.add(pessoa);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }

    public Object anterior(int id) {
        try {
            statementSQL = con.prepareStatement("select * from " + nomeTabela + " order by id;");
            statementSQL = con.prepareStatement("select max(id) as maior from " + nomeTabela + " where id<?;");
            statementSQL.setInt(1, id);
            ResultSet resultSet = statementSQL.executeQuery();
            resultSet.next();
            if (resultSet.getString("maior") != null) {
                return this.localizar(resultSet.getInt("maior"));
            } else {
                return this.ultimo();
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public Object proximo(int id) {
        try {
            statementSQL = con.prepareStatement("select * from " + nomeTabela + " order by id;");
            statementSQL = con.prepareStatement("select min(Id) as menor from " + nomeTabela + " where id>?;");
            statementSQL.setInt(1, id);
            ResultSet resultSet = statementSQL.executeQuery();
            resultSet.next();
            if (resultSet.getString("menor") != null) {
                return this.localizar(resultSet.getInt("menor"));
            } else {
                return this.primeiro();
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public Object primeiro() {
        try {
            statementSQL = con.prepareStatement("select * from " + nomeTabela + " order by id;");
            ResultSet resultSet = statementSQL.executeQuery();
            resultSet.next();
            return (new Pessoa(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("telefone")));
        } catch (SQLException ex) {
            return null;
        }
    }

    public Object ultimo() {
        try {
            statementSQL = con.prepareStatement("select * from " + nomeTabela + " order by id;");
            statementSQL = con.prepareStatement("select max(id) as maior from " + nomeTabela);
            ResultSet resultSet = statementSQL.executeQuery();
            resultSet.next();
            if (resultSet.getString("maior") != null) {
                int id = Integer.parseInt(resultSet.getString("maior"));
                return this.localizar(id);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public List lista() {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        try {
            statementSQL = con.prepareStatement("select * from " + nomeTabela + ";");
            ResultSet resultSet = statementSQL.executeQuery();
            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(resultSet.getInt("id"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setTelefone(resultSet.getString("telefone"));
                lista.add(pessoa);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }

    public String limpa() {
        try {
            String Sql = "delete from " + nomeTabela + ";";
            statementSQL = con.prepareStatement(Sql);
            statementSQL.execute();
            return "Tabela " + nomeTabela + " esta vazia!";
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
            return "Erro!";
        }
    }

    public int nroObjetos() {
        try {
            statementSQL = con.prepareStatement("select count(*) as contador from " + nomeTabela + ";");
            ResultSet resultSet = statementSQL.executeQuery();
            resultSet.next();
            return resultSet.getInt("contador");
        } catch (SQLException ex) {
            return 0;
        }
    }

    public int maiorID() {
        try {
            statementSQL = con.prepareStatement("select max(id) as maximo from " + nomeTabela + ";");
            ResultSet resultSet = statementSQL.executeQuery();
            resultSet.next();
            return resultSet.getInt("maximo") + 1;
        } catch (SQLException ex) {
            return 1;
        }
    }
}
