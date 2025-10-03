package br.com.antonio.eventostec.service;

import br.com.antonio.eventostec.domain.address.Address;
import br.com.antonio.eventostec.domain.event.Event;
import br.com.antonio.eventostec.domain.event.EventRequestDTO;
import br.com.antonio.eventostec.repositories.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AdressRepository adressRepository;

    public Address createAdress(EventRequestDTO data, Event event) {
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setEvent(event);

        return adressRepository.save(address);
    }

}



























