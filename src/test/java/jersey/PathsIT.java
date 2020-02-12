package jersey;

import static com.the.mild.project.server.util.ResourceConfig.PATH_TEST_RESOURCE;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.the.mild.project.server.Main;
import com.the.mild.project.server.jackson.JacksonTest;
import com.the.mild.project.server.jackson.util.JacksonHandler;

public class PathsIT {
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        server = Main.startServer();

        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testPathTestResource() {
        final JacksonTest test = new JacksonTest("test", "resource");

        String expectedResult = JacksonHandler.stringify(test);

        String responseMsg = target.path(PATH_TEST_RESOURCE).request().get(String.class);

        System.out.println(expectedResult);
        assertEquals(expectedResult, responseMsg);
    }
}
