package conta;
import java.util.Scanner;


public class ContaTerminal {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numero;
        String agencia;
        String nomeCliente;
        double saldo;

        System.out.print("Por favor digite o numeoro da agência: ");
        numero = sc.nextInt();
        System.out.print("digite o nome da agencia :");
        agencia = sc.next();
        System.out.print("digite seu nome por favor: ");
        nomeCliente = sc.next();
        System.out.print("digite seu saldo: ");
        saldo = sc.nextDouble();

        System.out.printf("Ola %s, obrigado por criar uma conta no nosso banco, sua agência é %s, conta %d," +
                " e seu saldo é %.2f já esta diponivel para saque", nomeCliente, agencia, numero,saldo);

        sc.close();
    }


}
