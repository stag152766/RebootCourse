package ru.course.assignment6.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String schoolNumber;
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    public School(String schoolNumber, District district) {
        this.schoolNumber = schoolNumber;
        this.district = district;
    }

    public School() {

    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolNumber='" + schoolNumber + '\'' +
                ", district=" + district +
                '}';
    }
}
