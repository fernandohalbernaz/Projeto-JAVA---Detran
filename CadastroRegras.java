package detran;
import java.util.Scanner;

public class CadastroRegras {
    private BaseDeDados baseDeDados; // A base de dados onde as regras serão armazenadas

    public CadastroRegras(BaseDeDados baseDeDados) {
        this.baseDeDados = baseDeDados;
    }

    // Método para exibir a interface e cadastrar uma nova regra
    public void cadastrarRegra() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o tipo da regra:");
        System.out.println("1 - Regra de Velocidade");
        System.out.println("2 - Regra de Rodízio");
        System.out.println("3 - Regra de Corredor de Ônibus");

        int tipoRegra = Integer.parseInt(scanner.nextLine());

        switch (tipoRegra) {
            case 1:
                cadastrarRegraVelocidade(scanner);
                break;
            case 2:
                cadastrarRegraRodizio(scanner);
                break;
            case 3:
                cadastrarRegraCorredorOnibus(scanner);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    // Método para cadastrar uma nova regra de velocidade
    private void cadastrarRegraVelocidade(Scanner scanner) {
        System.out.print("Digite o valor da velocidade máxima: ");
        int velocidadeMaxima = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite o nome do logradouro (ex: Avenida Paulista): ");
        String logradouro = scanner.nextLine();

        RegraVelocidade regraVelocidade = new RegraVelocidade(velocidadeMaxima, logradouro);
        baseDeDados.adicionarRegra(regraVelocidade);
        System.out.println("Regra de Velocidade cadastrada com sucesso!");
    }

    // Método para cadastrar uma nova regra de rodízio
    private void cadastrarRegraRodizio(Scanner scanner) {
        System.out.print("Digite o número da placa final (ex: 7): ");
        int finalPlaca = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite os logradouros afetados, separados por vírgula (ex: Avenida Paulista, Rua Augusta): ");
        String[] logradouros = scanner.nextLine().split(",");

        System.out.print("Digite o tipo de veículo (1 - Leve, 2 - Caminhão): ");
        int tipoVeiculo = Integer.parseInt(scanner.nextLine());

        RegraRodizio regraRodizio = new RegraRodizio(finalPlaca, logradouros, tipoVeiculo);
        baseDeDados.adicionarRegra(regraRodizio);
        System.out.println("Regra de Rodízio cadastrada com sucesso!");
    }

    // Método para cadastrar uma nova regra de corredor de ônibus
    private void cadastrarRegraCorredorOnibus(Scanner scanner) {
        System.out.print("Digite a hora inicial (ex: 7): ");
        int horaInicial = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite a hora final (ex: 10): ");
        int horaFinal = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite o nome do logradouro (ex: Avenida Brigadeiro): ");
        String logradouro = scanner.nextLine();

        RegraCorredorOnibus regraCorredorOnibus = new RegraCorredorOnibus(horaInicial, horaFinal, logradouro);
        baseDeDados.adicionarRegra(regraCorredorOnibus);
        System.out.println("Regra de Corredor de Ônibus cadastrada com sucesso!");
    }
}

