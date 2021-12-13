package org.sid.service;

import org.sid.model.Client;
import org.sid.model.Reservation;
import org.sid.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository ;

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll() ;
    }

    public Optional<Reservation> getReservation(Long id){
        return reservationRepository.findById(id) ;
    }

    public Reservation saveReservation(Reservation reservation){
        Reservation savedReservation = reservationRepository.save(reservation) ;
        return savedReservation ;
    }

    public void deleteReservation(Long id){
        reservationRepository.deleteById(id);
    }

    public List<Reservation> findAllReservationByClientId(Long id){
        return reservationRepository.findAllReservationByClientId(id) ;
    }
}
