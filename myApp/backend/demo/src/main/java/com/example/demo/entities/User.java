package com.example.demo.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phone_Number;

    @Column
    private String first_Name;

    @Column
    private String Last_Name;

    @Column
    private Boolean isactive;
    
    @Column
    private Boolean isapproved;


    @Column
    @Temporal(TemporalType.DATE)
    private Date created_at;

    @Column
    @Temporal(TemporalType.DATE)
    private Date modified_at;
    

    @ManyToOne
    private Role role;
    @JsonIgnore
    @OneToMany
    private List<Project> projects;
    
}
