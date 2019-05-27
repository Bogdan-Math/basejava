package main.code.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.code.model.Section;

import java.io.Reader;
import java.io.Writer;

public class JSONParser {

    private Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Section.class, new SectionJSONAdapter<Section>())
                .create();
    }

    public <T> T read(Reader reader, Class<T> tClass) {
        return gson.fromJson(reader, tClass);
    }

    public <T> void write(Writer writer, T object) {
        gson.toJson(object, writer);
    }

    public <T> T readObject(String content, Class<T> tClass) {
        return gson.fromJson(content, tClass);
    }

    public <T> String writeObject(T object, Class<T> tClass) {
        return gson.toJson(object, tClass);
    }
}
