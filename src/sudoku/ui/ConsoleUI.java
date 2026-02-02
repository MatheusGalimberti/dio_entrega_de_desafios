package sudoku.ui;

import sudoku.core.Board;
import sudoku.core.GameService;

import java.util.Locale;
import java.util.Scanner;

public class ConsoleUI {

    private final Board board;
    private final GameService game;

    public ConsoleUI(Board board, GameService game) {
        this.board = board;
        this.game = game;
    }

    public void start() {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        printHelp();
        board.print();

        while (true) {
            System.out.print("\n> ");
            String line = sc.nextLine().trim();

            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String cmd = parts[0].toLowerCase();

            try {
                switch (cmd) {
                    case "help" -> printHelp();

                    case "quit", "exit" -> {
                        System.out.println("Saindo...");
                        sc.close();
                        return;
                    }

                    case "set" -> {
                        if (parts.length != 4) {
                            System.out.println("Uso: set LINHA COLUNA VALOR (ex: set 0 2 9)");
                            break;
                        }
                        int l = parseInt(parts[1], "linha");
                        int c = parseInt(parts[2], "coluna");
                        int v = parseInt(parts[3], "valor");

                        boolean ok = game.set(board, l, c, v);
                        if (!ok) {
                            System.out.println("Jogada inválida (conflito ou célula fixa/valor inválido).");
                        } else {
                            System.out.println("OK.");
                        }

                        board.print();
                        if (game.isSolved(board)) {
                            System.out.println("\n🎉 Parabéns! Sudoku resolvido!");
                            sc.close();
                            return;
                        }
                    }

                    case "clear" -> {
                        if (parts.length != 3) {
                            System.out.println("Uso: clear LINHA COLUNA (ex: clear 0 2)");
                            break;
                        }
                        int l = parseInt(parts[1], "linha");
                        int c = parseInt(parts[2], "coluna");

                        boolean ok = game.clear(board, l, c);
                        if (!ok) {
                            System.out.println("Não é possível limpar (célula fixa ou posição inválida).");
                        } else {
                            System.out.println("OK.");
                        }

                        board.print();
                    }

                    default -> System.out.println("Comando desconhecido. Digite: help");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private int parseInt(String raw, String field) {
        try {
            return Integer.parseInt(raw);
        } catch (Exception e) {
            throw new NumberFormatException("Campo '" + field + "' inválido: " + raw);
        }
    }

    private void printHelp() {
        System.out.println("""
                Comandos:
                  help                      -> mostra ajuda
                  set LINHA COLUNA VALOR    -> coloca VALOR (1..9) (ex: set 0 2 9)
                  clear LINHA COLUNA        -> limpa posição (ex: clear 0 2)
                  quit                      -> sai

                Índices: LINHA e COLUNA vão de 0 a 8.
                Dica: '.' no tabuleiro significa vazio.
                """);
    }
}
