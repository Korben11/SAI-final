package scattergetter;

import jmsmessenger.Constants;

public class ApprovalRule {
    private Constants.APPROVAL approval;
    private String rule;

    public ApprovalRule(Constants.APPROVAL approval, String rule) {
        this.approval = approval;
        this.rule = rule;
    }

    public Constants.APPROVAL getApproval() {
        return approval;
    }

    public String getRule() {
        return rule;
    }
}
