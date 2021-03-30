package ru.course.assignment6.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@DynamicUpdate
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String fio;
    private int age;
    @ManyToMany
    @JoinTable(name = "parent_child",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id") )
    private Set<Parent> parents;
    @ManyToOne
    private School school;


    public Child(String fio) {
        this.fio = fio;
    }

    public Child() {

    }
}
