package detran;

public class RegraVelocidade extends RegraMulta {
    private int velocidadeMaxima;
    private String nomeLogradouro;

    public RegraVelocidade(int velMax, String logra) {
        this.velocidadeMaxima = velMax;
        this.nomeLogradouro = logra;
    }

    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        if (!ocorrencia.getNomeLogradouro().equalsIgnoreCase(nomeLogradouro)) {
            return 0;
        }
        int excesso = ocorrencia.getVelocidadeMedia() - velocidadeMaxima;
        if (excesso <= 0) return 0;
        if (excesso <= velocidadeMaxima * 0.1) return 1;
        if (excesso <= velocidadeMaxima * 0.4) return 2;
        return 3;
    }

    @Override
    public String obterDescricaoMulta() {
        return "Excesso de velocidade no logradouro: " + nomeLogradouro;
    }
}
