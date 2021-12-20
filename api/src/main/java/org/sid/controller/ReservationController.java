package org.sid.controller;

import org.sid.model.Client;
import org.sid.model.Reservation;
import org.sid.repository.ReservationRepository;
import org.sid.response.message.ResponseMessage;
import org.sid.service.ClientService;
import org.sid.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/m2vehicule/api/reservation/*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/allreservations")
    public ResponseEntity<?> getAllReservation() {
        List<Reservation> reservations = reservationService.getAllReservations();

        if (reservations.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("liste des réservations vide"), HttpStatus.OK);
        }

        return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
    }

    @PostMapping(value = "/addreservation/{idclient}")
    public ResponseEntity<?> saveReservation(@PathVariable("idclient") Long idClient, @RequestBody Reservation reservation) {

        Optional<Client> cl = clientService.getClient(idClient);
        if (cl.isPresent()) {
            Client client = cl.get();
            reservation.setClient(client);
            Reservation reservation1 = reservationService.saveReservation(reservation);
            if (reservation1 != null) {
                return new ResponseEntity<ResponseMessage>(new ResponseMessage("reservation validée avec succès"), HttpStatus.OK);
            }
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("Echec de validation de la reservation"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Ce client n'existe pas !"), HttpStatus.OK);
    }

    @PutMapping(value = "/updatereservation/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservation) {
        Optional<Reservation> reservation1 = reservationService.getReservation(id);
        if (reservation1.isPresent()) {
            Reservation reservationToUpdate = reservation1.get();

            reservationToUpdate.setReserv_dateDebut(reservation.getReserv_dateDebut());
            reservationToUpdate.setReserv_dateFin(reservation.getReserv_dateFin());
            reservationToUpdate.setReserv_dateReservation(reservation.getReserv_dateReservation());
            reservationToUpdate.setReserv_description(reservation.getReserv_description());
            reservationToUpdate.setReserv_montant(reservation.getReserv_montant());
            reservationToUpdate.setReserv_etat(reservation.getReserv_etat());

            Reservation reservationUpdated = reservationService.saveReservation(reservationToUpdate);
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("reservation modifiée avec succès "), HttpStatus.OK);
        }

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Client n'existe pas"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/deletereservation/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable("id") Long id) {

        reservationService.deleteReservation(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("reservation supprimée avec succès"), HttpStatus.OK);
    }

    @GetMapping(value = "/allreservationbyclient/{idclient}")
    public ResponseEntity<?> findAllReservationByClientId(@PathVariable("idclient") Long idClient){
        List<Reservation> reservationList = reservationService.findAllReservationByClientId(idClient) ;
        if (reservationList.isEmpty()){
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("Ce client n'a pas fait de reservation"), HttpStatus.OK);
        }
        return new ResponseEntity<List<Reservation>>(reservationList, HttpStatus.OK);
    }
}
