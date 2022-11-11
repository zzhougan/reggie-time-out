package com.zzg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzg.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Date 2022/10/30
 * @Author: Gan
 * @Description:
 **/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
