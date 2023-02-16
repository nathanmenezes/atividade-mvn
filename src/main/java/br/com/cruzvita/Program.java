package br.com.cruzvita;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.cruzvita.entities.Banco;
import br.com.cruzvita.entities.Cliente;
import br.com.cruzvita.entities.Conta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Program {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Scanner sc = new Scanner(System.in);
        int operacao = 0;
        while (operacao != 7) {
            System.out.println("BANCO CRUZ VITA");

            System.out.println("-----------------");
            System.out.println("[1] Criar Conta");
            System.out.println("[2] Deletar Conta");
            System.out.println("[3] Listar Contas");
            System.out.println("[4] Depositar");
            System.out.println("[5] Transferir");
            System.out.println("[6] Sacar");
            System.out.println("[7] Sair");
            System.out.println("-----------------");

            System.out.print("Digite a operação que você deseja executar: ");
        operacao = sc.nextInt();

        switch (operacao) {
            case 1:
                System.out.println("CRIAÇÃO DE CONTA");
                sc.nextLine();
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("CPF: ");
                String cpf = sc.nextLine();
                System.out.print("Data de Nascimento (dd/MM/yyyy): ");
                Date dataNascimento = sdf.parse(sc.next());

                Cliente cliente = new Cliente(nome, cpf, dataNascimento);

                entityManager.getTransaction().begin();
                entityManager.persist(cliente);
                entityManager.getTransaction().commit();

                Conta conta = new Conta(cliente);

                entityManager.getTransaction().begin();
                entityManager.persist(conta);
                entityManager.getTransaction().commit();


                System.out.print("Qual o banco você deseja abrir conta: ");
                sc.nextLine();
                String nomeBanco = sc.nextLine();
                Banco banco = new Banco(nomeBanco, conta);

                entityManager.getTransaction().begin();
                entityManager.persist(banco);
                entityManager.getTransaction().commit();

                System.out.println("Conta criada com sucesso!");
            break;

            case 2:
                System.out.println("DELEÇÃO DE CONTA");
                System.out.print("Digite o ID da conta a ser deletada: ");
                long id = sc.nextLong();

                banco = entityManager.find(Banco.class, id);
                entityManager.getTransaction().begin();
                entityManager.remove(banco);
                entityManager.getTransaction().commit();

                System.out.println("Conta deletada com sucesso!");
            break;
            case 3:
                System.out.println("LISTA DE CONTAS");
                Query query = entityManager.createQuery("SELECT b FROM Banco b");
                List<Banco> bancos = query.getResultList();
                for (Banco element : bancos) {
                    System.out.println("----------------");
                    System.out.println(element);
                    System.out.println("----------------");
                }
            break;
            case 4:
                System.out.println("DEPOSITO");
                System.out.print("Digite o ID de qual conta você deseja depositar: ");
                long idDeposito = sc.nextInt();

                banco = entityManager.find(Banco.class, idDeposito);

                System.out.print("Digite a quantidade a ser depositada: ");
                double quantidadeDeposito = sc.nextDouble();
                banco.deposito(quantidadeDeposito);

                entityManager.getTransaction().begin();
                entityManager.merge(banco);
                entityManager.getTransaction().commit();

                System.out.println("Deposito efetuado com sucesso!");
            break;
            case 5:
                System.out.println("TRANSFERIR");
                System.out.print("Digite o ID da conta que deseja transferir: ");
                long idTransferencia = sc.nextInt();
                System.out.print("Digite a quantidade a ser transferida: ");
                double quantidadeTransferencia = sc.nextDouble();
                banco = entityManager.find(Banco.class, idTransferencia);

                System.out.print("Digite o ID da conta que deseja receber a transferencia: ");
                long idRecebimento = sc.nextInt();

                Banco banco2 = entityManager.find(Banco.class, idRecebimento);

                banco.transferir(quantidadeTransferencia, banco2);

                entityManager.getTransaction().begin();
                entityManager.merge(banco);
                entityManager.merge(banco2);
                entityManager.getTransaction().commit();
                System.out.println("Transferencia efetuada com sucesso!");
            break;
            case 6:
                System.out.println("SAQUE");
                System.out.print("Digite o ID de qual conta você deseja sacar: ");
                long idSaque = sc.nextInt();

                banco = entityManager.find(Banco.class, idSaque);

                System.out.println("Digite a quantidade que você deseja sacar: ");
                double quantidadeSaque = sc.nextDouble();
                banco.sacar(quantidadeSaque);

                entityManager.getTransaction().begin();
                entityManager.merge(banco);
                entityManager.getTransaction().commit();

                System.out.println("Saque efetuado com sucesso!");
            break;
            default:
                break;
        }
        }
        entityManagerFactory.close();
        entityManager.close();
        sc.close();
    }
}
