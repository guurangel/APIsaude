package com.example.saude_mais.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.saude_mais.model.Appointment;
import com.example.saude_mais.repository.AppointmentsRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("appointments")
public class AppointmentsController {

    // private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AppointmentsRepository repository;

    @GetMapping
    @Cacheable("appointments")
    public List<Appointment> index() {
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "appointments", allEntries = true)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Appointment create(@RequestBody @Valid Appointment appointments) {
        System.out.println("Cadastrando agendamento de" + appointments.getName());
        return repository.save(appointments);
    }

    @GetMapping("{id}")
    public ResponseEntity<Appointment> get(@PathVariable Long id) {
        return ResponseEntity.ok(getAppointment(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Appointment> delete(@PathVariable Long id) {
        repository.delete(getAppointment(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Appointment> update(@PathVariable Long id, @RequestBody @Valid Appointment appointment) {
        getAppointment(id);
        appointment.setId(id);
        repository.save(appointment);
        return ResponseEntity.ok(appointment);
    }

    private Appointment getAppointment(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));
    }
}
