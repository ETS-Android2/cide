package com.github.pomaretta.cide.service;

import java.io.IOException;

import com.github.pomaretta.cide.domain.Request;
import com.github.pomaretta.cide.domain.Response;

public interface Protocol {
    Response send(Request request) throws IOException;
}
