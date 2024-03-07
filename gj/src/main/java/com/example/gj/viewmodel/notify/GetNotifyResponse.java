package com.example.gj.viewmodel.notify;

import com.example.gj.model.Notify;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetNotifyResponse {
    private List<NotifyResponse> notifyList;
    private long total;

    public GetNotifyResponse(List<Notify> list, long total) {
        this.notifyList = new ArrayList<>();
        for (Notify notify : list) {
            notifyList.add(new NotifyResponse(notify));
        }

        this.total = total;
    }
}
