//classe usuarios
package ControleDeFilmes.Models;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    private Long id;

    @Column(unique = true)
    private String login;

    private String senha;
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String perfil = "CLIENTE";

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        if (this.id == null) {
            this.perfil = "CLIENTE";
        } else {
            this.perfil = perfil;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "id=" + id +
               ", login='" + login + '\'' +
               ", nome='" + nome + '\'' +
               ", email='" + email + '\'' +
               ", perfil='" + perfil + '\'' +
               '}';
    }
}