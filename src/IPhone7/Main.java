package IPhone7;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        iPhone iphone = new iPhone();
        Scanner sc = new Scanner(System.in);

        // inicia as chamadas automáticas
        iphone.iniciarChamadasAutomaticas();

        boolean executando = true;

        while (executando) {

            System.out.println("\n===== MENU IPHONE =====");
            System.out.println("1 - Ligar");
            System.out.println("2 - Atender ligação");
            System.out.println("3 - Recusar ligação");
            System.out.println("4 - Desligar ligação");
            System.out.println("5 - Correio de voz");

            System.out.println("6 - Selecionar música");
            System.out.println("7 - Tocar música");
            System.out.println("8 - Pausar música");

            System.out.println("9 - Abrir página web");
            System.out.println("10 - Nova aba");
            System.out.println("11 - Atualizar página");
            System.out.println("12 - Fechar página");

            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");

            int opcao;

            try {
                opcao = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida.");
                sc.nextLine(); // limpa buffer
                continue;
            }

            switch (opcao) {

                case 1 -> {
                    System.out.print("Digite o número: ");
                    int numero = sc.nextInt();
                    iphone.ligar(numero);
                }

                case 2 -> iphone.atender();

                case 3 -> iphone.recusarLigacao();

                case 4 -> iphone.desligandoLigacao();

                case 5 -> iphone.iniciarCorreioDeVoz();

                case 6 -> iphone.selecionarMusica();

                case 7 -> iphone.tocar();

                case 8 -> iphone.pausar();

                case 9 -> iphone.exibirPagina();

                case 10 -> iphone.adicionarNovaAba();

                case 11 -> iphone.atualizarPagina();

                case 12 -> iphone.excluirPagina();

                case 0 -> {
                    System.out.println("Encerrando o iPhone...");
                    iphone.pararChamadasAutomaticas();
                    executando = false;
                }

                default -> System.out.println("Opção inválida.");
            }
        }

        sc.close();
        System.out.println("Programa finalizado.");
    }
}
