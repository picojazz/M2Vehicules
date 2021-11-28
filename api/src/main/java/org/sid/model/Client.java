package org.sid.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String prenom ;
    private String nom ;
    private String adresse ;
    private String cin ;
    private String login ;
    private String password ;
}
