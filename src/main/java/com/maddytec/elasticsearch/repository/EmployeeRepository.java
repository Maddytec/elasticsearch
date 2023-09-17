package com.maddytec.elasticsearch.repository;

import com.maddytec.elasticsearch.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;


public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {
    Optional<Employee> findByName(String name);

    Page<Employee> findBySalaryBetween(double min, double max, Pageable pageable);

    @Query(value = "{\"bool\": {\"must\": [{\"match\": {\"department\": \"?0\" }}]}}")
    Page<Employee> findByDepartment(String department, Pageable pageable);

    @Query(""" 
               {
                    "bool": {
                      "must": [
                        {"match": {
                          "department": "?0"
                          }
                        },
                        {
                          "range": {
                            "salary": {
                              "gte": ?1
                            }
                          }
                        }
                      ]
                    }
                  }
            """
    )
    Page<Employee> findByDepartmentAndSalary(String department, double minSalary, Pageable pageable);

}
