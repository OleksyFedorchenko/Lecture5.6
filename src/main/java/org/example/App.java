package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws IOException, JAXBException {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        List<Violation> violations = new ArrayList<>();
        List<String> jsonFileNames = getFileNames();
        ExecutorService executorService = Executors.newFixedThreadPool(5);


            for (String file : jsonFileNames) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    violations.addAll(objectMapper.readValue(br, new TypeReference<List<Violation>>() {
                    }));
            }
        }

        //Робимо мапу з типами і сумою штрафів
        Map<String, Double> result = new HashMap<>();
        for (Violation v : violations) {
            if (result.containsKey(v.getType())) {
                double sum = result.get(v.getType());
                result.put(v.getType(), sum + v.getFineAmount());
            } else result.put(v.getType(), v.getFineAmount());
        }

        //Пишемо в XML файл за допомогою парсера JAXB.
        writeResultToXmlByJAXB(sortingResultMap(result));

    }

    //Шукаємо всі json файли в поточному каталозі
    public static List<String> getFileNames() throws IOException {
        try (Stream<Path> walk = Files.walk(Paths.get("."), 1)) {
            return walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(Path::getFileName)
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith("json"))
                    .collect(Collectors.toList());
        }
    }

    //Метод сортування результатів в порядку спадання суми штрафів.
    public static LinkedHashMap<String, Double> sortingResultMap(Map<String, Double> result) {
        return result.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    //Пишемо в XML файл за допомогою парсера JAXB
    public static void writeResultToXmlByJAXB(Map<String, Double> map) throws JAXBException {
        ViolationsMap violationsMap = new ViolationsMap();
        violationsMap.setViolate(map);
        JAXBContext jaxbContext = JAXBContext.newInstance(ViolationsMap.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(violationsMap, System.out);
        jaxbMarshaller.marshal(violationsMap, new File("parsed_json.xml"));
    }
}
