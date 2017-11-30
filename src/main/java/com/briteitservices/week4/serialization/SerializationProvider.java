package com.briteitservices.week4.serialization;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * @author Stuart Douglas
 */
@Provider()
@Produces("application/json")
public class SerializationProvider implements MessageBodyWriter<SerializedObject<?>> {

    private static final Charset UTF_8;

    static {
        UTF_8 = Charset.forName("UTF-8");
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return SerializedObject.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(SerializedObject<?> serializedObject, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(SerializedObject<?> serializedObject, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        entityStream.write(serializedObject.getSerialized().getBytes(UTF_8));
    }
}
