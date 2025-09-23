package br.com.antonio.vendas.controllers;

import br.com.antonio.vendas.models.Cidade;
import br.com.antonio.vendas.repository.CidadeRepository;
import br.com.antonio.vendas.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/cadastroCidade")
    public ModelAndView cadastar(Cidade cidade) {
        ModelAndView mv = new ModelAndView("administrativo/cidades/cadastro");
        mv.addObject("cidade",  cidade);
        mv.addObject("listarEstados", estadoRepository.findAll());
        return mv;
    }

    @GetMapping("/listarCidade")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/cidades/lista");
        mv.addObject("listarCidade", cidadeRepository.findAll());
        return mv;
    }

    @GetMapping("/editarCidade/{id}")
    public ModelAndView edita(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        return cadastar(cidade.get());
    }

    @GetMapping("/removerCidade/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        cidadeRepository.delete(cidade.get());
        return listar();
    }


    @PostMapping("/salvarCidade")
    public ModelAndView salvar(Cidade cidade, BindingResult result){
        if(result.hasErrors()) {
            return cadastar(cidade);
        }

        cidadeRepository.saveAndFlush(cidade);
        return cadastar(new Cidade());
    }

}




































