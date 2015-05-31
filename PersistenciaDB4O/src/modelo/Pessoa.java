package modelo;

/**
 *
 * @author hlscarlin
 */
public class Pessoa implements Comparable {

    private String nome;
    private String telefone;
    private int id;

    public Pessoa() {
        this.nome = "";
        this.telefone = "";
        this.id = 0;
    }

    public Pessoa(int id) {
        this.nome = null;
        this.telefone = null;
        this.id = id;
    }

    public Pessoa(String nome) {
        this.nome = nome;
        this.telefone = null;
        this.id = 0;
    }

    public Pessoa(String n, String tel, int id) {
        this.nome = n;
        this.telefone = tel;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int compareTo(Object o) {
        Pessoa p = (Pessoa) o;
        //return this.getNome().compareTo(p.getNome()); //ordenacao apenas por nome

        //ordena por nome e por telefone
        if (this.getNome().compareTo(p.getNome()) == 0) {
            return this.getTelefone().compareTo(p.getTelefone());
        } else {
            return this.getNome().compareTo(p.getNome());
        }
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + " Nome: " + this.getNome() + "Tel: " + this.getTelefone();
    }
}
