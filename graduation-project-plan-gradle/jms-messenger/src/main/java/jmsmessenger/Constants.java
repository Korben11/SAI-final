package jmsmessenger;

public final class Constants {

    // services
    public static final String HTTP_LOCALHOST_8080_CREDIT_REST_HISTORY = "http://localhost:8080/credit/rest/history/";
    public static final String HTTP_LOCALHOST_8080_ARCHIVE_REST_ACCEPTED = "http://localhost:8080/archive/rest/accepted";

    public static final String TCP_LOCALHOST_61616 = "tcp://localhost:61616";
    public static final String ORG_APACHE_ACTIVEMQ_JNDI_ACTIVE_MQINITIAL_CONTEXT_FACTORY = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
    public static final String QUEUE = "queue.";
    public static final String CONNECTION_FACTORY = "ConnectionFactory";

    // Rules
    // LTE = LESS THAN EQUAL
    // GTE = GREATER THAN EQUAL

    // Queue names
    public static final String CLIENT_REQUEST_QUEUE = "ClientRequestQueue";
    public static final String CLIENT_RESPONSE_QUEUE = "ClientResponseQueue";

    public static final String AGGREGATION_ID = "aggregationId";

    private Constants(){}
}
