package br.com.ecommerce.superstore.vendas.adapter;

import br.com.ecommerce.superstore.vendas.domain.dto.VendaDTO;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import br.com.ecommerce.superstore.vendas.domain.interfaces.VendaDAO;
import br.com.ecommerce.superstore.vendas.infraestrutura.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Component
public class VendaDAOImpl implements VendaDAO {

    EntityManager entityManager;

    public VendaDAOImpl(@Autowired EntityManager entityManager) {
         this.entityManager = entityManager;
    }

    @Override
    public Boolean pedidoToVenda(Venda vendaAberta) {
        vendaAberta.setFechado(true);
        this.entityManager.merge(vendaAberta);
        return true;
    }

    @Override
    public Boolean createVenda(Venda venda) {
        this.entityManager.merge(venda);
        return true;
    }

    @Override
    public Optional<VendaDTO> getVendaByUserId(String userId) {

        TypedQuery<Venda> query = entityManager.createQuery("SELECT venda " +
                "FROM venda venda " +
                "WHERE venda.userId = :userId ", Venda.class);

        query.setParameter("userId", userId);
        Optional<Venda> vendaByUserId = Optional.ofNullable(Optional.ofNullable(query.getSingleResult()).orElseThrow(
                () -> new NotFoundException("Venda não existe")));
        return Optional.of(VendaDTO.vendaTOVendaDTO(vendaByUserId.get()));
    }

    @Override
    public VendaDTO getVendaById(String id) {
        TypedQuery<Venda> query = entityManager.createQuery("SELECT venda " +
                "FROM venda venda " +
                "WHERE venda.id = :id ", Venda.class);

        query.setParameter("id", id);
        Optional<Venda> vendaByUserId = Optional.ofNullable(Optional.ofNullable(query.getSingleResult()).orElseThrow(
                () -> new NotFoundException("Venda não existe")));

        return VendaDTO.vendaTOVendaDTO(vendaByUserId.get());
    }
}
