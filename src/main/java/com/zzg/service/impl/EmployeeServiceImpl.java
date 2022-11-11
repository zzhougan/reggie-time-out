package com.zzg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzg.entity.Employee;
import com.zzg.mapper.EmployeeMapper;
import com.zzg.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @Date 2022/11/1
 * @Author: Gan
 * @Description:
 **/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
