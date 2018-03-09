package com.zk;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Ticket {

	private String customerName;
	private String subject;
	private String body;
	private Map<String, Attachment> attachments = new HashMap<>();

	public String getCustomerName() {
		return customerName;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public Collection<Attachment> getAttachments() {
		return attachments.values();
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Attachment getAttachment(String name) {
		return attachments.get(name);
	}

	public void addAttachment(Attachment attachment) {
		attachments.put(attachment.getName(), attachment);
	}

	public int getNumberOfAttachments() {
		return attachments.size();
	}

}
