package br.com.antonio.vendas.controllers;

import br.com.antonio.vendas.models.Cliente;
import br.com.antonio.vendas.models.Fornecedor;
import br.com.antonio.vendas.repository.CidadeRepository;
import br.com.antonio.vendas.repository.ClienteRepository;
import br.com.antonio.vendas.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("/cadastroFornecedor")
    public ModelAndView cadastar(Fornecedor fornecedor) {
        ModelAndView mv = new ModelAndView("administrativo/fornecedores/cadastro");
        mv.addObject("fornecedor",  fornecedor);
        mv.addObject("listarCidades", cidadeRepository.findAll());
        return mv;
    }

    @GetMapping("/listarFornecedor")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/fornecedores/lista");
        mv.addObject("listarFornecedor", fornecedorRepository.findAll());
        return mv;
    }

    @GetMapping("/editarFornecedor/{id}")
    public ModelAndView edita(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        return cadastar(fornecedor.get());
    }

    @GetMapping("/removerFornecedor/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        fornecedorRepository.delete(fornecedor.get());
        return listar();
    }


    @PostMapping("/salvarFornecedor")
    public ModelAndView salvar(Fornecedor fornecedor, BindingResult result){
        if(result.hasErrors()) {
            return cadastar(fornecedor);
        }

        fornecedorRepository.saveAndFlush(fornecedor);
        return cadastar(new Fornecedor());
    }

}




































