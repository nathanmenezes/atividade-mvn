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
@Table(name = "TB_CONTA")
@Getter
@Setter
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "id")
    private Cliente cliente;
    private double saldo = 0;

    public Conta(){}

    public Conta(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "\n ID Conta: " + id + "\n Saldo: " + saldo + "\n" + cliente;
    }

    
}
