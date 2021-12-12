package org.sid.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserv_id ;

    private Date reserv_dateReservation ;
    private Date reserv_dateDebut ;
    private Date reserv_dateFin ;
    private String reserv_description ;
    private int reserv_etat ;
    private double reserv_montant ;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client ;
}
