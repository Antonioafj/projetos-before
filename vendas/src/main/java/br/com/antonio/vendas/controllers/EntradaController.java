package br.com.antonio.vendas.controllers;

import br.com.antonio.vendas.models.Entrada;
import br.com.antonio.vendas.models.ItemEntrada;
import br.com.antonio.vendas.models.Produto;
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
public class EntradaController {

    @Autowired
    private EntradaRepository entradaRepository;
    @Autowired
    private ItemEntradaRepository itemEntradaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    private List<ItemEntrada> listaItemEntrada = new ArrayList<ItemEntrada>();


    @GetMapping("/cadastroEntrada")
    public ModelAndView cadastar(Entrada entrada, ItemEntrada itemEntrada) {
        ModelAndView mv = new ModelAndView("administrativo/entradas/cadastro");
        mv.addObject("entrada", entrada);
        mv.addObject("itemEntrada", itemEntrada);
        mv.addObject("listarItemEntradas", this.listaItemEntrada);
        mv.addObject("listarFuncionarios", funcionarioRepository.findAll());
        mv.addObject("listarFornecedores", fornecedorRepository.findAll());
        mv.addObject("listarProdutos", produtoRepository.findAll());
        return mv;
    }

    @GetMapping("/listarEntrada")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/entradas/lista");
        mv.addObject("listarEntrada", entradaRepository.findAll());
        return mv;
    }

    @GetMapping("/editarEntrada/{id}")
    public ModelAndView edita(@PathVariable("id") Long id) {
        Optional<Entrada> entrada = entradaRepository.findById(id);
        this.listaItemEntrada = itemEntradaRepository.buscarPorEntrada(entrada.get().getId());

        return cadastar(entrada.get(), new ItemEntrada());
      }

//    @GetMapping("/removerEntrada/{id}")
//    public ModelAndView remover(@PathVariable("id") Long id) {
//        Optional<Entrada> entrada = entradaRepository.findById(id);
//        entradaRepository.delete(entrada.get());
//        return listar();
//    }


    @PostMapping("/salvarEntrada")
    public ModelAndView salvar(String acao, Entrada entrada, ItemEntrada itemEntrada, BindingResult result) {
        if (result.hasErrors()) {
            return cadastar(entrada, itemEntrada);
        }

        if (acao.equals("itens")) {
            this.listaItemEntrada.add(itemEntrada);
            entrada.setValorTotal(entrada.getValorTotal() + (itemEntrada.getValor() * itemEntrada.getQuantidade()));
            entrada.setQuantidadeTotal(entrada.getQuantidadeTotal() + itemEntrada.getQuantidade());

        } else if (acao.equals("salvar")) {
            entradaRepository.saveAndFlush(entrada);

            for (ItemEntrada item : listaItemEntrada) {
                item.setEntrada(entrada);
                itemEntradaRepository.saveAndFlush(item);

                Optional<Produto> prod = produtoRepository.findById(item.getProduto().getId());
                Produto produto = prod.get();
                produto.setEstoque(produto.getEstoque() + item.getQuantidade());
                produto.setPrecoVenda(item.getValor());
                produto.setPrecoCusto(item.getValorCusto());
                produtoRepository.saveAndFlush(produto);

                this.listaItemEntrada = new ArrayList<>();
            }
            return cadastar(new Entrada(), new ItemEntrada());
        }
        return cadastar(entrada, new ItemEntrada());
    }
}





































