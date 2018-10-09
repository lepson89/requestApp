package model;

import enums.RequestType;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleRequest {
    private long id;
    private String data;
    private RequestType requestType;
}
