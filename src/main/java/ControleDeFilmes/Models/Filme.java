//classe filmes
package ControleDeFilmes.Models;

import jakarta.persistence.*;

@Entity
public class Filme {
    
    @Id
    @SequenceGenerator(name = "filme_seq", sequenceName = "filme_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filme_seq")
    private Long id;

    
    @Column(nullable = false, unique = true)
    private String titulo;
    
    @Column(nullable = false)
    private String genero;
    
    @Column(nullable = false)
    private Integer anoLancamento;

    public Filme() {}

    public Filme(String titulo, String genero, Integer anoLancamento) {
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public String toString() {
        return "Filme{" +
               "id=" + id +
               ", titulo='" + titulo + '\'' +
               ", genero='" + genero + '\'' +
               ", anoLancamento=" + anoLancamento +
               '}';
    }
}