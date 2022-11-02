package br.com.ecommerce.superstore.vendas.adapter;

import br.com.ecommerce.superstore.vendas.domain.dto.VendaDTO;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import br.com.ecommerce.superstore.vendas.domain.interfaces.VendaDAO;
import br.com.ecommerce.superstore.vendas.infraestrutura.exception.NotFoundException;
import br.com.ecommerce.superstore.vendas.infraestrutura.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VendaDAOImpl implements VendaDAO {

    VendaRepository vendaRepository;

    public VendaDAOImpl(@Autowired VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Override
    public Boolean pedidoToVenda(Venda vendaAberta) {
        vendaAberta.setFechado(true);
        this.vendaRepository.save(vendaAberta);
        return true;
    }

    @Override
    public Boolean createVenda(Venda venda) {
        this.vendaRepository.save(venda);
        return true;
    }

    @Override
    public Optional<VendaDTO> getVendaByUserId(String userId) {
        Optional<Venda> vendaByUserId = Optional.ofNullable(this.vendaRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Venda não existe")));
        return Optional.of(VendaDTO.vendaTOVendaDTO(vendaByUserId.get()));
    }

    @Override
    public VendaDTO getVendaById(String id) {
        Venda venda_não_existe = this.vendaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venda não existe"));
        return VendaDTO.vendaTOVendaDTO(venda_não_existe);
    }
}
