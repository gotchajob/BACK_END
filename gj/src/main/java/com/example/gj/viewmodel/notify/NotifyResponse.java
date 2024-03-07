package com.example.gj.viewmodel.notify;

import com.example.gj.model.Notify;
import lombok.Data;

import java.util.Date;

@Data
public class NotifyResponse {
    private String id;
    private String title;
    private String description;
    private Date createdAt;

    public NotifyResponse(Notify notify) {
        this.id = notify.getId();
        this.title = notify.getTitle();
        this.description = notify.getDescription();
        this.createdAt = notify.getCreatedAt();
    }
}
