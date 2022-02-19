package com.github.pomaretta.cide.service;

import com.github.pomaretta.cide.domain.Request;

public interface Service {
    Object operate(Request request) throws Exception;
}
