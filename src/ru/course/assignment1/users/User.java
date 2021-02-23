package ru.course.assignment1.users;

import ru.course.assignment1.helpers.GsmManager;
import ru.course.assignment1.transport.Vehicle;

import java.util.List;
import java.util.Map;

public interface User {

    void startShift();
    void save();
    List<String> getInputStream();
}
