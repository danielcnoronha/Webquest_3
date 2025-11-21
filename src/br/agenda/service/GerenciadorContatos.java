/*"""* Equipe:
- Daniel Cavalcanti Noronha
- Alberto Silva
- Bernardo Cardoso
- Cauã Lucas
- Davi Spindola"""; */


package br.agenda.service;

import br.agenda.model.Contato;
import br.agenda.exception.*;
import java.util.List;

public interface GerenciadorContatos {
    void adicionarContato(Contato contato) throws ContatoExistenteException;
    Contato buscarContato(String nome) throws ContatoNaoEncontradoException;
    void removerContato(String nome) throws ContatoNaoEncontradoException;
    List<Contato> listarTodosContatos();
}
