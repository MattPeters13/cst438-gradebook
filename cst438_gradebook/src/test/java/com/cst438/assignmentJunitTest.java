package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.cst438.controllers.GradeBookController;
import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentGrade;
import com.cst438.domain.AssignmentGradeRepository;
import com.cst438.domain.AssignmentListDTO;
import com.cst438.domain.AssignmentRepository;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.GradebookDTO;
import com.cst438.services.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.context.ContextConfiguration;

/* 
 * Example of using Junit with Mockito for mock objects
 *  the database repositories are mocked with test data.
 *  
 * Mockmvc is used to test a simulated REST call to the RestController
 * 
 * the http response and repository is verified.
 * 
 *   Note: This tests uses Junit 5.
 *  ContextConfiguration identifies the controller class to be tested
 *  addFilters=false turns off security.  (I could not get security to work in test environment.)
 *  WebMvcTest is needed for test environment to create Repository classes.
 */
@ContextConfiguration(classes = { GradeBookController.class, AssignmentListDTO.class, AssignmentRepository.class })
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
//@ContextConfiguration(classes = {WebConfig.class})
public class assignmentJunitTest {

	static final String URL = "http://localhost:8080";
	public static final int TEST_COURSE_ID = 40442;
	public static final String TEST_STUDENT_EMAIL = "test@csumb.edu";
	public static final String TEST_STUDENT_NAME = "test";
	public static final String TEST_INSTRUCTOR_EMAIL = "dwisneski@csumb.edu";
	public static final int TEST_YEAR = 2021;
	public static final String TEST_SEMESTER = "Fall";

	@MockBean
	AssignmentRepository assignmentRepository;

	@MockBean
	AssignmentGradeRepository assignmentGradeRepository;

	@MockBean
	CourseRepository courseRepository; // must have this to keep Spring test happy

	@MockBean
	RegistrationService registrationService; // must have this to keep Spring test happy

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void checkAddAssignment()throws Exception{
		
		AssignmentListDTO a = new AssignmentListDTO();
		
		MockHttpServletResponse	response;
		response = mvc.perform(
							MockMvcRequestBuilders
							.post("/studentAdd?id=10&name=assignmentTEST&dueDate=2022-09-20&needsGrading=1"))
							//.content(asJsonString(a))
							//.contentType(MediaType.APPLICATION_JSON)
							//.accept(MediaType.APPLICATION_JSON)
							.andReturn().getResponse();

		assertEquals(200, response.getStatus());
		Assignment assignment = new Assignment();
		
	}
//		assignment.setEmail(TEST_STUDENT_EMAIL);
//		assignment.setName(TEST_STUDENT_NAME);
//		
//		given(assignmentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(assignment);
//		verify(assignmentRepository).save(any(Assignment.class));
//		response = mvc.perform(
//				MockMvcRequestBuilders
//			      .post("/student")
//			      .content(asJsonString(student))
//			      .contentType(MediaType.APPLICATION_JSON)
//			      .accept(MediaType.APPLICATION_JSON))
//				.andReturn().getResponse();
//
//		assertEquals(400, response.getStatus());
			
		private static String asJsonString(final Object obj) {
			try {

				return new ObjectMapper().writeValueAsString(obj);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
}
