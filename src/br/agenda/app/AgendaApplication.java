/*"""* Equipe:
- Daniel Cavalcanti Noronha
- Alberto Silva
- Bernardo Cardoso
- Cauã Lucas
- Davi Spindola"""; */

package br.agenda.app;

import br.agenda.model.Contato;
import br.agenda.exception.*;
import br.agenda.service.AgendaManager;

import java.util.List;
import java.util.Scanner;

public class AgendaApplication {

    public static void main(String[] args) {
        AgendaManager agenda = new AgendaManager();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 7) {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Buscar Contato");
            System.out.println("3. Remover Contato");
            System.out.println("4. Listar Todos os Contatos");
            System.out.println("5. Salvar em CSV");
            System.out.println("6. Carregar de CSV");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    try {
                        agenda.adicionarContato(new Contato(nome, telefone, email));
                        System.out.println("Contato adicionado!");
                    } catch (ContatoExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Nome do contato: ");
                    String buscar = scanner.nextLine();

                    try {
                        System.out.println(agenda.buscarContato(buscar));
                    } catch (ContatoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Nome para remover: ");
                    String remover = scanner.nextLine();

                    try {
                        agenda.removerContato(remover);
                        System.out.println("Contato removido!");
                    } catch (ContatoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    List<Contato> lista = agenda.listarTodosContatos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum contato cadastrado.");
                    } else {
                        lista.forEach(System.out::println);
                    }
                    break;

                case 5:
                    System.out.print("Arquivo para salvar: ");
                    String arqSalvar = scanner.nextLine();
                    agenda.salvarContatosCSV(arqSalvar);
                    System.out.println("Salvo!");
                    break;

                case 6:
                    System.out.print("Arquivo para carregar: ");
                    String arqCarregar = scanner.nextLine();
                    agenda.carregarContatosCSV(arqCarregar);
                    System.out.println("Carregado!");
                    break;

                case 7:
                    System.out.println("Finalizando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
