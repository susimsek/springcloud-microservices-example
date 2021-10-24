package io.github.susimsek.services.organization.endpoint;

import io.github.susimsek.services.organization.model.*;
import io.github.susimsek.services.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

import static io.github.susimsek.services.organization.util.OrganizationUtil.*;

@Endpoint
@RequiredArgsConstructor
public class OrganizationEndpoint {

    private final OrganizationService organizationService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createOrganizationRequest")
    @ResponsePayload
    public CreateOrganizationResponse add(@RequestPayload CreateOrganizationRequest request) {
        Organization organization = organizationService.createOrganization(request.getOrganization());
        return CreateOrganizationResponse.builder()
                .organization(organization)
                .build();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllOrganizationsRequest")
    @ResponsePayload
    public GetAllOrganizationsResponse findAll(@RequestPayload GetAllOrganizationsRequest request) {
        List<Organization> organizations = organizationService.getAllOrganizations();
       return GetAllOrganizationsResponse.builder()
               .organization(organizations)
               .build();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrganizationByIdRequest")
    @ResponsePayload
    public GetOrganizationByIdResponse findById(@RequestPayload GetOrganizationByIdRequest request) {
        Organization organization = organizationService.getOrganizationById(request.getId());
        return GetOrganizationByIdResponse.builder()
                .organization(organization)
                .build();
    }
}
