package fontys.s3.Carspacebackend.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericObjectResponse {
    private String message;
    private Object obj;
}