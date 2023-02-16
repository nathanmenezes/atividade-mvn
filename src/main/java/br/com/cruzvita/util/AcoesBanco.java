package br.com.cruzvita.util;

import br.com.cruzvita.entities.Banco;

public interface AcoesBanco {
    public void transferir(double quantidade, Banco banco);
    public void sacar(double quantidade);
    public void deposito(double quantidade);
}
