package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@XmlRootElement(name = "violations")
@XmlAccessorType(XmlAccessType.FIELD)
public class ViolationsMap {
    @XmlElementWrapper(name="violation")
    @XmlElement(name="element")
    private Map<String, Double> violate = new LinkedHashMap<>();
}
