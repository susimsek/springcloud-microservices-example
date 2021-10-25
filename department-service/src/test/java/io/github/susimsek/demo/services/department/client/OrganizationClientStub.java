package io.github.susimsek.demo.services.department.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.xml.messaging.saaj.soap.Envelope;
import io.github.susimsek.demo.services.department.model.Organization;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.github.susimsek.demo.services.department.util.OrganizationUtil.*;

public class OrganizationClientStub {

    public static void stubFindOrganizationById() throws JsonProcessingException {
        final Organization stubOrganization = getSampleOrganization();

        String soapResponse = String.format("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <SOAP-ENV:Header/>\n" +
                "    <SOAP-ENV:Body>\n" +
                "        <ns2:getOrganizationByIdResponse xmlns:ns2=\"http://susimsek.github.io/organizations\">\n" +
                "            <ns2:organization>\n" +
                "                <ns2:id>%d</ns2:id>\n" +
                "                <ns2:name>%s</ns2:name>\n" +
                "                <ns2:address>%s</ns2:address>\n" +
                "            </ns2:organization>\n" +
                "        </ns2:getOrganizationByIdResponse>\n" +
                "    </SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>",stubOrganization.getId(),stubOrganization.getName(),stubOrganization.getAddress());

        stubFor(post(urlEqualTo("/ws"))
                .withHeader("Content-Type", containing("text/xml; charset=UTF-8"))
                .withRequestBody(matching(".*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(soapResponse))
                        .withHeader("Content-Type", containing("text/xml; charset=UTF-8")));
    }

}