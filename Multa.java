package detran;

import java.util.Date;

public class Multa {
    private double valor;
    private String motivo;
    private Date data;
    private String placa;

    public Multa(double valor, String motivo, Date data, String placa) {
        this.valor = valor;
        this.motivo = motivo;
        this.data = data;
        this.placa = placa;
    }

    // Getters e Setters
    public double getValor() { return valor; }
    public String getMotivo() { return motivo; }
    public Date getData() { return data; }
    public String getPlaca() { return placa; }
}

