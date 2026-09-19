import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

List<Veiculo> veiculos = new ArrayList<>();

void main() {

    String menu = """
            ===== Cadastro de Veículos =====
            1 - Cadastrar Veículo
            2 - Listar Veículos
            3 - Consultar Veículo
            0 - Sair
            ================================
            """;

    int opcao;

    do {
        IO.println(menu);
        opcao = Input.readInt("Escolha uma opção: ");

        switch (opcao) {
            case 1 -> cadastrarVeiculo();
            case 2 -> listarVeiculos();
            case 3 -> consultarVeiculo();
            case 0 -> IO.println("Até logo!");
            default -> IO.println("Opção inválida!");
        }

    } while (opcao != 0);
}


void cadastrarVeiculo() {

    String marca = IO.readln("Digite a marca do veículo: ");
    String modelo = IO.readln("Digite o modelo do veículo: ");
    String placa = IO.readln("Digite a placa do veículo: ");
    int ano = Input.readInt("Digite o ano do veículo: ");

    marca = marca.trim();
    modelo = modelo.trim();
    placa = padronizarPlaca(placa);

    if (marca.isEmpty() || modelo.isEmpty() || placa.isEmpty()) {
        IO.println("Todos os campos devem ser preenchidos!");
        return;
    }

    if (placaExiste(placa)) {
        IO.println("Essa placa já está cadastrada!");
        return;
    }

    int anoAtual = LocalDate.now().getYear();

    if (ano < 1900 || ano > anoAtual + 1) {
        IO.println("Ano do veículo inválido!");
        IO.println("O ano deve estar entre 1900 e " + (anoAtual + 1) + ".");
        return;
    }

    Veiculo novoVeiculo = new Veiculo(marca, modelo, ano, placa);
    veiculos.add(novoVeiculo);

    IO.println("Veículo cadastrado com sucesso!");
}


void listarVeiculos() {

    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado.");
        return;
    }

    IO.println("===== Veículos Cadastrados =====");

    for (int i = 0; i < veiculos.size(); i++) {
        IO.println((i + 1) + " - " + veiculos.get(i));
    }
}


void consultarVeiculo() {

    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado.");
        return;
    }

    String placaProcurada = IO.readln("Digite a placa do veículo: ");
    placaProcurada = padronizarPlaca(placaProcurada);

    for (Veiculo veiculo : veiculos) {

        if (veiculo.getPlaca().equals(placaProcurada)) {
            IO.println("Veículo encontrado:");
            IO.println(veiculo);
            return;
        }
    }

    IO.println("Nenhum veículo encontrado com essa placa.");
}


String padronizarPlaca(String placa) {
    return placa.trim().toUpperCase();
}


boolean placaExiste(String placa) {

    for (Veiculo veiculo : veiculos) {

        if (veiculo.getPlaca().equals(placa)) {
            return true;
        }
    }

    return false;
}