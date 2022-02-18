package com.github.pomaretta.cide.service;

import java.util.HashMap;

public interface Cache {
    public void put(String key, String value) throws Exception;
    public void replace(String key, String value) throws Exception;
    public String get(String key) throws Exception;
    public void remove(String key) throws Exception;
    public HashMap<String,String> getAll() throws Exception;
}