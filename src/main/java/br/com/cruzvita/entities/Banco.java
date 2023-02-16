package br.com.cruzvita.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_BANCO")
@Getter
@Setter
public class Banco{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @OneToOne
    @JoinColumn(name = "codigo_conta", referencedColumnName = "id")
    private Conta conta;

    public Banco(){}

    public Banco(String nome, Conta conta) {
        this.nome = nome;
        this.conta = conta;
    }

    public void deposito(double quantidade){
        conta.setSaldo(conta.getSaldo() + quantidade);
    }

    public void transferir(double quantidade, Banco banco){
        if(this.conta.getSaldo() >= quantidade){
            this.conta.setSaldo(this.conta.getSaldo() - quantidade);
            banco.getConta().setSaldo(banco.getConta().getSaldo() + quantidade);
        }else{
            System.out.println("Saldo insuficiente para efetuar a transferencia!");
        }
    }

    public void sacar(double quantidade){
        if(conta.getSaldo() >= quantidade){
            conta.setSaldo(conta.getSaldo() - quantidade);
        }else{
            System.out.println("Saldo insuficiente para efetuar o saque!");
        }
    }

    @Override
    public String toString() {
        return " ID Banco: " + id + "\n Nome Banco: " + nome
                +conta;
    }

    
}
