package com.testcreation.trainer.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		if(response.getStatusCode().series() == Series.CLIENT_ERROR) {
			return false;
		}
		return response.getStatusCode().series() == Series.SERVER_ERROR;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if(response.getStatusCode().series() == Series.CLIENT_ERROR) {
			return;
		}
	}

}
