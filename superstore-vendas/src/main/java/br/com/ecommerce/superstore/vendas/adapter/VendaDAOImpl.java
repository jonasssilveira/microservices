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
        VendaDTO vendaFechada = VendaDTO.vendaTOVendaDTO(vendaAberta);
        this.vendaRepository.save(vendaFechada);
        return true;
    }

    @Override
    public Boolean createVenda(Venda venda) {
        VendaDTO criarVenda = VendaDTO.vendaTOVendaDTO(venda);
        this.vendaRepository.save(criarVenda);
        return true;
    }

    @Override
    public Optional<Venda> getVendaByUserId(String userId) {
        Optional<VendaDTO> vendaByUserId = Optional.ofNullable(this.vendaRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Venda não existe")));
        return Optional.of(VendaDTO.vendaDTOTOVenda(vendaByUserId.get()));
    }

    @Override
    public Venda getVendaById(String id) {
        VendaDTO vendaPeloId = this.vendaRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Venda não existe"));
        return VendaDTO.vendaDTOTOVenda(vendaPeloId);
    }
}
