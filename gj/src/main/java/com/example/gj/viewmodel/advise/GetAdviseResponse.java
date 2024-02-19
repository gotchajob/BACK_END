package com.example.gj.viewmodel.advise;

import com.example.gj.model.Advise;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetAdviseResponse {
    private List<AdviseResponse> adviseList;
    private int total;

    public GetAdviseResponse(List<Advise> list, int total) {
        if (adviseList == null) {
            adviseList = new ArrayList<>();
        }

        for (Advise advise : list) {
            adviseList.add(new AdviseResponse(advise));
        }

        this.total = total;
    }
}
