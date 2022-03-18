package br.com.ecommerce.superstore.vendas.domain.usecase;

import br.com.ecommerce.superstore.vendas.domain.dto.UsuarioDTO;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import br.com.ecommerce.superstore.vendas.domain.interfaces.UsuarioClient;
import br.com.ecommerce.superstore.vendas.domain.interfaces.ProdutoClient;
import br.com.ecommerce.superstore.vendas.domain.interfaces.VendaDAO;

import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;
import java.util.Optional;

public record VendaTransaction(VendaDAO vendaDAO,
                               UsuarioClient usuarioClient) {

    public Boolean sell(String id) throws NoSuchObjectException {
        Optional<Venda> vendaByUserId = getVendaByUserId(id);
        if (vendaByUserId == null)
            return true;

        vendaByUserId.get().sell();

        vendaDAO.createVenda(vendaByUserId.get());
        
        return true;
    }

    public Optional<Venda> getVendaByUserId(String id) throws NoSuchObjectException {
        UsuarioDTO usuarioPorId = usuarioClient.getUsuario(id).orElseThrow(
                () -> new NoSuchObjectException("Erro ao encontrar usuario")
        );
        Optional<Venda> vendaByUserId = vendaDAO.getVendaByUserId(usuarioPorId.id());

        if(vendaByUserId.isEmpty()
        || vendaByUserId.get().getFechado())
            return null;

        return vendaByUserId;
    }

    public Optional<Venda> getVendaById(String id){
        Optional<Venda> vendaById = Optional.of(this.vendaDAO.getVendaById(id));
        if(vendaById.isEmpty())
            throw new NoSuchElementException("Venda não encontrada!");
        return vendaById;

    }
}
