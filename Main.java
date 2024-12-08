package detran;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BaseDeDados baseDeDados = new BaseDeDados();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato para salvar

        CadastroRegras cadastroRegras = new CadastroRegras(baseDeDados);
        LeituraArquivo leituraArquivo = new LeituraArquivo();

        baseDeDados.inicializaRegras();

        List<Ocorrencia> ocorrenciasDoArquivo = leituraArquivo.lerOcorrencias("ocorrencias.txt");
        baseDeDados.getOcorrenciasProcessadas().addAll(ocorrenciasDoArquivo);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            baseDeDados.processar();

            System.out.println("Sistema de Controle de Multas");
            System.out.println("1. Registrar ocorrência");
            System.out.println("2. Visualizar ocorrências");
            System.out.println("3. Visualizar multas");
            System.out.println("4. Buscar multas por placa");
            System.out.println("5. Buscar multas por data");
            System.out.println("6. Cadastrar uma nova regra");
            System.out.println("7. Ler ocorrências do arquivo");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
            case 1:
                String placa;
                do {
                    System.out.print("Digite a placa do veículo (ex.: ABC-1234): ");
                    placa = scanner.nextLine();
                } while (!validarPlaca(placa));

                String dataStr;
                Date data;
                do {
                    System.out.print("Digite a data (yyyy-MM-dd): ");
                    dataStr = scanner.nextLine();
                    data = validarData(dataStr);
                } while (data == null);

                String logradouro;
                do {
                    System.out.print("Digite o nome do logradouro: ");
                    logradouro = scanner.nextLine();
                } while (logradouro.isEmpty());

                int velocidade;
                do {
                    System.out.print("Digite a velocidade (km/h): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("A velocidade deve ser um número.");
                        scanner.next();
                    }
                    velocidade = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                } while (velocidade <= 0);

                Ocorrencia ocorrencia = new Ocorrencia(placa, data, logradouro, velocidade, 1); // Veículo leve
                baseDeDados.getOcorrenciasSemProcessar().add(ocorrencia);
                leituraArquivo.escreverOcorrenciaNoArquivo(ocorrencia, "ocorrencias.txt");
                System.out.println("Ocorrência registrada com sucesso!");
                break;

                case 2:
                    System.out.println("Ocorrências cadastradas:");
                    for (Ocorrencia ocorr : baseDeDados.getOcorrenciasProcessadas()) {
                        System.out.println("==========================");
                        System.out.println("data: " + sdf.format(ocorr.getDataHora()));
                        System.out.println("placa: " + ocorr.getPlaca());
                        System.out.println("Tipo de veículo: " + (ocorr.getTipoVeiculo() == 1 ? "leve" : (ocorr.getTipoVeiculo() == 2 ? "caminhão" : "desconhecido")));
                        System.out.println("velocidade média: " + ocorr.getVelocidadeMedia());
                        System.out.println("==========================");
                    }
                    break;

                case 3:
                    System.out.println("Multas registradas:");
                    for (Multa multa : baseDeDados.getMultas()) {
                        System.out.println("==========================");
                        System.out.println("data: " + sdf.format(multa.getData()));
                        System.out.println("placa: " + multa.getPlaca());
                        System.out.println("motivo: " + multa.getMotivo());
                        System.out.println("R$: " + multa.getValor());
                        System.out.println("==========================");
                    }
                    break;

                case 4:
                    System.out.print("Digite a placa para buscar: ");
                    String placaBusca = scanner.nextLine();
                    System.out.println("Multas encontradas para a placa " + placaBusca + ":");
                    for (Multa multa : baseDeDados.buscarMultasPorPlaca(placaBusca)) {
                        System.out.println("==========================");
                        System.out.println("data: " + sdf.format(multa.getData()));
                        System.out.println("placa: " + multa.getPlaca());
                        System.out.println("motivo: " + multa.getMotivo());
                        System.out.println("R$: " + multa.getValor());
                        System.out.println("==========================");
                    }
                    break;

                case 5:
                    System.out.print("Digite a data (yyyy-MM-dd): ");
                    String dataBuscaStr = scanner.nextLine();
                    LocalDate dataBusca = LocalDate.parse(dataBuscaStr);
                    System.out.println("Multas encontradas:");
                    for (Multa multa : baseDeDados.buscarMultasPorData(dataBusca)) {
                        System.out.println("==========================");
                        System.out.println("data: " + sdf.format(multa.getData()));
                        System.out.println("placa: " + multa.getPlaca());
                        System.out.println("motivo: " + multa.getMotivo());
                        System.out.println("R$: " + multa.getValor());
                        System.out.println("==========================");
                    }
                    break;

                case 6:
                    cadastroRegras.cadastrarRegra();
                    break;

                case 7:
                    List<Ocorrencia> ocorrenciasLidas = leituraArquivo.lerOcorrencias("ocorrencias.txt");
                    System.out.println("Ocorrências lidas do arquivo:");
                    for (Ocorrencia ocorr : ocorrenciasLidas) {
                        System.out.println("==========================");
                        System.out.println("data: " + sdf.format(ocorr.getDataHora()));
                        System.out.println("placa: " + ocorr.getPlaca());
                        System.out.println("Tipo de veículo: " + (ocorr.getTipoVeiculo() == 1 ? "leve" : (ocorr.getTipoVeiculo() == 2 ? "caminhão" : "desconhecido")));
                        System.out.println("velocidade média: " + ocorr.getVelocidadeMedia());
                        System.out.println("==========================");
                    }
                    break;

                case 8:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    
    private static boolean validarPlaca(String placa) {
        String regex = "^[A-Z]{3}-[0-9]{4}$|^[A-Z]{3}[0-9][A-Z][0-9]{2}$";
        if (!placa.matches(regex)) {
            System.out.println("Placa inválida! Deve estar no formato ABC-1234 ou ABC1D23.");
            return false;
        }
        return true;
    }

    private static Date validarData(String dataStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            return sdf.parse(dataStr);
        } catch (ParseException e) {
            System.out.println("Data inválida! Use o formato yyyy-MM-dd.");
            return null;
        }
    }
}
