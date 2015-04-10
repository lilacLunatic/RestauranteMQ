package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import model.Cliente;
import model.Funcionario;
import model.Ingrediente;
import model.Item;
import model.ItemPreparavel;
import model.ItemPronto;
import model.Mesa;
import model.Pedido;
import model.Reserva;
import model.Unidade;
import persistencia.ClienteDAO;
import persistencia.FuncionarioDAO;
import persistencia.IngredienteDAO;
import persistencia.ItemPreparavelDAO;
import persistencia.ItemProntoDAO;
import persistencia.MesaDAO;
import persistencia.PedidoDAO;
import persistencia.ReservaDAO;

//TODO: milhares de verificacoes de entrada e tratamento de erros
//TODO: alterações na conta do cliente
//TODO: verificaçoes
//TODO: mais testes pra ver se o sistema continua rodando amanha.
public class Main {

    private final static MesaDAO MESA_DAO = new MesaDAO();
    private final static ReservaDAO RESERVA_DAO = new ReservaDAO();
    private final static PedidoDAO PEDIDO_DAO = new PedidoDAO();
    private static final ItemProntoDAO ITEM_PRONTO_DAO = new ItemProntoDAO();
    private static final ItemPreparavelDAO ITEM_PREPARAVEL_DAO = new ItemPreparavelDAO();
    private static final ClienteDAO CLIENTE_DAO = new ClienteDAO();
    private final static Scanner scanner = new Scanner(System.in);
    private final static FuncionarioDAO FUNCIONARIO_DAO = new FuncionarioDAO();
    private static final IngredienteDAO INGREDIENTE_DAO = new IngredienteDAO();

    static {
        scanner.useDelimiter(Pattern.compile("\n|\r\n"));
    }
    private final static int SAIR = 0;
    private final static int CADASTRO_CLIENTE = 1;
    private final static int LOGIN_CLIENTE = 2;
    private final static int LOGIN_FUNCIONARIO = 3;
    private final static int LOGIN_ADMINISTRADOR = 5;
    private final static int VER_CARDAPIO = 4;

    public static void main(String[] args) {

        int opcao;

        while ((opcao = menuInicial()) != SAIR) {
            switch (opcao) {
                case CADASTRO_CLIENTE:
                    cadastroCliente();
                    break;
                case LOGIN_CLIENTE:
                    loginCliente();
                    break;
                case LOGIN_FUNCIONARIO:
                    loginFuncionario(false);
                    break;
                case LOGIN_ADMINISTRADOR:
                    loginFuncionario(true);
                    break;
                case VER_CARDAPIO:
                    mostraCardapio();
                    break;
                default:
            }
        }

    }

    //TODO: ver se nao faltam funcionalidades pro cliente ou pro funcionario
    private static int menuInicial() {
        System.out.println("Escolha sua opção:");
        System.out.println(CADASTRO_CLIENTE + " - Cadastrar cliente");
        System.out.println(LOGIN_CLIENTE + " - Login como cliente");
        System.out.println(LOGIN_FUNCIONARIO + " - Login como funcionario");
        System.out.println(VER_CARDAPIO + " - Consultar cardapio");
        System.out.println(LOGIN_ADMINISTRADOR + " - Login como administrador");
        System.out.println(SAIR + " - Sair");

        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return 0;
        }
    }

    private static void cadastroCliente() {
        System.out.println("CADASTRO DE CLIENTE\n");
        Cliente cliente = new Cliente();
        System.out.println("Digite seu nome:  ");
        cliente.setNome(scanner.next());
        System.out.println("Digite seu endereço: ");
        cliente.setEndereco(scanner.next());
        System.out.println("Digite seu cpf:  ");
        cliente.setCpf(scanner.next());
        System.out.println("Digite seu telefone:  ");
        cliente.setTelefone(scanner.next());
        System.out.println("Digite um login:  ");
        cliente.setLogin(scanner.next());
        System.out.println("Digite uma senha:  ");
        cliente.setSenha(scanner.next());

        CLIENTE_DAO.save(cliente);
    }

    private static void loginFuncionario(boolean admin) {
        System.out.println("LOGIN DE FUNCIONARIO\n");
        System.out.print("Nome de usuario:");
        String username = scanner.next();
        System.out.print("Senha:");
        String senha = scanner.next();

        Funcionario funcionario = new Funcionario();
        boolean login = funcionario.login(username, senha);
        if (login) {
            funcionario = FUNCIONARIO_DAO.getByLogin(username);
            if (!admin) {
                menuFuncionario(funcionario);
            } else {
                if (funcionario.isAdministrador()) {
                    menuAdmin(funcionario);
                } else {
                    System.out.println("Voce nao possui privilegios de administrador");
                }
            }
        } else {
            System.out.println("Nome de usuario e/ou senha incorretos");
        }
    }

    private static void loginCliente() {
        System.out.println("LOGIN DE CLIENTE\n");
        System.out.print("Nome de usuario:");
        String username = scanner.next();
        System.out.print("Senha:");
        String senha = scanner.next();

        Cliente cliente = new Cliente();
        boolean login = cliente.login(username, senha);
        if (login) {
            menuCliente(cliente);
        } else {
            System.out.println("Nome de usuario e/ou senha incorretos");
        }
    }

    private static void menuCliente(Cliente cliente) {
        final int CLIENTE_PEDIDO = 1;
        final int CLIENTE_RESERVA = 2;
        final int CLIENTE_CONSULTA_RESERVA = 3;
        final int CLIENTE_CONSULTA_PEDIDO = 4;
        final int CLIENTE_CONTA = 5;
        final int CLIENTE_LOGOUT = 0;
        int opcao;

        System.out.println("Bem vindo, " + cliente.getNome() + "!\n");

        do {
            System.out.println("Escolha sua opção:");
            System.out.println(CLIENTE_PEDIDO + " - Fazer pedido");
            System.out.println(CLIENTE_RESERVA + " - Fazer reserva");
            System.out.println(CLIENTE_CONSULTA_RESERVA + " - Consultar reservas");
            System.out.println(CLIENTE_CONSULTA_PEDIDO + " - Consultar pedidos");
            System.out.println(CLIENTE_CONTA + " - Detalhes da conta");
            System.out.println(CLIENTE_LOGOUT + " - Sair");

            opcao = scanner.nextInt();
            switch (opcao) {
                case CLIENTE_PEDIDO:
                    clientePedido(cliente);
                    break;
                case CLIENTE_RESERVA:
                    clienteReserva(cliente);
                    break;
                case CLIENTE_CONSULTA_RESERVA:
                    clienteConsultaReserva(cliente);
                    break;
                case CLIENTE_CONSULTA_PEDIDO:
                    clienteConsultaPedido(cliente);
                    break;
                case CLIENTE_CONTA:
                    clienteConta(cliente);
                    break;

                default:
            }
        } while (opcao != CLIENTE_LOGOUT);
    }

    private static void menuFuncionario(Funcionario funcionario) {
        final int FUNCIONARIO_PEDIDO = 1;
        final int FUNCIONARIO_RESERVA = 2;
        final int FUNCIONARIO_CARDAPIO = 3;
        final int FUNCIONARIO_LOGOUT = 0;
        int opcao;

        System.out.println("Bem vindo, " + funcionario.getNome() + "!\n");

        do {
            System.out.println("Escolha sua opção:");
            System.out.println(FUNCIONARIO_PEDIDO + " - Fazer pedido");
            System.out.println(FUNCIONARIO_RESERVA + " - Fazer reserva");
            System.out.println(FUNCIONARIO_CARDAPIO + " - Mudar cardapio");
            System.out.println(FUNCIONARIO_LOGOUT + " - Sair");

            opcao = scanner.nextInt();
            switch (opcao) {
                case FUNCIONARIO_PEDIDO:
                    funcionarioPedido();
                    break;
                case FUNCIONARIO_RESERVA:
                    funcionarioReserva();
                    break;
                case FUNCIONARIO_CARDAPIO:
                    menuCardapio();

                default:
            }
        } while (opcao != FUNCIONARIO_LOGOUT);

    }

    private static void clientePedido(Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        System.out.println("Seu endereco (deixe em branco para usar o endereco da sua conta:)");
        String endereco = scanner.next();
        if ("".equals(endereco)) {
            pedido.setEndereco(cliente.getEndereco());
        } else {
            pedido.setEndereco(endereco);
        }
        pedido.setDataEHora(Calendar.getInstance());
        System.out.println("Observações:  ");
        String observacao = scanner.next();
        pedido.setObservações(observacao);
        PEDIDO_DAO.save(pedido);

        List<Item> itens = mostraCardapio();
        List<Integer> itensDoPedido = new ArrayList<>();

        int opcao;
        while (true) {
            System.out.println("Digite o numero de um item para adicionar a seu pedido,"
                    + " digite 0 quando terminar:");

            if ((opcao = scanner.nextInt()) == 0) {
                break;
            } else {
                itensDoPedido.add(opcao);
            }
        }

        for (int i = 0; i < itensDoPedido.size(); i++) {
            Item item = itens.get(itensDoPedido.get(i) - 1);
            PEDIDO_DAO.adicionaItem(item.getId().intValue(), PEDIDO_DAO.getLastPedido().getId().intValue());
            //PEDIDO_DAO.adicionaItem(item.getId().intValue(), pedido.getId().intValue());
            item.deduzQuantidade();
        }

    }

    private static void clienteReserva(Cliente cliente) {
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        System.out.println("Escolha a data e a hora que deseja reservar (dd/mm/aaaa HH:mm)");
        System.out.println("Escolher horário múltiplo de 30 minutos ");
        String dataString = scanner.next();
        Calendar dataEHora = Calendar.getInstance();
        try {
            dataEHora.setTime(formatData.parse(dataString));
        } catch (ParseException ex) {
            System.out.println("formato de data invalido");
            return;
        }

        System.out.println("Escolha o numero de lugares que voce deseja");
        int lugares;
        try {
            lugares = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erro");
            return;
        }
        List<Mesa> mesasDisponiveis = RESERVA_DAO.checkDisponibilidade(dataEHora, lugares);
        if (mesasDisponiveis.isEmpty()) {
            System.out.println("nao ha mesa disponivel no horario desejado "
                    + "com o numero de lugares desejado");
            return;
        }
        Mesa mesa = mesasDisponiveis.get(0);
        Reserva reserva = new Reserva();
        reserva.setMesa(mesa);
        reserva.setDataEHora(dataEHora);
        reserva.setCliente(cliente);

        try {
            RESERVA_DAO.save(reserva);
        } catch (Exception e) {
            System.out.println("Erro ao registrar reserva: " + e.getMessage());
            return;
        }
        System.out.println("Sua reserva foi feita com sucesso\n"
                + "Sua mesa eh a numero " + mesa.getNumero() + ","
                + " reservada para " + dataString);
    }

    private static List<Item> mostraCardapio() {
        List<Item> itens = new ArrayList<>();
        itens.addAll(ITEM_PREPARAVEL_DAO.listAll());
        itens.addAll(ITEM_PRONTO_DAO.listAll());
        System.out.println("CARDAPIO");
        System.out.println("Nº | CATEGORIA        |        NOME");
        System.out.println("-----------------------------------");
        for (int i = 1; i <= itens.size(); i++) {
            Item item = itens.get(i - 1);
            System.out.println(i + " | " + item.getCategoria() + " | "
                    + item.getNome());
        }

        return itens;
    }

    private static void funcionarioReserva() {
        System.out.println("Insira o nome de usuario do cliente: ");
        String username = scanner.next();
        Cliente cliente = CLIENTE_DAO.getByLogin(username);
        clienteReserva(cliente);
    }

    private static void funcionarioPedido() {
        System.out.println("Insira o nome de usuario do cliente: ");
        String username = scanner.next();
        Cliente cliente = CLIENTE_DAO.getByLogin(username);
        clientePedido(cliente);
    }

    private static void menuCardapio() {
        final int CARDAPIO_ADICIONA_ITEM = 1;
        final int CARDAPIO_REMOVE_ITEM = 2;
        final int CARDAPIO_ADICIONA_INGREDIENTE = 3;
        final int CARDAPIO_REMOVE_INGREDIENTE = 4;
        final int CARDAPIO_SAIR = 0;
        int opcao;

        System.out.println("\n");

        do {
            System.out.println("Escolha sua opção:");
            System.out.println(CARDAPIO_ADICIONA_ITEM + " - Adicionar item");
            System.out.println(CARDAPIO_REMOVE_ITEM + " - Remover item");
            System.out.println(CARDAPIO_ADICIONA_INGREDIENTE + " - Adiciona ingrediente");
            System.out.println(CARDAPIO_REMOVE_INGREDIENTE + " - Remover ingrediente");
            System.out.println(CARDAPIO_SAIR + " - Sair");

            opcao = scanner.nextInt();
            switch (opcao) {
                case CARDAPIO_ADICIONA_ITEM:
                    adicionarItem();
                    break;
                case CARDAPIO_REMOVE_ITEM:
                    removerItem();
                    break;
                case CARDAPIO_ADICIONA_INGREDIENTE:
                    adicionarIngrediente();
                    break;
                case CARDAPIO_REMOVE_INGREDIENTE:
                    removerIngrediente();
                    break;

                default:
            }
        } while (opcao != CARDAPIO_SAIR);
    }

    private static void adicionarItem() {
        final int TIPO_ITEM_PREPARAVEL = 1;
        final int TIPO_ITEM_PRONTO = 2;
        final int TIPO_ITEM_SAIR = 0;

        int tipoItem;
        System.out.println("ESCOLHA O TIPO DE ITEM QUE DESEJA ADICIONAR:");
        System.out.println(TIPO_ITEM_PREPARAVEL + " - Item preparavel");
        System.out.println(TIPO_ITEM_PRONTO + " - Item pronto");
        System.out.println(TIPO_ITEM_SAIR + " - Sair");

        tipoItem = scanner.nextInt();
        switch (tipoItem) {
            case TIPO_ITEM_PREPARAVEL:
                ItemPreparavel itemPreparavel = new ItemPreparavel();
                List<Ingrediente> ingredientes = mostraIngredientes();
                Map<Ingrediente, Integer> receita = new HashMap<>();
                int opcaoIngrediente;
                while (true) {
                    System.out.println("Digite o numero de um ingrediente "
                            + "para adicionar ao item (0 para terminar):");

                    if ((opcaoIngrediente = scanner.nextInt()) == 0) {
                        break;
                    } else {
                        Ingrediente ingrediente = ingredientes.get(opcaoIngrediente - 1);
                        int quantidade;
                        System.out.println("Digite a quantidade presente do "
                                + "ingrediente no item");
                        quantidade = scanner.nextInt();
                        receita.put(ingrediente, quantidade);
                    }
                }
                itemPreparavel.setReceita(receita);

                System.out.println("Insira um nome para o item");
                itemPreparavel.setNome(scanner.next());
                System.out.println("Insira a categoria do item");
                itemPreparavel.setCategoria(scanner.next());

                System.out.println("Insira o preco do item:");
                itemPreparavel.setPreco(scanner.nextDouble());

                ITEM_PREPARAVEL_DAO.save(itemPreparavel);
                ItemPreparavel itemComID = ITEM_PREPARAVEL_DAO.getLastItem();
                itemComID.setReceita(receita);
                ITEM_PREPARAVEL_DAO.adicionarIngredientes(itemComID);
                System.out.println("Item salvo");
                break;
            case TIPO_ITEM_PRONTO:
                ItemPronto itemPronto = new ItemPronto();
                System.out.println("Insira o nome do item: ");
                itemPronto.setNome(scanner.next());
                System.out.println("Insira o preco do item:");
                itemPronto.setPreco(scanner.nextDouble());
                System.out.println("Insira a qtd inicial do item:");
                itemPronto.setQuantidadeEstoque(scanner.nextInt());
                System.out.println("Insira a categoria");
                itemPronto.setCategoria(scanner.next());

                ITEM_PRONTO_DAO.save(itemPronto);
                System.out.println("Item salvo");
                break;

            default:
        }
    }

    private static void removerItem() {
        List<Item> todosItens = mostraCardapio();
        System.out.println("Digite o numero do item que deseja remover:");
        int numeroItem = scanner.nextInt();
        Item item = todosItens.get(numeroItem - 1);
        System.out.println("Voce tem certeza de que deseja excluir o item " + item.getNome() + "?(S/N)");
        String resposta = scanner.next();
        if ("S".equals(resposta) || "s".equals(resposta)) {
            if (item instanceof ItemPreparavel) {
                ITEM_PREPARAVEL_DAO.delete(item.getId());
                System.out.println("Item removido");
            } else if (item instanceof ItemPronto) {
                ITEM_PRONTO_DAO.delete(item.getId());
                System.out.println("Item removido");
            } else {
                System.out.println("Erro inesperado");
            }
        } else {
            System.out.println("Nenhuma remocao realizada");
        }
    }

    private static void menuAdmin(Funcionario funcionario) {
        final int FUNCIONARIO_PEDIDO = 1;
        final int FUNCIONARIO_RESERVA = 2;
        final int FUNCIONARIO_CARDAPIO = 3;
        final int ADMIN_CADASTRO = 4;
        final int REESTOQUE_INGREDIENTE = 5;
        final int REESTOQUE_ITEM = 6;
        final int ADMIN_MESA = 7;
        final int FUNCIONARIO_LOGOUT = 0;
        int opcao;

        System.out.println("Bem vindo, " + funcionario.getNome() + "!\n");

        do {
            System.out.println("Escolha sua opção:");
            System.out.println(FUNCIONARIO_PEDIDO + " - Fazer pedido");
            System.out.println(FUNCIONARIO_RESERVA + " - Fazer reserva");
            System.out.println(FUNCIONARIO_CARDAPIO + " - Mudar cardapio");
            System.out.println(ADMIN_CADASTRO + " - Cadastrar funcionario");
            System.out.println(REESTOQUE_INGREDIENTE + " - Fazer reestoque de um ingrediente");
            System.out.println(REESTOQUE_ITEM + " - Fazer reestoque de um item");
            System.out.println(ADMIN_MESA + " - Registrar mesa");
            System.out.println(FUNCIONARIO_LOGOUT + " - Sair");

            opcao = scanner.nextInt();
            switch (opcao) {
                case FUNCIONARIO_PEDIDO:
                    funcionarioPedido();
                    break;
                case FUNCIONARIO_RESERVA:
                    funcionarioReserva();
                    break;
                case FUNCIONARIO_CARDAPIO:
                    menuCardapio();
                    break;
                case ADMIN_CADASTRO:
                    menuCadastroFuncionario();
                    break;
                case REESTOQUE_INGREDIENTE:
                    reestoqueIngrediente();
                    break;
                case REESTOQUE_ITEM:
                    reestoqueItem();
                    break;
                case ADMIN_MESA: {
                    registrarMesa();
                }

                default:
            }
        } while (opcao != FUNCIONARIO_LOGOUT);
    }

    private static void menuCadastroFuncionario() {
        System.out.println("CADASTRO DE FUNCIONARIO\n");
        Funcionario funcionario = new Funcionario();
        System.out.println("Digite o nome do funcionario:  ");
        funcionario.setNome(scanner.next());
        System.out.println("Digite o endereço: ");
        funcionario.setEndereco(scanner.next());
        System.out.println("Digite o cpf:  ");
        funcionario.setCpf(scanner.next());
        System.out.println("Digite o telefone:  ");
        funcionario.setTelefone(scanner.next());
        System.out.println("Digite o salario em R$:  ");
        funcionario.setSalario(scanner.nextDouble());
        System.out.println("Digite um login:  ");
        funcionario.setLogin(scanner.next());
        System.out.println("Digite uma senha:  ");
        funcionario.setSenha(scanner.next());

        funcionario.setAdministrador(false);
        funcionario.setDataDeEntrada(Calendar.getInstance());
        FUNCIONARIO_DAO.save(funcionario);
    }

    private static void clienteConsultaReserva(Cliente cliente) {
        System.out.println("SUAS RESERVAS\n\n");
        List<Reserva> todasReservas = RESERVA_DAO.listAll();
        boolean possui = false;
        for (Reserva reserva : todasReservas) {
            if (Objects.equals(reserva.getCliente().getId(), cliente.getId())) {
                possui = true;
                Calendar dataCalendar = reserva.getDataEHora();
                String data = new SimpleDateFormat("dd/MM/yyyy").format(dataCalendar.getTime());
                String hora = new SimpleDateFormat("HH:mm").format(dataCalendar.getTime());
                int mesa = reserva.getMesa().getNumero();
                int lugares = reserva.getMesa().getQuantidadeDeLugares();

                System.out.println("Data: " + data);
                System.out.println("Hora: " + hora);
                System.out.println("Mesa: " + mesa);
                System.out.println("Lugares: " + lugares);
            }
        }

        if (!possui) {
            System.out.println("Nenhuma reserva feita");
        }
    }

    private static void clienteConsultaPedido(Cliente cliente) {
        System.out.println("SEUS PEDIDOS\n\n");
        List<Pedido> todosPedidos = PEDIDO_DAO.listAll();
        boolean possui = false;
        for (Pedido pedido : todosPedidos) {
            if (Objects.equals(pedido.getCliente().getId(), cliente.getId())) {
                possui = true;
                Calendar dataCalendar = pedido.getDataEHora();
                String data = new SimpleDateFormat("dd/MM/yyyy").format(dataCalendar.getTime());
                String hora = new SimpleDateFormat("HH:mm").format(dataCalendar.getTime());
                List<Item> itens = pedido.getItens();

                System.out.println("Data: " + data);
                System.out.println("Hora: " + hora);
                for (Item item : itens) {
                    System.out.println("Item: " + item.getNome());
                }
            }
        }

        if (!possui) {
            System.out.println("Nenhum pedido feito");
        }
    }

    private static List<Ingrediente> mostraIngredientes() {
        List<Ingrediente> ingredientes = INGREDIENTE_DAO.listAll();
        System.out.println("INGREDIENTES");
        System.out.println("Nº |    NOME    |   UNIDADE");
        System.out.println("---------------------------");
        for (int i = 1; i <= ingredientes.size(); i++) {
            Ingrediente ingrediente = ingredientes.get(i - 1);
            System.out.println(i + " | " + ingrediente.getNome() + " | "
                    + ingrediente.getUnidade());
        }

        return ingredientes;
    }

    private static List<ItemPronto> mostraItensProntos() {
        List<ItemPronto> itens = ITEM_PRONTO_DAO.listAll();
        System.out.println("ITENS PRONTOS");
        System.out.println("Nº |    NOME    |   CATEGORIA");
        System.out.println("---------------------------");
        for (int i = 1; i <= itens.size(); i++) {
            ItemPronto item = itens.get(i - 1);
            System.out.println(i + " | " + item.getNome() + " | "
                    + item.getCategoria());
        }

        return itens;
    }

    private static void reestoqueIngrediente() {
        List<Ingrediente> ingredientes = mostraIngredientes();

        System.out.println("Digite o número do ingrediente que você deseja atualizar");
        Long opcao = scanner.nextLong();

        System.out.println("Digite a quantidade a ser adicionada no estoque");
        int quantidade = scanner.nextInt();

        INGREDIENTE_DAO.reestoque(INGREDIENTE_DAO.getById(opcao), quantidade);

    }

    private static void reestoqueItem() {
        List<ItemPronto> itens = mostraItensProntos();

        System.out.println("Digite o número do item que você deseja atualizar");
        Long opcao = scanner.nextLong();

        System.out.println("Digite a quantidade a ser adicionada no estoque");
        int quantidade = scanner.nextInt();

        ITEM_PRONTO_DAO.reestoque(ITEM_PRONTO_DAO.getById(opcao), quantidade);

    }

    private static void clienteConta(Cliente cliente) {
        final int CONSULTAR_DADOS = 1;
        final int ALTERAR_NOME = 2;
        final int ALTERAR_ENDERECO = 3;
        final int ALTERAR_TELEFONE = 4;
        final int ALTERAR_CPF = 5;
        final int ALTERAR_SENHA = 6;
        final int SAIR_CONTA = 0;
        int opcao;

        do {
            System.out.println("Escolha sua opção:");
            System.out.println(CONSULTAR_DADOS + " - Consultar dados da conta");
            System.out.println(ALTERAR_NOME + " - Alterar nome");
            System.out.println(ALTERAR_ENDERECO + " - Alterar endereco");
            System.out.println(ALTERAR_TELEFONE + " - Alterar telefone");
            System.out.println(ALTERAR_CPF + " - Alterar CPF");
            System.out.println(ALTERAR_SENHA + " - Alterar Senha");
            System.out.println(SAIR_CONTA + " - Sair");

            opcao = scanner.nextInt();
            switch (opcao) {
                case CONSULTAR_DADOS:
                    clienteMostraDados(cliente);
                    break;
                case ALTERAR_NOME:
                    clienteAlteraNome(cliente);
                    break;
                case ALTERAR_ENDERECO:
                    clienteAlteraEndereco(cliente);
                    break;
                case ALTERAR_TELEFONE:
                    clienteAlteraTelefone(cliente);
                    break;
                case ALTERAR_CPF:
                    clienteAlteraCPF(cliente);
                    break;
                case ALTERAR_SENHA:
                    clienteAlteraSenha(cliente);
                    break;
                default:
            }
        } while (opcao != SAIR);
    }

    private static void clienteMostraDados(Cliente cliente) {
        System.out.println("DADOS DO CLIENTE " + cliente.getLogin() + "\n");
        System.out.println("NOME:");
        System.out.println(cliente.getNome() + "\n");
        System.out.println("ENDERECO:");
        System.out.println(cliente.getEndereco() + "\n");
        System.out.println("TELEFONE:");
        System.out.println(cliente.getTelefone() + "\n");
        System.out.println("CPF:");
        System.out.println(cliente.getCpf() + "\n");
    }

    private static void clienteAlteraNome(Cliente cliente) {
        System.out.println("Insira o novo nome:");
        String nome = scanner.next();
        cliente.setNome(nome);

        CLIENTE_DAO.updateNome(cliente);
        System.out.println("Nome alterado com sucesso\n");
    }

    private static void clienteAlteraEndereco(Cliente cliente) {
        System.out.println("Insira o novo endereco:");
        String endereco = scanner.next();
        cliente.setEndereco(endereco);

        CLIENTE_DAO.updateEndereco(cliente);
        System.out.println("Endereco alterado com sucesso\n");
    }

    private static void clienteAlteraTelefone(Cliente cliente) {
        System.out.println("Insira o novo telefone:");
        String telefone = scanner.next();
        cliente.setNome(telefone);

        CLIENTE_DAO.updateTelefone(cliente);
        System.out.println("Telefone alterado com sucesso\n");
    }

    private static void clienteAlteraCPF(Cliente cliente) {
        System.out.println("Insira o novo CPF:");
        String cpf = scanner.next();
        cliente.setCpf(cpf);
        
        CLIENTE_DAO.updateCpf(cliente);
        System.out.println("CPF alterado com sucesso\n");
    }

    private static void clienteAlteraSenha(Cliente cliente) {
        //TODO: implementar clienteAlteraSenha
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    private static void adicionarIngrediente() {
        Ingrediente ingrediente = new Ingrediente();

        System.out.println("Insira um nome para o ingrediente");
        ingrediente.setNome(scanner.next());
        String escolha;

        System.out.println("Insira a unidade do ingrediente (QUILOGRAMA, GRAMA, LITRO, MILILITRO, ADMENSIONAL, METRO ou CENTIMETRO ou 0 para sair)");
        escolha = scanner.next();
        Unidade unidade = Unidade.valueOf(escolha);
        ingrediente.setUnidade(unidade);

        INGREDIENTE_DAO.save(ingrediente);

    }

    private static void removerIngrediente() {
        List<Ingrediente> ingredientes = mostraIngredientes();

        System.out.println("Digite o numero do ingrediente para ser removido");
        int numero = scanner.nextInt();
        Ingrediente ingrediente = ingredientes.get(numero - 1);

        INGREDIENTE_DAO.delete(ingrediente.getId());

    }

    private static void registrarMesa() {
        Mesa mesa = new Mesa();
        System.out.println("Digite o numero da mesa");
        mesa.setNumero(scanner.nextInt());
        System.out.println("Digite a quantidade de lugares da mesa");
        mesa.setQuantidadeDeLugares(scanner.nextInt());

        MESA_DAO.save(mesa);
    }
}
