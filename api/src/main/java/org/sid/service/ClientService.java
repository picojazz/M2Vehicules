package org.sid.service;

import org.sid.model.Client;
import org.sid.model.Reservation;
import org.sid.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository ;

    public List<Client> getAllClients(){
        return clientRepository.findAll() ;
    }

    public Optional<Client> getClient(Long id){
        return clientRepository.findById(id) ;
    }

    public Client saveClient(Client client){
        Client savedClient = clientRepository.save(client);
        return savedClient ;
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }
}
