package com.cst438.domain;

import java.sql.Date;

public class AssignmentDTO {
	
	public int assignmentId;
	public Course courseId;
	public String assignmentName;
	public Date dueDate;
	public String courseTitle;
	public int needsGrading;
	
	public AssignmentDTO(int assignmentId, Course courseId, String assignmentName, Date dueDate, String courseTitle, int needsGrading) {
		super();
		this.assignmentId = assignmentId;
		//this.courseId = courseId;
		this.assignmentName = assignmentName;
		this.dueDate = dueDate;
		//this.courseTitle = courseTitle;
		this.needsGrading = needsGrading;
	}

	public AssignmentDTO() {
		super();
	}

	public int getAssignmentID() {
		return assignmentId;
	}

	public void setAssignmentID(int assignmentID) {
		this.assignmentId = assignmentID;
	}

	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}

	public String getassignmentName() {
		return assignmentName;
	}

	public void setassignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public int getNeedsGrading() {
		return needsGrading;
	}
	public void setNeedsGrading(int needsGrading) {
		this.needsGrading = needsGrading;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssignmentDTO other = (AssignmentDTO) obj;
		if (assignmentId != other.assignmentId)
			return false;
		if (assignmentName == null) {
			if (other.assignmentName != null)
				return false;
		} else if (!assignmentName.equals(other.assignmentName))
			return false;
		if (courseId != other.courseId)
			return false;
		if (courseTitle == null) {
			if (other.courseTitle != null)
				return false;
		} else if (!courseTitle.equals(other.courseTitle))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		return true;
	}
	
	
}