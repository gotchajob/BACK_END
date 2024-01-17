package com.example.gj.model;

import com.example.gj.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class Response<T> {
    private String status;
    private String responseText;
    private T data;


    public Response(String status, String responseText, T data) {
        this.status = status;
        this.responseText = responseText;
        this.data = data;
    }

    public static <T> ResponseEntity<Response<T>> error(Exception e) {
        Response<T> error = new Response<>(Status.INTERNAL_ERROR.name(), "Error: " + e.getMessage(), null);
        return ResponseEntity.internalServerError().body(error);
    }

    public static <T> ResponseEntity<Response<T>> success(T data) {
        Response<T> response = new Response<>(Status.SUCCESS.name(), "Success", data);
        return ResponseEntity.ok(response);
    }
}
