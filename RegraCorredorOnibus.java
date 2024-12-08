package detran;

public class RegraCorredorOnibus extends RegraMulta {
    private int horaInicial;
    private int horaFinal;
    private String nomeLogradouro;

    public RegraCorredorOnibus(int horaInicial, int horaFinal, String nomeLogradouro) {
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.nomeLogradouro = nomeLogradouro;
    }

    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        if (!ocorrencia.getNomeLogradouro().equalsIgnoreCase(nomeLogradouro)) {
            return 0;
        }

        int horaOcorrencia = ocorrencia.getDataHora().getHours();
        if (horaOcorrencia >= horaInicial && horaOcorrencia <= horaFinal) {
            return 3; // Multa grave
        }

        return 0; // Sem multa
    }

    @Override
    public String obterDescricaoMulta() {
        return "Circulação irregular no corredor de ônibus: " + nomeLogradouro;
    }
}

