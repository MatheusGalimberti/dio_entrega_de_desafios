package controleDeFluxo;

import controleDeFluxo.exceptions.ParametrosInvalidosException;
import java.util.Scanner;

public class Contador{
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int inicio = lerNumero(sc, "Digite o primeiro numero: ");
            int fim = lerNumero(sc, "Digite o segundo numero: ");

            validarParametros(inicio, fim);
            imprimirContagem(inicio, fim);

        } catch (ParametrosInvalidosException e) {
            System.out.println(e.getMessage());
        }
    }

    static int lerNumero(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        return sc.nextInt();
    }

    static void validarParametros(int inicio, int fim) throws ParametrosInvalidosException {
        if (inicio > fim) throw new ParametrosInvalidosException();
    }

    static void imprimirContagem(int inicio, int fim) {
        for (int i = inicio; i <= fim; i++) {
            System.out.printf("Imprimindo o numero %d%n", i);
        }
    }
}
