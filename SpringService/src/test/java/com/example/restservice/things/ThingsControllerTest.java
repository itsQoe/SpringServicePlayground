package com.example.restservice.things;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ThingsControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void shouldGetAllThings() throws Exception {
		mvc.perform(
				put("/things").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content("""
						  {"name":"test","description":"desc"}
						"""))
			.andExpect(status().isOk());
		mvc.perform(get("/things").accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().is2xxSuccessful());
	}

}
