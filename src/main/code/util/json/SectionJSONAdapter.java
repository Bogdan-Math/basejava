package main.code.util.json;

import com.google.gson.*;

import java.lang.reflect.Type;

public class SectionJSONAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, src.getClass().getName());
        JsonElement jsonElement = context.serialize(src);
        jsonObject.add(INSTANCE, jsonElement);
        return jsonObject;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String asString = jsonPrimitive.getAsString();
        try {
            Class<?> aClass = Class.forName(asString);
            return context.deserialize(jsonObject.get(INSTANCE), aClass);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
}
