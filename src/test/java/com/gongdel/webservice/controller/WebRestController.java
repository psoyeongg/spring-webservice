package com.gongdel.webservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebRestController {

    @Autowired
    private TestRestTemplate testRestTemplate;

 /*   @Test
    public void 프로필확인() {
        // when
        String profile = this.testRestTemplate.getForObject("/profile", String.class);

        // then
        assertThat(profile).isEqualTo("local");

    }*/
}
