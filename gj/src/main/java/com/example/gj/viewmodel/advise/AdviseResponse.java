package com.example.gj.viewmodel.advise;

import com.example.gj.model.Advise;
import lombok.Data;

import java.util.Date;

@Data
public class AdviseResponse {
    private String id;
    private String fullName;
    private String phone;
    private String email;
    private String advise;
    private int status;
    private Date createdAt;
    private String processingBy;

    public AdviseResponse(Advise advise) {
        this.id = advise.getId();
        this.fullName = advise.getFullName();
        this.phone = advise.getPhone();
        this.email = advise.getEmail();
        this.advise = advise.getAdvise();
        this.status = advise.getStatus();
        this.createdAt = advise.getCreatedAt();
        this.processingBy = advise.getProcessingBy();
    }
}
