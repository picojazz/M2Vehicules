package org.sid.repository;

import org.sid.model.Client;
import org.sid.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("from Reservation reserv where reserv.client.client_id=:id")
    List<Reservation> findAllReservationByClientId(Long id) ;

//    List<Reservation> findReservationByClient(Client client) ;
}
