package main.code.storage.disk.strategy;

import main.code.model.*;
import main.code.util.xml.XMLParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XMLStreamSerializerStrategy implements StreamSerializerStrategy {

    private XMLParser xmlParser;

    public XMLStreamSerializerStrategy() {
        xmlParser = new XMLParser(
                Resume.class,
                Organization.class,
                Link.class,
                OrganizationSection.class,
                TextSection.class,
                ListSection.class,
                Organization.Position.class
        );
    }

    @Override
    public void doWrite(OutputStream outputStream, Resume resume) throws IOException {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            xmlParser.<Resume>marshall(outputStreamWriter, resume);
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return xmlParser.<Resume>unmarshal(inputStreamReader);
        }
    }
}
