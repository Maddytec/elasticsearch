package com.maddytec.elasticsearch.service;

import com.maddytec.elasticsearch.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    public Employee create(Employee employee);

    public List<Employee> findAll();

    Employee findById(String id);
    Employee findByName(String name);

    void update(String id, Employee employee);

    void delete(String id);

}
