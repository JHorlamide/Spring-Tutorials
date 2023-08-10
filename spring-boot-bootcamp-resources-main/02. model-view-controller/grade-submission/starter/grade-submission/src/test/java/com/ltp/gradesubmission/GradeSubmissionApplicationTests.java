package com.ltp.gradesubmission;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GradeSubmissionApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@Test
	public void testShowGradeForm() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/?1234");

		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"))
			.andExpect(model().attributeExists("grade"));
	}

	@Test
	public void testSuccessfulFormSubmission() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
			.param("studentName", "Olamide")
			.param("subject", "Mathematics")
			.param("score", "A+");

		mockMvc.perform(request)
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/grades"));
	}

	@Test
	public void testUnsuccessFormSubmission() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
			.param("studentName", "		")
			.param("subject", "		")
			.param("score", "R+");

		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"));
	}

	@Test
	public void testShowGrades() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/grades");

		mockMvc.perform(request)
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("grades"))
		.andExpect(model().attributeExists("grades"));
	}
}
