package detran;

import java.util.Date;

public class Ocorrencia {
    private String placa;
    private Date dataHora;
    private String nomeLogradouro;
    private int velocidadeMedia;
    private int tipoVeiculo; // 1 - Veículo leve, 2 - Caminhão

    public Ocorrencia(String placa, Date data, String logradouro, int velocidade, int tipoVeiculo) {
        this.placa = placa;
        this.dataHora = data;
        this.nomeLogradouro = logradouro;
        this.velocidadeMedia = velocidade;
        this.tipoVeiculo = tipoVeiculo;
    }
	// Getters e Setters
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public Date getDataHora() { return dataHora; }
    public void setDataHora(Date dataHora) { this.dataHora = dataHora; }

    public String getNomeLogradouro() { return nomeLogradouro; }
    public void setNomeLogradouro(String nomeLogradouro) { this.nomeLogradouro = nomeLogradouro; }

    public int getVelocidadeMedia() { return velocidadeMedia; }
    public void setVelocidadeMedia(int velocidadeMedia) { this.velocidadeMedia = velocidadeMedia; }

    public int getTipoVeiculo() { return tipoVeiculo; }
    public void setTipoVeiculo(int tipoVeiculo) { this.tipoVeiculo = tipoVeiculo; }
}
