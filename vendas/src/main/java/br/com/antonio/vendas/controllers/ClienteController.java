package br.com.antonio.vendas.controllers;

import br.com.antonio.vendas.models.Cliente;
import br.com.antonio.vendas.repository.CidadeRepository;
import br.com.antonio.vendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("/cadastroCliente")
    public ModelAndView cadastar(Cliente cliente) {
        ModelAndView mv = new ModelAndView("administrativo/clientes/cadastro");
        mv.addObject("cliente",  cliente);
        mv.addObject("listarCidades", cidadeRepository.findAll());
        return mv;
    }

    @GetMapping("/listarCliente")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/clientes/lista");
        mv.addObject("listarCliente", clienteRepository.findAll());
        return mv;
    }

    @GetMapping("/editarCliente/{id}")
    public ModelAndView edita(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cadastar(cliente.get());
    }

    @GetMapping("/removerCliente/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        clienteRepository.delete(cliente.get());
        return listar();
    }


    @PostMapping("/salvarCliente")
    public ModelAndView salvar(Cliente cliente, BindingResult result){
        if(result.hasErrors()) {
            return cadastar(cliente);
        }

        clienteRepository.saveAndFlush(cliente);
        return cadastar(new Cliente());
    }

}




































