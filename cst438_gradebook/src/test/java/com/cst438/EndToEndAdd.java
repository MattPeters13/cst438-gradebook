package com.cst438;

import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import org.springframework.beans.factory.annotation.Autowired;

import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentGrade;
import com.cst438.domain.AssignmentGradeRepository;
import com.cst438.domain.AssignmentRepository;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;


@SpringBootTest
public class EndToEndAdd {
	
	public static final String CHROME_DRIVER_FILE_LOCATION = "/Users/matthewpeters/Downloads/chromedriver";
	public static final String URL = "http://localhost:3000";
	public static final String TEST_USER_EMAIL = "test@csumb.edu";
	public static final String TEST_INSTRUCTOR_EMAIL = "dwisneski@csumb.edu";
	public static final int SLEEP_DURATION = 1000; // 1 second.
	public static final String TEST_ASSIGNMENT_NAME = "Test Assignment";
	public static final String TEST_COURSE_TITLE = "Test Course";
	public static final String TEST_STUDENT_NAME = "Test";
	
	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	AssignmentGradeRepository assignnmentGradeRepository;

	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Test
	public void AddAssignment() throws Exception{
		
		
		Course c = new Course();
		c.setCourse_id(10101);
		c.setYear(2022);
		c.setSemester("Spring");
		c.setTitle(TEST_COURSE_TITLE);
		c.setInstructor(TEST_INSTRUCTOR_EMAIL);
		courseRepository.save(c);
		
		Assignment a  = new Assignment();
		a.setName(TEST_ASSIGNMENT_NAME);
		a.setNeedsGrading(1);
		a.setDueDate(new Date(2023-01-25));
		a.setCourse(c);
		
		//--------------------------
		//Web setup for Chrome
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		Thread.sleep(SLEEP_DURATION);
		//--------------------------
		try {
			
			driver.findElement(By.xpath("//input[@name='name']")).sendKeys(a.getName());
			driver.findElement(By.xpath("//input[@name='courseId']")).sendKeys(Integer.toString(a.getCourse().getCourse_id()));
			driver.findElement(By.xpath("//input[@name='dueDate']")).sendKeys("2023-01-25");
			
			Thread.sleep(SLEEP_DURATION);
			driver.findElement(By.xpath("//button[@id='add']")).click();
			Thread.sleep(SLEEP_DURATION);
			
			String toast_text = driver.findElement(By.cssSelector(".Toastify__toast-body")).getText();
			System.out.println("First toast text: " + toast_text);
			
			Thread.sleep(SLEEP_DURATION);
			assertEquals(toast_text, "Assignment added");
		
			
		}finally {
			/*
			 *  clean up database so the test is repeatable.
			 */
			assignmentRepository.delete(assignmentRepository.findCourseId(a.getCourse().getCourse_id()).orElse(null));
			courseRepository.delete(c);
			System.out.println("deleted");
		}
		driver.quit();
    }
		
  

}
