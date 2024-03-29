package produtos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import produtos.database.repository.ProdutoRepository;
import produtos.domain.model.Produto;
import produtos.exceptions.BadRequestException;
import produtos.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> listProdutos() {
        List<Produto> produtoList = new ArrayList<>();
        produtoRepository.findAll().forEach(produtoList::add);
        return produtoList;
    }

    public Produto getProdutoById(String id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        return produtoOptional.orElseThrow(() -> new BadRequestException("Produto invalido!"));
    }

    public Produto findProdutoByName(String nome) {
        Optional<Produto> produtoOptional = produtoRepository.findByNome(nome);
        return produtoOptional.orElseThrow(() -> new BadRequestException("Produto invalido!"));
    }

    public Produto updateProduto(Produto produto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(produto.getId());
        Produto produto1 = produtoOptional.orElseThrow(() -> new BadRequestException("Produto invalido!"));
        Produto produtoUpdate = updateProduto(produto1, produto);
        produtoRepository.save(produtoUpdate);
        return produtoUpdate;
    }

    public Produto createProduto(Produto produto) {
        Optional<Produto> produtoOptional = produtoRepository.findByNome(produto.getNome());
        if (produtoOptional.isEmpty()) {
            return produtoRepository.save(produto);
        }
        throw new BadRequestException("Este email já está cadastrado, faça login ou crie um novo email");
    }

    public Produto updateProduto(Produto produtoUpdate, Produto produto) {
        produtoUpdate.setNome(produto.getNome());
        produtoUpdate.setDescricao(produto.getDescricao());
        produtoUpdate.setQuantidade(produto.getQuantidade());
        produtoUpdate.setValor(produto.getValor());
        produtoUpdate.setImagemProduto(produto.getImagemProduto());
        return produtoUpdate;
    }

    public String deleteProduto(String id) {
        var produto = getProdutoById(id);
        produtoRepository.delete(produto);
        return id;
    }

    public void sellProduct(String id, Integer quantidadeParaVenda){
        Produto produto = produtoRepository.findById(id).orElseThrow(()->new NotFoundException("Produto não encontrado"));
        if(produto.getQuantidade().equals(0))
            throw new BadRequestException("Não existe produto disponivel para venda");
        if(produto.getQuantidade()-quantidadeParaVenda==0)
            throw new BadRequestException("Quantidade de pedido superior a quantidade em estoque");
        produto.setQuantidade(produto.getQuantidade()-quantidadeParaVenda);
        this.produtoRepository.save(produto);

    }

    public void buyProduct(String id, Integer quantidadeParaCompra) {
        Produto produto = this.getProdutoById(id);
        produto.setQuantidade(produto.getQuantidade() + quantidadeParaCompra);
        this.produtoRepository.save(produto);
    }



}
