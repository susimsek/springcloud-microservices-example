package io.github.susimsek.demo.services.department.util;

import io.github.susimsek.demo.services.department.model.Department;
import io.github.susimsek.demo.services.department.service.DepartmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class FooUtil {

    @Setter
    static DepartmentService departmentService;

    final DepartmentService tmpService;

    @PostConstruct
    public void init() {
        departmentService = tmpService;
    }

    public static String getFoo() {
        Department department = departmentService.getDepartmentById(1L);
        return department.getName();
    }
}
