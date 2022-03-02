package io.github.susimsek.demo.services.department.util;

import io.github.susimsek.demo.services.department.model.Department;

import io.github.susimsek.demo.services.department.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.github.susimsek.demo.services.department.util.DepartmentUtil.getSampleDepartment;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class FooUtilTest {

    @Mock
    DepartmentServiceImpl departmentService;

    @Test
    void getFoo() {
        FooUtil.setDepartmentService(departmentService);
        Department department = getSampleDepartment();
        Mockito.when(departmentService.getDepartmentById(anyLong())).thenReturn(department);
        String foo = FooUtil.getFoo();
        assertEquals(foo, department.getName());
    }
}