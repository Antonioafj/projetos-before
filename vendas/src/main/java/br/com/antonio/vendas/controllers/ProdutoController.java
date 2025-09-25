package br.com.antonio.vendas.controllers;

import br.com.antonio.vendas.models.Produto;
import br.com.antonio.vendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/cadastroProduto")
    public ModelAndView cadastar(Produto produto) {
        ModelAndView mv = new ModelAndView("administrativo/produtos/cadastro");
        mv.addObject("produto",  produto);
        return mv;
    }

    @GetMapping("/listarProduto")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
        mv.addObject("listarProdutos", repository.findAll());
        return mv;
    }

    @GetMapping("/editarProduto/{id}")
    public ModelAndView edita(@PathVariable("id") Long id) {
        Optional<Produto> produto = repository.findById(id);
        return cadastar(produto.get());
    }

    @GetMapping("/removerProduto/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Produto> produto = repository.findById(id);
        repository.delete(produto.get());
        return listar();
    }


    @PostMapping("/salvarProduto")
    public ModelAndView salvar(Produto produto, BindingResult result){
        if(result.hasErrors()) {
            return cadastar(produto);
        }

        repository.saveAndFlush(produto);
        return cadastar(new Produto());
    }

}




































