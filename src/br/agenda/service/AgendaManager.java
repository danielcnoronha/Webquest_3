/*"""* Equipe:
- Daniel Cavalcanti Noronha
- Alberto Silva
- Bernardo Cardoso
- Cauã Lucas
- Davi Spindola"""; */


package br.agenda.service;

import br.agenda.model.Contato;
import br.agenda.exception.*;

import java.io.*;
import java.util.*;

public class AgendaManager implements GerenciadorContatos {

    private List<Contato> contatos;

    public AgendaManager() {
        contatos = new ArrayList<>();
    }

    @Override
    public void adicionarContato(Contato contato) throws ContatoExistenteException {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(contato.getNome())) {
                throw new ContatoExistenteException("Contato com esse nome já existe.");
            }
        }
        contatos.add(contato);
    }

    @Override
    public Contato buscarContato(String nome) throws ContatoNaoEncontradoException {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        throw new ContatoNaoEncontradoException("Contato não encontrado.");
    }

    @Override
    public void removerContato(String nome) throws ContatoNaoEncontradoException {
        Contato c = buscarContato(nome);
        contatos.remove(c);
    }

    @Override
    public List<Contato> listarTodosContatos() {
        return contatos;
    }

    public void salvarContatosCSV(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Contato c : contatos) {
                writer.write(c.getNome() + ";" + c.getTelefone() + ";" + c.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public void carregarContatosCSV(String nomeArquivo) {
        contatos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    contatos.add(new Contato(dados[0], dados[1], dados[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
    }

    public List<Contato> listarContatosOrdenados() {
        List<Contato> ordenados = new ArrayList<>(contatos);
        ordenados.sort(Comparator.comparing(Contato::getNome, String.CASE_INSENSITIVE_ORDER));
        return ordenados;
    }

    public List<Contato> buscarPorDominioEmail(String dominio) {
        List<Contato> filtrados = new ArrayList<>();
        for (Contato c : contatos) {
            if (c.getEmail().toLowerCase().endsWith(dominio.toLowerCase())) {
                filtrados.add(c);
            }
        }
        return filtrados;
    }
}
