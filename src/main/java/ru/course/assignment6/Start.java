package ru.course.assignment6;


import ru.course.assignment6.dao.DistrictDAO;
import ru.course.assignment6.dao.ParentDAO;
import ru.course.assignment6.dao.SchoolDAO;
import ru.course.assignment6.entity.Child;
import ru.course.assignment6.entity.District;
import ru.course.assignment6.entity.Parent;
import ru.course.assignment6.entity.School;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Start {


    public static void main(String[] args) {
        DistrictDAO districtDAO = new DistrictDAO();
        SchoolDAO schoolDAO = new SchoolDAO();
        ParentDAO parentDAO = new ParentDAO();


        // добавление района
        District district1 = new District("1");
        District district2 = new District("2");
        districtDAO.save(district1);
        districtDAO.save(district2);

        // добавление учебного заведения
        School school1 = new School("111", district1);
        schoolDAO.save(school1);

        // добавление взрослого
        Parent parent1 = new Parent("Иванов Иван Иванович", district1);


        // добавление ребенка
        Child child1 = new Child("ребенокМ");
        Child child2 = new Child("ребенокЖ");
        Set<Child> children = new HashSet<>(Arrays.asList(child1, child2));
        parent1.setChildren(children);
        // сохранять Child независимо от Parent не требуется
        // эту работу выполнит cascade = CascadeType.ALL
        parentDAO.save(parent1);


        // вывод учебного
        Parent parent = parentDAO.findById(1);
        List<School> schools = parentDAO.findAvailableSchoolForChild(parent);
        for (School s : schools) {
            System.out.println(s);
        }


        // смена адреса проживания
        parent1.setDistrict(district2);
        parentDAO.update(parent1);


        // смена учебного заведения
        parent1.setSchool(school1);
        parentDAO.update(parent1);


    }
}
