package com.digital.marketing.be.service.dto;

public class ContractAllocationTemplateResp {

    private Integer numberOfBE;
    private Integer numberOfFE;
    private Integer numberOfBA;

    public ContractAllocationTemplateResp() {

    }

    public ContractAllocationTemplateResp(Integer numberOfBE, Integer numberOfFE, Integer numberOfBA) {
        this.numberOfBE = numberOfBE;
        this.numberOfFE = numberOfFE;
        this.numberOfBA = numberOfBA;
    }

    public Integer getNumberOfBE() {
        return numberOfBE;
    }

    public void setNumberOfBE(Integer numberOfBE) {
        this.numberOfBE = numberOfBE;
    }

    public Integer getNumberOfFE() {
        return numberOfFE;
    }

    public void setNumberOfFE(Integer numberOfFE) {
        this.numberOfFE = numberOfFE;
    }

    public Integer getNumberOfBA() {
        return numberOfBA;
    }

    public void setNumberOfBA(Integer numberOfBA) {
        this.numberOfBA = numberOfBA;
    }
}
