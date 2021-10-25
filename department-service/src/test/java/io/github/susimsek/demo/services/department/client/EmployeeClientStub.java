package io.github.susimsek.demo.services.department.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.susimsek.demo.services.department.model.Employee;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.github.susimsek.demo.services.department.util.EmployeeUtil.*;
import static io.github.susimsek.demo.services.department.util.JsonUtil.*;


public class EmployeeClientStub {

    public static void stubFindEmployeesByDepartmentId() throws JsonProcessingException {
        final List<Employee> stubEmployees = getSampleAllEmployees();
        stubFor(get(urlPathMatching("/department/.*"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(toJson(stubEmployees))
                ));
    }

}