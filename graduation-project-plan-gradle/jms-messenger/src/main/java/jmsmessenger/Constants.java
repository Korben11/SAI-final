package jmsmessenger;

public final class Constants {

    // services
    public static final String HTTP_LOCALHOST_8080_ADMINISTRATION_REST_STUDENTS = "http://localhost:8080/administration/rest/students/";
    /**
     *
     * This service randomly generates student information, which consists of mentor name and gained ECs in the graduation phase (semester 7).
     * Output format is JSON.
     * The service can be accessed at http://localhost:8080/administration/rest/students/{studentNumber}.
     *
     * There are three cases for generating ECs:
     *      Exactly 30 ECs are generated for student numbers in range 0..999. For example: http://localhost:8080/administration/rest/students/123
     *      ECs in range [24..29] are randomly generated for student numbers in range 1000..9999. For example: http://localhost:8080/administration/rest/students/1234
     *      ECs in range [0..25] are randomly generated for student numbers higher than 10000. For example: http://localhost:8080/administration/rest/students/12345
     *
     * Response:  {"graduationPhaseECs":30,"mentor":"Rafayel"}
     */

    public static final String TCP_LOCALHOST_61616 = "tcp://localhost:61616";
    public static final String ORG_APACHE_ACTIVEMQ_JNDI_ACTIVE_MQINITIAL_CONTEXT_FACTORY = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
    public static final String QUEUE = "queue.";
    public static final String CONNECTION_FACTORY = "ConnectionFactory";

    // Rules
    // LTE = LESS THAN EQUAL
    // GTE = GREATER THAN EQUAL

    // Approvals
    public static enum APPROVAL {
        GRAD_COORDINATOR,
        CHUNG,
        BERT,
        RAFAYEL,
    }

    // Queue names
    public static final String APPROVAL_CLIENT_REQUEST_QUEUE = "ApprovalClientRequestQueue";
    public static final String APPROVAL_CLIENT_RESPONSE_QUEUE = "ApprovalClientResponseQueue";
    public static final String STUDENT_CLIENT_REQUEST_QUEUE = "StudentClientRequestQueue";
    public static final String STUDENT_CLIENT_RESPONSE_QUEUE = "StudentClientResponseQueue";


    public static final String AGGREGATION_ID = "aggregationId";

    private Constants(){}
}
