package com.example.saude_mais.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.saude_mais.model.Appointment;
import com.example.saude_mais.repository.AppointmentsRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @PostConstruct
    public void init() {

        appointmentsRepository.saveAll(List.of(
                Appointment.builder().name("Carlos Bacon").specialties("Urologista").date(LocalDate.parse("2025-05-21"))
                        .build(),
                Appointment.builder().name("José Mourinho").specialties("Cardiologista")
                        .date(LocalDate.parse("2025-07-19")).build(),
                Appointment.builder().name("Agnaldo Gonçalves").specialties("Radiologista").date(LocalDate.parse("2026-01-06")).build()));
    }
}
