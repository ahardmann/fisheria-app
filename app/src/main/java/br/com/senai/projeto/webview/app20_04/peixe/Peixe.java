package br.com.senai.projeto.webview.app20_04.peixe;

public class Peixe {

    private Long id;
    private String nome;
    private String origem;
    private String porte;
    private String isca;

    public Peixe() {
    }

    public Peixe(Long id, String nome, String origem, String isca, String porte) {
        this.id = id;
        this.nome = nome;
        this.origem = origem;
        this.isca = isca;
        this.porte = porte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getIsca() {
        return isca;
    }

    public void setIsca(String isca) {
        this.isca = isca;
    }
}
