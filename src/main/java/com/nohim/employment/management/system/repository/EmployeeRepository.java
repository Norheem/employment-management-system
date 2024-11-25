package com.nohim.employment.management.system.repository;

import com.nohim.employment.management.system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmail( String email);

}
