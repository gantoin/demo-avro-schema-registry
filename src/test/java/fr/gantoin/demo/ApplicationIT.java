package fr.gantoin.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.gantoin.avro.Active;
import fr.gantoin.avro.AvroHttpRequest;
import fr.gantoin.avro.ClientIdentifier;

public class ApplicationIT {

    AvroSerializer serializer;
    AvroDeserializer deserializer;
    AvroHttpRequest request;

    @Before
    public void setUp() {
        serializer = new AvroSerializer();
        deserializer = new AvroDeserializer();

        ClientIdentifier clientIdentifier = ClientIdentifier.newBuilder()
                .setHostname("localhost")
                .setIpAddress("255.255.255.0")
                .build();

        List<CharSequence> employees = new ArrayList<>();
        employees.add("James");
        employees.add("Alice");
        employees.add("David");
        employees.add("Han");

        request = AvroHttpRequest.newBuilder()
                .setRequestTime(1L)
                .setActive(Active.YES)
                .setClientIdentifier(clientIdentifier)
                .setEmployeeNames(employees)
                .build();
    }

    @Test
    public void whenSerialized_UsingJSONEncoder_ObjectGetsSerialized() {
        byte[] data = serializer.serializeAvroHttpRequestJSON(request);
        Assert.assertTrue(Objects.nonNull(data));
        Assert.assertTrue(data.length > 0);
    }

    @Test
    public void WhenSerializedUsingJSONEncoder_thenObjectGetsSerialized() {
        byte[] data = serializer.serializeAvroHttpRequestJSON(request);
        assertTrue(Objects.nonNull(data));
        assertTrue(data.length > 0);
    }

    @Test
    public void WhenSerializedUsingBinaryEncoder_thenObjectGetsSerialized() {
        byte[] data = serializer.serializeAvroHttpRequestJSON(request);
        assertTrue(Objects.nonNull(data));
        assertTrue(data.length > 0);
    }

    @Test
    public void WhenDeserializeUsingJSONDecoder_thenActualAndExpectedObjectsAreEqual() {
        byte[] data = serializer.serializeAvroHttpRequestJSON(request);
        AvroHttpRequest actualRequest = deserializer.deserializeAvroHttpRequestJSON(data);
        assertEquals(actualRequest, request);
        assertEquals(actualRequest.getRequestTime(), request.getRequestTime());
    }

    @Test
    public void WhenDeserializeUsingBinaryEncoder_thenActualAndExpectedObjectsAreEqual() {
        byte[] data = serializer.serializeAvroHttpRequestJSON(request);
        AvroHttpRequest actualRequest = deserializer.deserializeAvroHttpRequestJSON(data);
        assertEquals(actualRequest, request);
        assertEquals(actualRequest.getRequestTime(), request.getRequestTime());
    }
}
