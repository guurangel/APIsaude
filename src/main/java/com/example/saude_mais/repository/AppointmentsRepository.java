package com.example.saude_mais.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.saude_mais.model.Appointment;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {

    //SELECT * FROM APPOINTMENT WHERE SPECIALTIES = :SPECIALTIES
    //@Query("SELECT a FROM APPOINTMENT as a WHERE a.specialties = :specialties") //JPQL
    //Page<Appointment> findBySpecialtiesContainingIgnoringCase(String specialties, Pageable pageable); // Query Methods



    //Page<Appointment> findByDate(LocalDate date, Pageable pageable);



    Page<Appointment> findBySpecialtiesContainingIgnoringCaseAndDate(String specialties, LocalDate date, Pageable pageable);
}
