package br.com.cruzvita.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_CLIENTE")
@Getter
@Setter
public class Cliente {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    
    public Cliente(){}

    public Cliente(String nome, String cpf, Date dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "ID Cliente: " + id + "\n Nome: " + nome + "\n CPF: " + cpf + " \n Data de Nascimento: " + sdf.format(dataNascimento);
    }

    
}
