package sudoku.core;

public class GameService {

    private final SudokuValidator validator = new SudokuValidator();

    public boolean set(Board board, int linha, int coluna, int valor) {
        if (!validator.podeColocar(board, linha, coluna, valor)) {
            return false;
        }
        board.setValor(linha, coluna, valor);
        return true;
    }

    public boolean clear(Board board, int linha, int coluna) {
        if (!validator.podeLimpar(board, linha, coluna)) {
            return false;
        }
        board.setValor(linha, coluna, 0);
        return true;
    }

    public boolean isSolved(Board board) {
        return validator.estaResolvido(board);
    }
}
