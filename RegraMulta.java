package detran;

public abstract class RegraMulta {
    protected static final double VALOR_MULTA_LEVE = 100.0;
    protected static final double VALOR_MULTA_MEDIA = 200.0;
    protected static final double VALOR_MULTA_GRAVE = 400.0;

    public abstract int verificaNivelMulta(Ocorrencia ocorrencia);

    public Multa calcularMulta(Ocorrencia ocorrencia) {
        int nivel = verificaNivelMulta(ocorrencia);
        if (nivel > 0) {
            return new Multa(obterValorMulta(nivel), obterDescricaoMulta(), ocorrencia.getDataHora(), ocorrencia.getPlaca());
        }
        return null;
    }

    private double obterValorMulta(int nivel) {
        return switch (nivel) {
            case 1 -> VALOR_MULTA_LEVE;
            case 2 -> VALOR_MULTA_MEDIA;
            case 3 -> VALOR_MULTA_GRAVE;
            default -> 0;
        };
    }

    public abstract String obterDescricaoMulta();
}

