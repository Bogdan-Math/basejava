package main.code.storage.disk.strategy;

import main.code.model.Resume;
import main.code.util.json.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JSONStreamSerializerStrategy implements StreamSerializerStrategy {

    private JSONParser jsonParser;

    public JSONStreamSerializerStrategy() {
        this.jsonParser = new JSONParser();
    }

    @Override
    public void doWrite(OutputStream outputStream, Resume resume) throws IOException {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            jsonParser.write(outputStreamWriter, resume);
        }

    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return jsonParser.read(inputStreamReader, Resume.class);
        }
    }
}
