package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.DeptDTO;
import com.board.domain.EmployeeDTO;

@Mapper
public interface EmployeeMapper {
  public List<EmployeeDTO> selectEmployeeList(EmployeeDTO params);
  public int insertDept(DeptDTO params);
  public int insertEmployee(EmployeeDTO params);

}