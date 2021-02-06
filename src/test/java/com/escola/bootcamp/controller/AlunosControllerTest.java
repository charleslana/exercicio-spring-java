package com.escola.bootcamp.controller;

import com.escola.bootcamp.BootcampApplicationTests;
import com.escola.bootcamp.controllers.AlunosController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AlunosControllerTest extends BootcampApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private AlunosController alunosController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(alunosController).build();
    }

    @Test
    public void testGETIndexAlunosController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPOSTSaveAlunosController() throws Exception {
        String json = new ObjectMapper().writeValueAsString(ImmutableMap.builder()
                .put("nome", "Aluno D")
                .put("grade.id", "2")
                .build());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
