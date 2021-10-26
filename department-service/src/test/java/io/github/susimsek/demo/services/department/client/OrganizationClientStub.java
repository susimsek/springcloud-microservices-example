package io.github.susimsek.demo.services.department.client;

import wiremock.org.apache.commons.io.IOUtils;


import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class OrganizationClientStub {

    public static void stubFindOrganizationById() throws IOException {
        String responseEnvelope = IOUtils.toString(OrganizationClientStub.class.getResourceAsStream("/contract/findOrganizationByIdResponse.xml"),"UTF-8");

        stubFor(post(urlEqualTo("/ws"))
                .withHeader("Content-Type", containing("text/xml; charset=UTF-8"))
                .withRequestBody(matching(".*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(responseEnvelope))
                        .withHeader("Content-Type", containing("text/xml; charset=UTF-8")));
    }

}