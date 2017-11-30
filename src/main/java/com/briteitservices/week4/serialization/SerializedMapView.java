package com.briteitservices.week4.serialization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Serialized view of a map.
 *
 * @author Stuart Douglas
 */
public class SerializedMapView {

    private final Map<Class<?>, SerializedView> serializers;

    SerializedMapView(Map<Class<?>, SerializedView> serializers) {
        this.serializers = Collections.unmodifiableMap(serializers);
    }

    public static SerializedMapView create(final SerializedView... view) {
        Map<Class<?>, SerializedView> serializers = new HashMap<>();
        for (SerializedView v : view) {
            serializers.put(v.getEntityClass(), v);
        }
        return new SerializedMapView(serializers);
    }

    public SerializedObject<Map> serialize(final Map<String, ?> map) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            SerializedView serializer = null;
            Class<?> type = entry.getValue().getClass();
            while (type != null && type != Object.class && serializer == null) {
                serializer = serializers.get(type);
                type = type.getSuperclass();
            }
            if (serializer == null) {
                throw new IllegalArgumentException("No serializer for " + entry.getValue().getClass());
            }
            builder.add(entry.getKey(), serializer.serializeToJson(entry.getValue(), Collections.emptyMap()));
        }
        JsonObject build = builder.build();
        return new SerializedObject<Map>(map, build);
    }
}
