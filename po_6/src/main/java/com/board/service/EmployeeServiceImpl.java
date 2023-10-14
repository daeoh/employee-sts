package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.EmployeeDTO;
import com.board.mapper.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeMapper em;

@Override
public List<EmployeeDTO> getEmployeeList(EmployeeDTO params) {
	List<EmployeeDTO> employeeList = Collections.emptyList();
	
	employeeList = em.selectEmployeeList(params);
	return employeeList;
}

@Override
public boolean insertEmployee(EmployeeDTO params) {
	int queryResult=0;
	queryResult= em.insertEmployee(params);
	return (queryResult ==1)? true: false;
	//em.insertEmployee(params);
	//return true;	
}
}
