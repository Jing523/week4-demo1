package com.briteitservices.week4.serialization;

import javax.json.JsonStructure;

/**
 * A semi-serialized representation of an object
 *
 * @author Stuart Douglas
 */
public class SerializedObject<T> {

    private final T object;
    private final String serialized;
    private final JsonStructure jsonValue;

    SerializedObject(T object, JsonStructure jsonValue) {
        this.object = object;
        this.serialized = jsonValue.toString();
        this.jsonValue = jsonValue;
    }

    public T getObject() {
        return object;
    }

    public String getSerialized() {
        return serialized;
    }

    public JsonStructure getJsonValue() {
        return jsonValue;
    }
}
