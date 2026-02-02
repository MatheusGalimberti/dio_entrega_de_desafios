package sudoku.core;

public class SudokuValidator {

    public boolean podeColocar(Board board, int linha, int coluna, int valor) {
        if (valor < 1 || valor > 9) return false;

        var alvo = board.getEspaco(linha, coluna);

        if (alvo.isFixo()) return false;

        if (existeNaLinha(board, linha, coluna, valor)) return false;
        if (existeNaColuna(board, linha, coluna, valor)) return false;
        if (existeNoBloco(board, linha, coluna, valor)) return false;

        return true;
    }

    public boolean podeLimpar(Board board, int linha, int coluna) {
        var alvo = board.getEspaco(linha, coluna);
        return !alvo.isFixo();
    }

    private boolean existeNaLinha(Board board, int linha, int colunaIgnorar, int valor) {
        for (int c = 0; c < 9; c++) {
            if (c == colunaIgnorar) continue;
            if (board.getEspaco(linha, c).getValor() == valor) return true;
        }
        return false;
    }

    private boolean existeNaColuna(Board board, int linhaIgnorar, int coluna, int valor) {
        for (int l = 0; l < 9; l++) {
            if (l == linhaIgnorar) continue;
            if (board.getEspaco(l, coluna).getValor() == valor) return true;
        }
        return false;
    }

    private boolean existeNoBloco(Board board, int linha, int coluna, int valor) {
        int startLinha = (linha / 3) * 3;
        int startColuna = (coluna / 3) * 3;

        for (int l = startLinha; l < startLinha + 3; l++) {
            for (int c = startColuna; c < startColuna + 3; c++) {
                if (l == linha && c == coluna) continue;
                if (board.getEspaco(l, c).getValor() == valor) return true;
            }
        }
        return false;
    }

    // Vitória: completo e sem conflitos
    public boolean estaResolvido(Board board) {
        for (int l = 0; l < 9; l++) {
            for (int c = 0; c < 9; c++) {
                int v = board.getEspaco(l, c).getValor();
                if (v == 0) return false; // ainda tem vazio

                if (existeNaLinha(board, l, c, v)) return false;
                if (existeNaColuna(board, l, c, v)) return false;
                if (existeNoBloco(board, l, c, v)) return false;
            }
        }
        return true;
    }
}
