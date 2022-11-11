package com.zzg.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzg.common.R;
import com.zzg.entity.Employee;
import com.zzg.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date 2022/11/1
 * @Author: Gan
 * @Description: 用于接收和处理与员工信息有关的请求
 **/
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        //将页面提交过来的密码进行md5加密
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        //根据页面提交的用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //如果没有查询到则返回登录失败
        if(emp == null){
            return R.error("登录失败");
        }
        //如果密码不一致也是返回登录失败
        if(!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }
        //查看员工状态，如果员工已禁用，则返回员工已禁用
        if(emp.getStatus() == 0){
            return R.error("员工已禁用");
        }
        //登陆成功，将员工id存入Session并返回登陆成功
        request.getSession().setAttribute("emp", emp.getId());
        //log.info("Controller查询到用户Session:" + request.getSession().getAttribute("emp"));
        return R.success(emp);
    }

    @PostMapping("logout")
    public R<Employee> logout(){
        return R.success(null);
    }
}
