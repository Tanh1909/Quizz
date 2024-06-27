package com.example.quizz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Role {
    @Id
    private String name;

    public Role(ERole eRole) {
        this.name = eRole.name();
    }
}
