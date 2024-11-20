package com.digital.marketing.be.service.dto;

import com.digital.marketing.be.repository.entity.ContractType;

public class ContractAllocationTemplateReq {

    private ContractType contractType;
    private Integer manDays;

    public ContractAllocationTemplateReq() {

    }

    public ContractAllocationTemplateReq(ContractType contractType, Integer manDays) {
        this.contractType = contractType;
        this.manDays = manDays;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Integer getManDays() {
        return manDays;
    }

    public void setManDays(Integer manDays) {
        this.manDays = manDays;
    }
}
