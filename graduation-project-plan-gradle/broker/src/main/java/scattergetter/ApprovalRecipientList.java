package scattergetter;

import jmsmessenger.Constants;
import jmsmessenger.gateways.AsyncSenderGateway;
import jmsmessenger.models.GraduationApprovalRequest;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class ApprovalRecipientList {
    private ApprovalRule rules[] = {
            new ApprovalRule(Constants.APPROVAL.GRAD_COORDINATOR, Constants.GRAD_COORDINATOR_ECS),
            new ApprovalRule(Constants.APPROVAL.RAFAYEL, Constants.RAFAYEL_ECS),
            new ApprovalRule(Constants.APPROVAL.BERT, Constants.BERT_ECS),
            new ApprovalRule(Constants.APPROVAL.CHUNG, Constants.CHUNG_ECS),
    };
    private AsyncSenderGateway gateway;
    private Evaluator evaluator;

    public ApprovalRecipientList(AsyncSenderGateway gateway) {
        this.gateway = gateway;
        evaluator = new Evaluator();
    }

    public int sendRequest(GraduationApprovalRequest request, Integer aggregationId) {
        int passed = 0;

        setEvaluator(request.getMentor(), request.getEcs());

        try {
            for (ApprovalRule rule : rules) {
                if (!(evaluator.evaluate(rule.getRule()).equals("1.0")))
                    continue;
                passed++;
                gateway.sendRequest(request, rule.getApproval() + Constants.APPROVAL_CLIENT_REQUEST_QUEUE, aggregationId);
            }
        } catch (EvaluationException e) {
            e.printStackTrace();
        }

        return passed;
    }

    private void setEvaluator(String mentor, int ecs) {
        evaluator.clearVariables();
        evaluator.putVariable("mentor", mentor);
        evaluator.putVariable("ecs", Integer.toString(ecs));
    }
}
