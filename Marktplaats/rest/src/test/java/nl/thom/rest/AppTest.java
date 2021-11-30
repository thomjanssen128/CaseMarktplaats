package nl.thom.rest;

import nl.thom.rest.domain.Contact;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.net.URL;
import java.util.List;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class AppTest {

    // Api test

    @ArquillianResource
    private URL deploymentURL;

    private String contactResourcePath;

    @Before
    public void setup() {
        contactResourcePath = deploymentURL + "api/contacts";
    }

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive warEmpty = ShrinkWrap.create(WebArchive.class, "AppIT.war");
        WebArchive warFilled = warEmpty
                .addPackages(true, App.class.getPackage())
                .addAsWebInfResource("META-INF/beans-test.xml", "META-INF/beans.xml")
                .addAsResource("META-INF/persistence-test.xml", "META-INF/persistence.xml");

        return warFilled;
    }

    @Test
    public void testje() {
        Client postman = ClientBuilder.newClient();
        Contact c = Contact.builder().firstName("Joop").surname("J").build();
        Contact d = Contact.builder().firstName("Bertus").build();
        Contact e = Contact.builder().firstName("Ans").build();

        // // post some contacts
        String contactJson_c = postman
                .target(contactResourcePath)
                .request()
                .post(entity(c, APPLICATION_JSON), String.class);
        String contactJson_d = postman
                .target(contactResourcePath)
                .request()
                .post(entity(d, APPLICATION_JSON), String.class);
        String contactJson_e = postman
                .target(contactResourcePath)
                .request()
                .post(entity(e, APPLICATION_JSON), String.class);

        List<Contact> list = postman
                .target(contactResourcePath)
                .request().get(new GenericType<List<Contact>>() {});

        assertEquals(list.size(), 3);
        assertEquals("Joop", list.get(0).getFirstName());
    }
}