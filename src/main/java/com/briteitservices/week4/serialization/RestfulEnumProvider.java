package com.briteitservices.week4.serialization;

/**
 * @author Stuart Douglas
 */
public class RestfulEnumProvider<T extends Enum & RestfulEnum> {

    private final T value;

    protected RestfulEnumProvider(Class<T> clazz, String value) {
        T[] values = clazz.getEnumConstants();
        T realValue = null;
        for (T v : values) {
            if (v.getLabel().equals(value)) {
                realValue = v;
                break;
            }
        }
        if (realValue == null) {
            throw new RuntimeException("Not a valid enum label: " + value);
        }
        this.value = realValue;
    }

    public T getValue() {
        return value;
    }
}
