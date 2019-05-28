/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmsmessenger.models;

import jmsmessenger.gateways.IResponse;

/**
 *
 * @author 884294
 */
public class GraduationClientReply implements IResponse {
    private boolean approved;
    private String rejectedBy;
    
    public GraduationClientReply(boolean approved, String rejectedBy){
        this.approved = approved;
        this.rejectedBy = rejectedBy;
    }
    
    public GraduationClientReply(){
        this.approved = false;
        this.rejectedBy = "unknown";
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getRejectedBy() {
        return rejectedBy;
    }

    public void setRejectedBy(String rejectedBy) {
        this.rejectedBy = rejectedBy;
    }
    
    @Override
    public String toString(){
        return (approved?"approved":"rejected") + ((!approved)?rejectedBy:"");
    }
}
