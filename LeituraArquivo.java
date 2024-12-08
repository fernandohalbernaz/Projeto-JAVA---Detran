package detran;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LeituraArquivo {

	// Método para ler ocorrências de um arquivo
	public List<Ocorrencia> lerOcorrencias(String nomeArquivo) {
	    List<Ocorrencia> ocorrencias = new ArrayList<>();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    // Define o caminho do arquivo dentro da pasta src/dados
	    File arquivo = new File("src/dados/" + nomeArquivo);

	    // Verifica se o arquivo existe antes de tentar lê-lo
	    if (!arquivo.exists()) {
	        System.out.println("O arquivo " + arquivo.getAbsolutePath() + " não foi encontrado.");
	        return ocorrencias;
	    }

	    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
	        String linha;
	        while ((linha = br.readLine()) != null) {
	            String[] campos = linha.split(",");
	            if (campos.length == 5) {
	                String placa = campos[0];
	                Date dataHora = sdf.parse(campos[1]);
	                String logradouro = campos[2];
	                int velocidade = Integer.parseInt(campos[3]);
	                int tipoVeiculo = Integer.parseInt(campos[4]);

	                Ocorrencia ocorrencia = new Ocorrencia(placa, dataHora, logradouro, velocidade, tipoVeiculo);
	                ocorrencias.add(ocorrencia);
	            }
	        }
	    } catch (IOException | java.text.ParseException e) {
	        System.out.println("Erro ao ler o arquivo: " + e.getMessage());
	    }

	    return ocorrencias;
	}


    // Método para escrever uma ocorrência no arquivo
    public void escreverOcorrenciaNoArquivo(Ocorrencia ocorrencia, String caminhoRelativo) {
        try {
            // Caminho dentro de src/dados
            File arquivo = new File("src/dados/" + caminhoRelativo);
            
            // Garante que o diretório "dados" dentro de "src" exista
            File diretorio = arquivo.getParentFile();
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            // Escreve no arquivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String linha = ocorrencia.getPlaca() + ","
                        + sdf.format(ocorrencia.getDataHora()) + ","
                        + ocorrencia.getNomeLogradouro() + ","
                        + ocorrencia.getVelocidadeMedia() + ","
                        + ocorrencia.getTipoVeiculo();
                bw.write(linha);
                bw.newLine();
            }

            System.out.println("Ocorrência salva no arquivo: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
    
    public List<Ocorrencia> buscarOcorrenciasPorData(String dataFiltro, String caminhoArquivo) {
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        SimpleDateFormat sdfArquivo = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            // Converte a string da data de filtro em LocalDate
            LocalDate dataBusca = LocalDate.parse(dataFiltro);

            // Lê as ocorrências do arquivo
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] campos = linha.split(",");
                    if (campos.length == 5) {
                        Date dataHora = sdfArquivo.parse(campos[1]);

                        // Converte a data do registro para LocalDate
                        LocalDate dataRegistro = dataHora.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();

                        // Compara as datas
                        if (dataRegistro.isEqual(dataBusca)) {
                            String placa = campos[0];
                            String logradouro = campos[2];
                            int velocidade = Integer.parseInt(campos[3]);
                            int tipoVeiculo = Integer.parseInt(campos[4]);

                            Ocorrencia ocorrencia = new Ocorrencia(placa, dataHora, logradouro, velocidade, tipoVeiculo);
                            ocorrencias.add(ocorrencia);
                        }
                    }
                }
            }

        } catch (IOException | ParseException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }

        return ocorrencias;
    }
}
