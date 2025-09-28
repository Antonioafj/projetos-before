package br.com.antonio.vendas.controllers;

import br.com.antonio.vendas.models.ItemVenda;
import br.com.antonio.vendas.models.Produto;
import br.com.antonio.vendas.models.Venda;
import br.com.antonio.vendas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ItemVendaRepository itemVendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    private List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();


    @GetMapping("/cadastroVenda")
    public ModelAndView cadastar(Venda venda, ItemVenda itemVenda) {
        ModelAndView mv = new ModelAndView("administrativo/vendas/cadastro");
        mv.addObject("venda", venda);
        mv.addObject("itemVenda", itemVenda);
        mv.addObject("listarItemVendas", this.listaItemVenda);
        mv.addObject("listarFuncionarios", funcionarioRepository.findAll());
        mv.addObject("listarClientes", clienteRepository.findAll());
        mv.addObject("listarProdutos", produtoRepository.findAll());
        return mv;
    }

    @GetMapping("/listarVenda")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/vendas/lista");
        mv.addObject("listarVenda", vendaRepository.findAll());
        return mv;
    }

    @GetMapping("/editarVenda/{id}")
    public ModelAndView edita(@PathVariable("id") Long id) {
        Optional<Venda> venda = vendaRepository.findById(id);
        this.listaItemVenda = itemVendaRepository.buscarPorVenda(venda.get().getId());

        return cadastar(venda.get(), new ItemVenda());
      }

//    @GetMapping("/removerVenda/{id}")
//    public ModelAndView remover(@PathVariable("id") Long id) {
//        Optional<Venda> venda = vendaRepository.findById(id);
//        vendaRepository.delete(venda.get());
//        return listar();
//    }


    @PostMapping("/salvarVenda")
    public ModelAndView salvar(String acao, Venda venda, ItemVenda itemVenda, BindingResult result) {
        if (result.hasErrors()) {
            return cadastar(venda, itemVenda);
        }

        if (acao.equals("itens")) {
            this.listaItemVenda.add(itemVenda);
            itemVenda.setValor(itemVenda.getProduto().getPrecoVenda());
            itemVenda.setSubtotal(itemVenda.getProduto().getPrecoVenda() * itemVenda.getQuantidade());
            venda.setValorTotal(venda.getValorTotal() + (itemVenda.getValor() * itemVenda.getQuantidade()));
            venda.setQuantidadeTotal(venda.getQuantidadeTotal() + itemVenda.getQuantidade());

        } else if (acao.equals("salvar")) {
            vendaRepository.saveAndFlush(venda);

            for (ItemVenda item : listaItemVenda) {
                item.setVenda(venda);

//                item.setSubtotal(item.getValor()* item.getQuantidade());
                itemVendaRepository.saveAndFlush(item);

                Optional<Produto> prod = produtoRepository.findById(item.getProduto().getId());
                Produto produto = prod.get();
                produto.setEstoque(produto.getEstoque() - item.getQuantidade());
                produto.setPrecoVenda(item.getValor());
//                produto.setPrecoCusto(item.getSubtotal());
                produtoRepository.saveAndFlush(produto);

                this.listaItemVenda = new ArrayList<>();
            }
            return cadastar(new Venda(), new ItemVenda());
        }
        return cadastar(venda, new ItemVenda());
    }
}





































