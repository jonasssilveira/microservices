package br.com.ecommerce.superstore.vendas.infraestrutura.services;

import br.com.ecommerce.superstore.vendas.adapter.VendaDAOImpl;
import br.com.ecommerce.superstore.vendas.domain.dto.VendaDTO;
import br.com.ecommerce.superstore.vendas.domain.entities.Venda;
import br.com.ecommerce.superstore.vendas.domain.interfaces.UsuarioClient;
import br.com.ecommerce.superstore.vendas.domain.usecase.VendaTransaction;
import br.com.ecommerce.superstore.vendas.infraestrutura.exception.NotFoundException;
import br.com.ecommerce.superstore.vendas.infraestrutura.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    VendaTransaction vendaTransaction;
    VendaRepository vendaRepository;
    UsuarioClient usuarioClient;
    VendaService(@Autowired VendaDAOImpl vendaDAO,
                 @Autowired VendaRepository vendaRepository){
        vendaTransaction = new VendaTransaction(vendaDAO,
                null);
        this.vendaRepository = vendaRepository;
    }

    Page<VendaDTO> getAll(Pageable pageable){
        return this.vendaRepository.findAll(pageable);
    }

    VendaDTO getById(String id){
        Venda venda = this.vendaTransaction.getVendaById(id).orElseThrow(() ->
                new NotFoundException("Não foi encontrado vendas com este identificador"));
        return VendaDTO.vendaTOVendaDTO(venda);
    }

}
