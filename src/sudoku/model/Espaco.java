package sudoku.model;

public class Espaco {

    private int valor;          // 0 = vazio
    private final int linha;
    private final int coluna;
    private final boolean fixo;

    public Espaco(int linha, int coluna, int valor, boolean fixo) {
        this.linha = linha;
        this.coluna = coluna;
        this.valor = valor;
        this.fixo = fixo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        if (fixo) {
            throw new IllegalStateException("Não é possível alterar um espaço fixo.");
        }
        this.valor = valor;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public boolean isFixo() {
        return fixo;
    }
}
