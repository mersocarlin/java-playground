/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author Hemerson
 */
public class Pessoa {

    private int id;
    private String nome;
    private String telefone;

    public Pessoa() {
        this.id = 0;
        this.nome = null;
        this.telefone = null;
    }

    public Pessoa(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.telefone = pessoa.getTelefone();
    }

    public Pessoa(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "\n\nID: " + this.getId() +
                "\nNome: " + this.getNome() +
                "\nTelefone: " + this.getTelefone();
    }
}
