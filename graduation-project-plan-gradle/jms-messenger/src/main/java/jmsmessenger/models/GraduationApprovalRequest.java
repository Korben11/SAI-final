/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmsmessenger.models;

import jmsmessenger.gateways.IRequest;

/**
 *
 * @author 884294
 */
public class GraduationApprovalRequest implements IRequest {
    
    private int studentNumber;       
    private String company;
    private String projectTitle;    
    private int ecs;
    private String mentor;
    
    public GraduationApprovalRequest (int studentNumber, String company, String projectTitle, int ecs, String mentor){
        this.studentNumber = studentNumber;
        this.company = company;
        this.projectTitle = projectTitle;
        this.ecs = ecs;
        this.mentor = mentor;
    }
    
    public GraduationApprovalRequest (){
        this.studentNumber = 0;
        this.company = "unknown";
        this.projectTitle = "unknown";
        this.ecs = 0;
        this.mentor = "unknown";
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
    
    public int getEcs() {
        return ecs;
    }

    public void setEcs(int ecs) {
        this.ecs = ecs;
    }
    
    
    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }
    
        @Override
     public String toString(){
        return "stnr="+ studentNumber + " comp=" + company + " proj=" + projectTitle + " ecs="
				+ ecs + " mentor=" + mentor;
    }
}
