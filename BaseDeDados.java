package detran;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class BaseDeDados {
    public List<Ocorrencia> getOcorrenciasSemProcessar() {
		return ocorrenciasSemProcessar;
	}

	public void setOcorrenciasSemProcessar(List<Ocorrencia> ocorrenciasSemProcessar) {
		this.ocorrenciasSemProcessar = ocorrenciasSemProcessar;
	}

	public List<Ocorrencia> getOcorrenciasProcessadas() {
		return ocorrenciasProcessadas;
	}

	public void setOcorrenciasProcessadas(List<Ocorrencia> ocorrenciasProcessadas) {
		this.ocorrenciasProcessadas = ocorrenciasProcessadas;
	}

	public List<Multa> getMultas() {
		return multas;
	}

	public void setMultas(List<Multa> multas) {
		this.multas = multas;
	}

	public List<RegraMulta> getRegras() {
		return regras;
	}

	public void setRegras(List<RegraMulta> regras) {
		this.regras = regras;
	}

	private List<Ocorrencia> ocorrenciasSemProcessar = new ArrayList<>();
    private List<Ocorrencia> ocorrenciasProcessadas = new ArrayList<>();
    private List<Multa> multas = new ArrayList<>();
    private List<RegraMulta> regras = new ArrayList<>();

    public void inicializaRegras() {
        // Regras de velocidade
        regras.add(new RegraVelocidade(60, "Avenida Paulista")); // Velocidade máxima de 60 km/h
        regras.add(new RegraVelocidade(50, "Rua Augusta"));      // Velocidade máxima de 50 km/h
        regras.add(new RegraVelocidade(40, "Rua da Consolação"));
        regras.add(new RegraVelocidade(70, "Avenida Rebouças"));
        regras.add(new RegraVelocidade(60, "Avenida Ibirapuera"));
        regras.add(new RegraVelocidade(30, "Rua Frei Caneca"));
        regras.add(new RegraVelocidade(80, "Rodovia dos Bandeirantes"));
        regras.add(new RegraVelocidade(90, "Rodovia Anhanguera"));
        regras.add(new RegraVelocidade(50, "Rua Haddock Lobo"));
        regras.add(new RegraVelocidade(40, "Rua Oscar Freire"));

        // Regras de corredor de ônibus
        regras.add(new RegraCorredorOnibus(6, 9, "Avenida Paulista"));      // Horário restrito: 6h-9h
        regras.add(new RegraCorredorOnibus(7, 10, "Avenida Brigadeiro"));
        regras.add(new RegraCorredorOnibus(16, 20, "Rua da Consolação"));  // Horário restrito: 16h-20h
        regras.add(new RegraCorredorOnibus(17, 19, "Avenida Rebouças"));
        regras.add(new RegraCorredorOnibus(6, 8, "Rua Augusta"));
        regras.add(new RegraCorredorOnibus(7, 9, "Rua Frei Caneca"));

        // Regras de rodízio
        regras.add(new RegraRodizio(1, new String[]{"Avenida Paulista", "Rua Augusta"}, 2));  // Final de placa 1
        regras.add(new RegraRodizio(2, new String[]{"Rua da Consolação"}, 1));               // Final de placa 2
        regras.add(new RegraRodizio(3, new String[]{"Avenida Brigadeiro"}, 1));              // Final de placa 3
        regras.add(new RegraRodizio(4, new String[]{"Avenida Rebouças"}, 2));                // Final de placa 4
        regras.add(new RegraRodizio(5, new String[]{"Avenida Ibirapuera"}, 2));              // Final de placa 5
        regras.add(new RegraRodizio(6, new String[]{"Rua Haddock Lobo"}, 2));                // Final de placa 6
        regras.add(new RegraRodizio(7, new String[]{"Rua Oscar Freire"}, 2));                // Final de placa 7
        regras.add(new RegraRodizio(8, new String[]{"Rodovia dos Bandeirantes"}, 1));        // Final de placa 8
        regras.add(new RegraRodizio(9, new String[]{"Rodovia Anhanguera"}, 1));              // Final de placa 9
        regras.add(new RegraRodizio(0, new String[]{"Rua Frei Caneca"}, 2));                 // Final de placa 0
    }


    public void processar() {
        for (Ocorrencia ocorrencia : ocorrenciasSemProcessar) {
            for (RegraMulta regra : regras) {
                Multa multa = regra.calcularMulta(ocorrencia);
                if (multa != null) {
                    multas.add(multa);
                }
            }
            ocorrenciasProcessadas.add(ocorrencia);
        }
        ocorrenciasSemProcessar.clear();
    }

    public List<Multa> buscarMultasPorPlaca(String placa) {
        List<Multa> resultado = new ArrayList<>();
        for (Multa multa : multas) {
            if (multa.getPlaca().equalsIgnoreCase(placa)) {
                resultado.add(multa);
            }
        }
        return resultado;
    }

    public List<Multa> buscarMultasPorData(LocalDate data) {
        List<Multa> resultado = new ArrayList<>();
        for (Multa multa : multas) {
        	LocalDate dataRegistro = multa.getData().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if (dataRegistro.equals(data)) {
                resultado.add(multa);
            }
        }
        return resultado;
    }
    
    public void adicionarRegra(RegraMulta regra) {
        regras.add(regra);
    }
}
