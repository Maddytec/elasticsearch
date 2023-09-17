package com.maddytec.elasticsearch.service;

import com.maddytec.elasticsearch.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    public Employee create(Employee employee);

    public Page<Employee> findAll(Pageable pageable);

    Employee findById(String id);

    Employee findByName(String name);

    void update(String id, Employee employee);

    void delete(String id);

}
