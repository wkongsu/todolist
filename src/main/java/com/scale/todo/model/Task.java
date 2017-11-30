package com.scale.todo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {
	@Id
	private String id;
	private String subject;
	private String detail;
	private String status;

	public Task() {
	}

	public Task(String subject, String detail, String status) {
		this.subject = subject;
		this.detail = detail;
		this.status = status;
	}

	public Task(String id, String subject, String detail, String status) {
		this.id = id;
		this.subject = subject;
		this.detail = detail;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Task [id=");
		builder.append(id);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", detail=");
		builder.append(detail);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}