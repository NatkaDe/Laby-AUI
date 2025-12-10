package com.example.art_museum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class RestRepository {
    @Autowired
    private RestTemplate restTemplate;
}

