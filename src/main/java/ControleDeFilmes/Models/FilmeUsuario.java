package ControleDeFilmes.Models;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class FilmeUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "filme_id", nullable = false)
    private Filme filme;
    
    @Column(nullable = false)
    private boolean assistido = false;

    @Column
    private LocalDate dataAssistido;

    public FilmeUsuario() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public boolean isAssistido() {
        return assistido;
    }

    public void setAssistido(boolean assistido) {
        this.assistido = assistido;
    }

    public LocalDate getDataAssistido() {
        return dataAssistido;
    }

    public void setDataAssistido(LocalDate dataAssistido) {
        this.dataAssistido = dataAssistido;
    }

    @Override
    public String toString() {
        return "FilmeUsuario{" +
               "id=" + id +
               ", usuario=" + (usuario != null ? usuario.getEmail() : "null") +
               ", filme=" + (filme != null ? filme.getTitulo() : "null") +
               ", assistido=" + assistido +
               ", dataAssistido=" + dataAssistido +
               '}';
    }
}