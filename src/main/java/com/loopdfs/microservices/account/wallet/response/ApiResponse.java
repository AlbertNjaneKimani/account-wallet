package com.loopdfs.microservices.account.wallet.response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private Header headers;
    private Body body;

    public ApiResponse() {
        this.headers = new Header();
        this.body = new Body();
    }

    public static ApiResponse createResponse(int responseCode, String customerMessage,String responseMessage, Object responseBody) {
        ApiResponse response = new ApiResponse();
        response.headers.setResponseCode(responseCode);
        response.headers.setCustomerMessage(customerMessage);
        response.headers.setTechnicalMessage(responseMessage);
        response.body.setData(responseBody);
        return response;
    }

}

