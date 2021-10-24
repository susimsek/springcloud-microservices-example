//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.10.24 at 08:39:42 PM TRT 
//


package io.github.susimsek.services.organization.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.*;

@Entity
@Table(name = "organization")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "organization", propOrder = {
    "id",
    "name",
    "address"
})
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_id_seq")
    @SequenceGenerator(name="organization_id_seq", sequenceName = "ORGANIZATION_ID_SEQ", allocationSize = 1)
    private long id;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String address;
}
