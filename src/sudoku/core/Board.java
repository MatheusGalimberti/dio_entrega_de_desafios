package sudoku.core;

import sudoku.model.Espaco;

public class Board {

    private final Espaco[][] grid = new Espaco[9][9];

    public Board(int[][] initial) {
        if (initial == null || initial.length != 9) {
            throw new IllegalArgumentException("Puzzle inicial inválido: precisa ter 9 linhas.");
        }

        for (int l = 0; l < 9; l++) {
            if (initial[l] == null || initial[l].length != 9) {
                throw new IllegalArgumentException("Puzzle inicial inválido: cada linha precisa ter 9 colunas.");
            }

            for (int c = 0; c < 9; c++) {
                int valor = initial[l][c];
                boolean fixo = valor != 0;

                if (valor < 0 || valor > 9) {
                    throw new IllegalArgumentException("Valor inválido no puzzle: " + valor + " em (" + l + "," + c + ")");
                }

                grid[l][c] = new Espaco(l, c, valor, fixo);
            }
        }
    }

    public Espaco getEspaco(int linha, int coluna) {
        validarPosicao(linha, coluna);
        return grid[linha][coluna];
    }

    public void setValor(int linha, int coluna, int valor) {
        getEspaco(linha, coluna).setValor(valor);
    }

    public void validarPosicao(int linha, int coluna) {
        if (linha < 0 || linha > 8 || coluna < 0 || coluna > 8) {
            throw new IndexOutOfBoundsException("Posição inválida: linha=" + linha + ", coluna=" + coluna);
        }
    }

    public void print() {
        System.out.print("    ");
        for (int col = 0; col < 9; col++) {
            System.out.print(col + " ");
            if (col == 2 || col == 5) System.out.print("  ");
        }
        System.out.println();
        System.out.println("   -------------------------");

        for (int row = 0; row < 9; row++) {
            System.out.print(row + " | ");

            for (int col = 0; col < 9; col++) {
                int value = grid[row][col].getValor();
                System.out.print((value == 0 ? "." : value) + " ");

                if (col == 2 || col == 5) {
                    System.out.print("| ");
                }
            }

            System.out.println();

            if (row == 2 || row == 5) {
                System.out.println("   |-------+-------+-------|");
            }
        }

        System.out.println("   -------------------------");
    }
}
