package com.example.saude_mais.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    var specialties = List.of(
        "Cardiologista", "Dermatologista", "Ortopedista", "Neurologista", "Oftalmologista",
        "Pediatra", "Endocrinologista", "Ginecologista", "Psiquiatra", "Oncologista",
        "Cardiologista", "Reumatologista", "Cirurgião Geral", "Nutricionista",
        "Pneumologista", "Hematologista", "Urologista", "Alergologista", "Cardiologista",
        "Pneumologista", "Cirurgião Geral", "Urologista", "Cardiologista", "Urologista", "Radiologista"
    );

    var names = List.of(
        "Carlos Bacon", "José Mourinho", "Agnaldo Gonçalves", "Ana Silva", "Pedro Alves",
        "Mariana Costa", "Ricardo Oliveira", "Fernanda Lima", "Gustavo Santos", "Beatriz Rocha",
        "Lucas Pereira", "Juliana Martins", "Roberto Nunes", "Patrícia Almeida", "Daniel Souza",
        "Camila Ferreira", "Eduardo Ribeiro", "Tatiana Andrade", "Marcos Vinicius", "Vanessa Dias",
        "Felipe Castro", "Larissa Monteiro", "Antônio Carlos", "Isabela Fontes", "Rafael Menezes",
        "Helena Vasconcelos", "Rodrigo Santana", "Cristina Machado", "Otávio Correia", "Amélia Pires"
    );

    var appointments = new ArrayList<Appointment>();

    for (int i = 0; i < 30; i++) {
        LocalDate randomDate = LocalDate.now()
            .plusDays(new Random().nextInt(365 * 3)); // Até 3 anos no futuro
        
        appointments.add(Appointment.builder()
            .name(names.get(new Random().nextInt(names.size())))
            .specialties(specialties.get(new Random().nextInt(specialties.size())))
            .date(randomDate)
            .build());
    }

    appointmentsRepository.saveAll(appointments);
    }       
}
