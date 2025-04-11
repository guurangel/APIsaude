package com.example.saude_mais.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Não pode estar em branco.")
    @JsonProperty("name")
    private String name;

    // @NotNull(message = "Não pode estar em branco.")
    @FutureOrPresent
    @JsonProperty("date")
    private LocalDate date;

    @NotBlank(message = "Não pode estar em branco.")
    @JsonProperty("specialties")
    private String specialties;
}
