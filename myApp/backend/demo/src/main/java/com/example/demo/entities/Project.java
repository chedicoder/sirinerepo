package com.example.demo.entities;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "projects") 
@Data
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;
	
	@Column
    private String titre;
	@Column
    private String nom_client;
	@Column
    private String description;
	@Column
    private String fichier_charge;
	@Column
    @Temporal(TemporalType.DATE)
    private Date debut_date;
	@Column
    @Temporal(TemporalType.DATE)
    private Date fin_date;
	@ManyToOne
	private User user;
}
