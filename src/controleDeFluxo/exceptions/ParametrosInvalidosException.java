package controleDeFluxo.exceptions;

public class ParametrosInvalidosException extends RuntimeException {

    private static final String DEFAULT_MESSAGE =
            "O segundo parâmetro deve ser maior que o primeiro.";

    public ParametrosInvalidosException() {
        super(DEFAULT_MESSAGE);
    }

}
