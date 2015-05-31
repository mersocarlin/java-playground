package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Pessoa;

/**
 *
 * @author hlscarlin
 */
public class DAOPessoa implements DAO {

    public static final String driver = "org.apache.derby.jdbc.ClientDriver";
    public static final String url = "jdbc:derby://localhost:1527/pessoa";
    public String usuario = "root";
    public String senha = "root";
    PreparedStatement pstmt = null;
    Connection con = null;
    String nomeTabela = "pessoa";

    /** Creates a new instance of JdbcPessoa */
    public DAOPessoa() {
    }

    public String conecta() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, senha);
            return "Conectado com o Banco de Dados!";
        } catch (ClassNotFoundException Driver) {
            return "Driver não Localizado " + Driver;
        } catch (SQLException fonte) {
            return "Deu erro na conexão com a fonte de dados " + fonte;
        }
    }

    public String desconecta() {
        try {
            con.close();
            return "Banco Fechado";
        } catch (SQLException erroSQL) {
            return "Não foi possivel " +
                    "fechar o bando de dados " + erroSQL.getMessage();
        }
    }

    public String inserir(Object o) {
        try {
            Pessoa p = (Pessoa) o;
            pstmt = con.prepareStatement("insert into " + nomeTabela +
                    " values (?,?,?)");
            pstmt.setString(1, p.getNome());
            pstmt.setString(2, p.getTelefone());
            pstmt.setInt(3, p.getId());
            pstmt.executeUpdate();
            return "Pessoa inserida!";
        } catch (SQLException ex) {
            return "Erro na insecao!";
        }
    }

    public String excluir(int id) {
        try {
            pstmt = con.prepareStatement("delete from " + nomeTabela +
                    " where Id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return "Pessoa removida!";
        } catch (SQLException ex) {
            return "Erro ao excluir pessoa!";
        }
    }

    public String atualizar(int id, Object o) {
        try {
            Pessoa p = (Pessoa) o;
            pstmt = con.prepareStatement("update " + nomeTabela +
                    " set Nome=?,Telefone=? where Id = ?");
            pstmt.setString(1, p.getNome());
            pstmt.setString(2, p.getTelefone());
            pstmt.setInt(3, p.getId());
            pstmt.executeUpdate();
            return "Pessoa atualizada!";
        } catch (SQLException ex) {
            return "Erro ao atualizar pessoa!";
        }
    }

    public String localizar(int id) {
        try {
            pstmt = con.prepareStatement("select * from " + nomeTabela + " where Id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String str = "";
            str += "nome=";
            str += rs.getString("Nome") + "&";
            str += "telefone=";
            str += rs.getString("Telefone") + "&";
            str += "id=";
            str += rs.getInt("Id");
            return str;
        } catch (SQLException ex) {
            return "Pessoa nao encontrada!";
        }
    }

    public String localizar(String nome) {
        try {
            pstmt = con.prepareStatement("select * from " + nomeTabela + " where Nome=?");
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String str = "";
            str += "nome=" + rs.getString("Nome") + "&";
            str += "telefone=" + rs.getString("Telefone") + "&";
            str += "id=" + rs.getInt("Id");
            return str;
        } catch (SQLException ex) {
            return "Pessoa nao encontrada!";
        }
    }

    public String primeiro() {
        try {
            pstmt = con.prepareStatement("select * from " + nomeTabela + " order by Id");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return "nome=" + rs.getString("Nome") + "&telefone=" + rs.getString("Telefone") + "&id=" + rs.getInt("Id");
        } catch (SQLException ex) {
            return "Pessoa nao encontrada!";
        }
    }

    public String ultimo() {
        try {
            pstmt = con.prepareStatement("select * from " + nomeTabela + " order by Id");
            pstmt = con.prepareStatement("select max(Id) as maior from " + nomeTabela);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if (rs.getString("maior") != null) {
                int id = Integer.parseInt(rs.getString("maior"));
                return this.localizar(id);
            } else {
                return "Pessoa nao encontrada!";
            }
        } catch (SQLException ex) {
            return "Pessoa nao encontrada!";
        }
    }

    public String proximo(int id) {
        try {
            pstmt = con.prepareStatement("select * from " + nomeTabela + " order by Id");
            pstmt = con.prepareStatement("select min(Id) as menor from " + nomeTabela + " where Id>?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if (rs.getString("menor") != null) {
                return this.localizar(rs.getInt("menor"));
            } else {
                return this.primeiro();
            }
        } catch (SQLException ex) {
            return "Pessoa nao encontrada!";
        }
    }

    public String anterior(int id) {
        try {
            pstmt = con.prepareStatement("select * from " + nomeTabela + " order by Id");
            pstmt = con.prepareStatement("select max(Id) as maior from " + nomeTabela + " where Id<?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if (rs.getString("maior") != null) {
                return this.localizar(rs.getInt("maior"));
            } else {
                return this.ultimo();
            }
        } catch (SQLException ex) {
            return "Pessoa nao encontrada!";
        }
    }

    public String nroObjetos() {
        try {
            pstmt = con.prepareStatement("select count(*) as contador from " + nomeTabela);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getString("contador");
        } catch (SQLException ex) {
            return "Ninguem foi inserido no banco!";
        }
    }

    public String setID() {
        try {
            pstmt = con.prepareStatement("select max(Id) as maior from " + nomeTabela);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("maior");
                return "" + (id + 1);
            }
        } catch (SQLException ex) {
            return "1";
        }
        return "";
    }

    public String limpaBanco() {
        try {
            pstmt = con.prepareStatement("delete from " + nomeTabela);
            pstmt.execute();
            return "Banco de dados esta vazio!";
        } catch (Exception e) {
            return "Erro!";
        }
    }
}
