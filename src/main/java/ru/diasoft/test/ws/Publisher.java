package ru.diasoft.test.ws;

import javax.xml.ws.Endpoint;

/**
 * Publishes web service {@link ru.diasoft.test.ws.ContactsWs} on http://localhost:9901/diasoft.
 * Used for running tests and debugging
 */
public class Publisher {
    public final static String url = "http://localhost:9901/diasoft";
    protected static Endpoint endpoint;

    /**
     * Used for debugging
     *
     * @param args No args
     */
    public static void main(String... args) {
        publish();
    }

    /**
     * Publishes web service.
     */
    public static void publish(){
        endpoint = Endpoint.publish(url, new ContactsWsImpl());
    }

    /**
     * Stops published web service.
     */
    public static void stop(){
        if (null != endpoint && endpoint.isPublished()){
            endpoint.stop();
        }
    }

}
