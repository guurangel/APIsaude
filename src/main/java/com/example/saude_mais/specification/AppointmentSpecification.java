package com.example.saude_mais.specification;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import com.example.saude_mais.controllers.AppointmentsController.AppointmentsFilters;
import com.example.saude_mais.model.Appointment;
import jakarta.persistence.criteria.Predicate;

public class AppointmentSpecification {

    public static Specification<Appointment> withFilters(AppointmentsFilters filters){
        return (root, query, cb) -> {  //lambda expresion 
            var predicates = new ArrayList<>();

            if (filters.specialties() != null){
                predicates.add(
                    cb.like(
                        cb.lower(root.get("specialties")), "%" + filters.specialties().toLowerCase() + "%"
                    )
                );
            }

            if(filters.startDate() != null && filters.endDate() != null){
                predicates.add(
                    cb.between(root.get("date"), filters.startDate(), filters.endDate())
                );
            }

            if(filters.startDate() != null && filters.endDate() == null){
                predicates.add(
                    cb.equal(root.get("date"), filters.startDate())
                );
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);

        };
    }
    
}