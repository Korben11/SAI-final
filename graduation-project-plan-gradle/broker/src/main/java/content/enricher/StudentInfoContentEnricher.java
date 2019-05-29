package content.enricher;

import jmsmessenger.serializers.GsonSerializer;
import jmsmessenger.serializers.Serializer;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class StudentInfoContentEnricher {
    private WebTarget client;
    private Serializer serializer;

    public StudentInfoContentEnricher(String url) {
        URI uri = UriBuilder.fromUri(url).build();
        client = ClientBuilder.newClient(new ClientConfig()).target(uri);
        serializer = new GsonSerializer(StudentInfo.class, StudentInfo.class);
    }

    public StudentInfo getStudentInfo(int studentNr) {
        Invocation.Builder request = client.path(Integer.toString(studentNr)).request(MediaType.APPLICATION_JSON);
        Response response = request.get();
        String json = response.readEntity(String.class);
        if (response.getStatus() == 200) {
            return (StudentInfo) serializer.deserializeRequest(json);
        }
        return null;
    }
}
