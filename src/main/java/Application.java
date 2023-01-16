import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

public class Application {
    public static void main(String[] args) {
        Schema clientIdentifier = SchemaBuilder.record("ClientIdentifier")
                .namespace("fr.gantoin.avro")
                .fields().requiredString("hostname").requiredString("ipAddress")
                .endRecord();

        Schema avroHttpRequest = SchemaBuilder.record("AvroHttpRequest")
                .namespace("fr.gantoin.avro")
                .fields().requiredLong("requestTime")
                .name("clientIdentifier").type(clientIdentifier).noDefault()
                .name("employeeNames").type().array().items().stringType().arrayDefault(null)
                .name("active").type().enumeration("Active").symbols("YES", "NO").noDefault()
                .endRecord();

        // Sout the Avro schema
        System.out.println(avroHttpRequest.toString(true));
    }
}
