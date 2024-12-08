package detran;

import java.util.Arrays;

public class RegraRodizio extends RegraMulta {
    private int finalPlaca;
    private String[] logradourosAfetados;
    private int diaDaSemana; // Exemplo: 1 = Segunda, 2 = Terça...

    public RegraRodizio(int finalPlaca, String[] logradourosAfetados, int diaDaSemana) {
        this.finalPlaca = finalPlaca;
        this.logradourosAfetados = logradourosAfetados;
        this.diaDaSemana = diaDaSemana;
    }

    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        String placa = ocorrencia.getPlaca();
        int ultimoDigito = Character.getNumericValue(placa.charAt(placa.length() - 1));

        // Verificar se o final da placa coincide com o rodízio
        if (ultimoDigito != finalPlaca) {
            return 0;
        }

        // Verificar se o logradouro está na lista dos afetados
        if (!Arrays.asList(logradourosAfetados).contains(ocorrencia.getNomeLogradouro())) {
            return 0;
        }

        // Verificar se o dia da ocorrência coincide com o dia do rodízio
        int diaOcorrencia = ocorrencia.getDataHora().getDay(); // 0 = Domingo, 1 = Segunda, etc.
        if (diaOcorrencia != diaDaSemana) {
            return 0;
        }

        return 2; // Multa média
    }

    @Override
    public String obterDescricaoMulta() {
        return "Infração de rodízio no logradouro: " + Arrays.toString(logradourosAfetados);
    }
}

