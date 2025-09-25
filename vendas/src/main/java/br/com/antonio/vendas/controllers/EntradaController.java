package br.com.antonio.vendas.controllers;

import br.com.antonio.vendas.models.Entrada;
import br.com.antonio.vendas.models.ItemEntrada;
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
    public ModelAndView cadastar(Entrada entrada) {
        ModelAndView mv = new ModelAndView("administrativo/entradas/cadastro");
        mv.addObject("entrada",  entrada);
        mv.addObject("listarItemEntradas", this.listaItemEntrada);
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
        return cadastar(entrada.get());
    }

    @GetMapping("/removerEntrada/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Entrada> entrada = entradaRepository.findById(id);
        entradaRepository.delete(entrada.get());
        return listar();
    }


    @PostMapping("/salvarEntrada")
    public ModelAndView salvar(Entrada entrada, BindingResult result){
        if(result.hasErrors()) {
            return cadastar(entrada);
        }

        entradaRepository.saveAndFlush(entrada);
        return cadastar(new Entrada());
    }

}




































