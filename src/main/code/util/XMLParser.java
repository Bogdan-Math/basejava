package main.code.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class XMLParser {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public XMLParser(Class... classes) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classes);
            marshaller = jaxbContext.createMarshaller();
            unmarshaller = jaxbContext.createUnmarshaller();

            marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.name());
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void marshall(Writer writer, T instance) {
        try {
            marshaller.marshal(instance, writer);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T unmarshal(Reader reader) {
        try {
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
