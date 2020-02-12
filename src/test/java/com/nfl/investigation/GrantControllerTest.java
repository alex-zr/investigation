package com.nfl.investigation;


import com.auth0.jwt.JWT;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.nfl.investigation.Constants.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvestigationApplication.class)
@AutoConfigureMockMvc
public class GrantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSimpleController_HasGrants_Success() throws Exception {
        String claims = "{" +
                            "\"roles\": {\"CMS\":[\"USER\", \"ADMIN\"], \"POOLS\":[\"EDITOR\"], \"PRODUCT\":[\"ROLE\"]}" +
                        "}";

        String token = JWT.create()
                .withSubject(claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));

        mockMvc.perform(MockMvcRequestBuilders.get("/hello").header(HEADER_STRING, token))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world!"));
    }

    @Test
    public void testSimpleController_AbsentGrants_Forbidden() throws Exception {
        List<String> roles = Arrays.asList("HACKER", "TERRORIST");
        Map<String, List<String>> map = Collections.singletonMap("CMS", roles);
        Map<String, Map<String, List<String>>> rolesMap = Collections.singletonMap(ROLES_PREFIX, map);
        String claims = JsonUtils.writeValueAsString(rolesMap);

        String token = JWT.create()
                .withSubject(claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));


        mockMvc.perform(MockMvcRequestBuilders.get("/hello").header(HEADER_STRING, token))
                .andExpect(status().isForbidden());
    }
}