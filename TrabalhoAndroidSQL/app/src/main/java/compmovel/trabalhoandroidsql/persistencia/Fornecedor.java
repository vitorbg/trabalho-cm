package compmovel.trabalhoandroidsql.persistencia;

/**
 * Created by vitor on 16/12/15.
 */
public class Fornecedor {

    private int id;
    private String nome;
    private String telefone;

    public Fornecedor() {
    }

    public Fornecedor(int id, String nome, String telefone) {
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
}


