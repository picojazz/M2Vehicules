package org.sid.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id ;

    private String client_prenom ;
    private String client_nom ;
    private String client_adresse ;
    private String client_cin ;
    private String client_login ;
    private String client_password ;
/*
    @OneToMany( cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                targetEntity = Reservation.class,
                mappedBy = "client")
    private List<Reservation> reservations = new ArrayList<>() ;

 */

}
