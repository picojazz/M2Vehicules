package org.sid.controller;

import org.sid.model.Client;
import org.sid.repository.ClientRepository;
import org.sid.response.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping(value = "/m2vehicule/api/client/*")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository ;

    @GetMapping(value = "/allclients")
    public ResponseEntity<?> getAllClients(){
        List<Client> clients = clientRepository.findAll() ;
        if (clients.isEmpty()){
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("liste des clients vide"), HttpStatus.OK) ;
        }

        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK) ;
    }

    @PostMapping(value = "/addclient")
    public ResponseEntity<?> saveClient(@RequestBody Client client) {
        Client client1 = clientRepository.save(client) ;

        if (client1 != null){
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("client enregistré avec succès"), HttpStatus.OK) ;
        }
        return new ResponseEntity<ResponseMessage>( new ResponseMessage("Echec de l'enregistrement du client"), HttpStatus.OK);
    }

    @PutMapping(value = "/updateclient/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") Long id, @RequestBody Client client){
        Optional<Client> client1 = clientRepository.findById(id) ;
        if(client1.isPresent()){
            Client clientToUpdate = client1.get() ;

            clientToUpdate.setPrenom(client.getPrenom());
            clientToUpdate.setNom(client.getNom());
            clientToUpdate.setAdresse(client.getAdresse());
            clientToUpdate.setCin(client.getAdresse());
            clientToUpdate.setLogin(client.getLogin());
            clientToUpdate.setPassword(client.getPassword());

            Client clientUpdated = clientRepository.save(clientToUpdate) ;
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("client modifiée avec succès"+ clientUpdated), HttpStatus.OK) ;
        }

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Client n'existe pas"), HttpStatus.NOT_FOUND) ;
    }

    @DeleteMapping(value = "/deleteclient/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        clientRepository.deleteById(id);

        return new ResponseEntity<ResponseMessage> (new ResponseMessage("client supprimé avec succès"), HttpStatus.OK) ;
    }
}
