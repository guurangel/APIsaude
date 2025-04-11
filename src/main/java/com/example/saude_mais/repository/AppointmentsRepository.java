package com.example.saude_mais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.saude_mais.model.Appointment;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {

}
