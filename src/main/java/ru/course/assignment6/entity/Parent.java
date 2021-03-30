package ru.course.assignment6.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@DynamicUpdate
public class Parent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String fio;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parent_child",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id") )
    private Set<Child> children;
    @ManyToOne
    private District district;

    public void addChild(Child child1) {
        children.add(child1);
    }

    public void setSchool(School school1) {
        for (Child child: children){
            child.setSchool(school1);
        }
    }

    public Parent(String fio, District district) {
        this.fio = fio;
        this.district = district;
    }

    public Parent() {

    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", children=" + children +
                ", district=" + district +
                '}';
    }



}
