package compmovel.trabalhoandroidsql.persistencia;

/**
 * Created by vitor on 08/10/15.
 */
public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private String foto;

    public Produto(int id, String nome, String descricao, double preco, String foto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.foto = foto;
    }

    public Produto(){

    }

    public Produto(String nome, String descricao, double preco, String foto) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.foto = foto;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
