package content.enricher;

import jmsmessenger.gateways.IRequest;
import jmsmessenger.gateways.IResponse;

public class StudentInfo implements IResponse, IRequest {
    public int graduationPhaseECs;
    public String mentor;

    @Override
    public String toString() {
        return "StudentInfo{" +
                "graduationPhaseECs=" + graduationPhaseECs +
                ", mentor='" + mentor + '\'' +
                '}';
    }

    public int getGraduationPhaseECs() {
        return graduationPhaseECs;
    }

    public String getMentor() {
        return mentor;
    }
}
