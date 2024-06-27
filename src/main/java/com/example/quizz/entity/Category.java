package com.example.quizz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    private String name;

    @OneToMany(mappedBy ="category",fetch = FetchType.EAGER)
    private List<Topic> topics;
}
