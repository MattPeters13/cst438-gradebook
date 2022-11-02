package com.cst438.domain;

import java.util.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends CrudRepository <Course, Integer> {

	@Query("select a from Course a where a.course_id = :course_id")
	Course findById(@Param("course_id") Course course_id);
	
	@Query("select a from Assignment a where a.course.course_id = :course_id")
	Optional<Assignment>findCourseId(@Param("course_id")int courseId);
}
