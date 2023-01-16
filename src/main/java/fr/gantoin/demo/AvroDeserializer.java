package fr.gantoin.demo;

import java.io.IOException;

import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;

import fr.gantoin.avro.AvroHttpRequest;

public class AvroDeserializer {

    public AvroHttpRequest deserializeAvroHttpRequestJSON(byte[] data) {
        DatumReader<AvroHttpRequest> reader = new SpecificDatumReader<>(AvroHttpRequest.class);
        Decoder decoder;
        try {
            decoder = DecoderFactory.get().jsonDecoder(AvroHttpRequest.getClassSchema(), new String(data));
            return reader.read(null, decoder);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing AvroHttpRequest", e);
        }
    }
}
