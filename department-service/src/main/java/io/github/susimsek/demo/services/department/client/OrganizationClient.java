package io.github.susimsek.demo.services.department.client;

import io.github.susimsek.demo.services.department.model.GetOrganizationByIdRequest;
import io.github.susimsek.demo.services.department.model.GetOrganizationByIdResponse;
import io.github.susimsek.demo.services.department.model.Organization;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class OrganizationClient extends WebServiceGatewaySupport {

    public Organization findByOrganization(Long departmentId) {
        GetOrganizationByIdRequest request = new GetOrganizationByIdRequest();
        request.setId(departmentId);

        GetOrganizationByIdResponse response = (GetOrganizationByIdResponse) getWebServiceTemplate()
          .marshalSendAndReceive(request);
        return response.getOrganization();
    }
}