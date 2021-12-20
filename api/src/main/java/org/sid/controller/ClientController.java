package org.sid.controller;

import org.sid.model.Client;
import org.sid.repository.ClientRepository;
import org.sid.response.message.ResponseMessage;
import org.sid.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping(value="/m2vehicule/api/client/*")
public class ClientController {

    @Autowired
    private ClientService clientService ;

    @GetMapping(value="/allclients")
    public ResponseEntity<?> getAllClients(){
        List<Client> clients = clientService.getAllClients() ;

        if (clients.isEmpty()){
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("liste des clients vide"), HttpStatus.OK) ;
        }

        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK) ;
    }

    @GetMapping(value = "/findclient/{id}")
    public ResponseEntity<?> getClient(@PathVariable("id") Long id){
        Optional<Client> cl = clientService.getClient(id) ;
        if (cl.isPresent()){
            Client client = cl.get() ;
            return new ResponseEntity<Client>(client, HttpStatus.OK) ;
        }
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Ce client n'existe pas !"), HttpStatus.NOT_FOUND) ;
    }

    @PostMapping(value = "/addclient")
    public ResponseEntity<?> saveClient(@RequestBody Client client) {
        Client client1 = clientService.saveClient(client) ;

        if (client1 != null){
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("client enregistré avec succès"), HttpStatus.OK) ;
        }
        return new ResponseEntity<ResponseMessage>( new ResponseMessage("Echec de l'enregistrement du client"), HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/updateclient/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") Long id, @RequestBody Client client){
        Optional<Client> client1 = clientService.getClient(id) ;
        if(client1.isPresent()){
            Client clientToUpdate = client1.get() ;

            clientToUpdate.setClient_prenom(client.getClient_prenom());
            clientToUpdate.setClient_nom(client.getClient_nom());
            clientToUpdate.setClient_adresse(client.getClient_adresse());
            clientToUpdate.setClient_cin(client.getClient_adresse());
            clientToUpdate.setClient_login(client.getClient_login());
            clientToUpdate.setClient_password(client.getClient_password());

            Client clientUpdated = clientService.saveClient(clientToUpdate) ;

            return new ResponseEntity<ResponseMessage>(new ResponseMessage("client modifiée avec succès "), HttpStatus.OK) ;
        }

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Client n'existe pas"), HttpStatus.NOT_FOUND) ;
    }

    @DeleteMapping(value = "/deleteclient/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);

        return new ResponseEntity<ResponseMessage> (new ResponseMessage("client supprimé avec succès"), HttpStatus.OK) ;
    }
}
