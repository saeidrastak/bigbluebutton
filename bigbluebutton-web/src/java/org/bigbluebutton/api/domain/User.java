package org.bigbluebutton.api.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class User {
	private String internalUserId;
	private String externalUserId;
	private String fullname;
	private String role;
	private Map<String,String> status;
	private boolean isListener;
	
	public User(String internalUserId, String externalUserId, String fullname, String role) {
		this.internalUserId = internalUserId;
		this.externalUserId = externalUserId;
		this.fullname = fullname;
		this.role = role;
		this.status = new ConcurrentHashMap<String, String>();
		this.isListener = false;
	}
	
	public String getInternalUserId() {
		return this.internalUserId;
	}
	public void setInternalUserId(String internalUserId) {
		this.internalUserId = internalUserId;
	}
	
	public String getExternalUserId(){
		return this.externalUserId;
	}
	
	public void setExternalUserId(String externalUserId){
		this.externalUserId = externalUserId;
	}
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public boolean isModerator() {
		return this.role.equalsIgnoreCase("MODERATOR");
	}
	
	public void setStatus(String key, String value){
		this.status.put(key, value);
	}
	public void removeStatus(String key){
		this.status.remove(key);
	}
	public Map<String,String> getStatus(){
		return this.status;
	}

	public boolean isListener() {
		return isListener;
	}

	public void setIsListener(boolean isListener) {
		this.isListener = isListener;
	}

	public boolean isPresenter() {
		String isPresenter = this.status.get("presenter");
		if (isPresenter != null) {
			return isPresenter.equalsIgnoreCase("true");
		}
		return false;
	}

	public boolean hasStream() {
		String hasStream = this.status.get("hasStream");
		if (hasStream != null) {
			// hasStream example: "false,stream=320x24038-1328716010847"
			String[] a = hasStream.split(",");
			return (a.length > 0) && (a[0].equalsIgnoreCase("true"));
		}
		return false;
	}

	public String getStreamName() {
		if (this.hasStream()) {
			// hasStream example: "true,stream=320x24038-1328716010847"
			String str = this.status.get("hasStream");
			int pos = str.indexOf("stream=");
			return str.substring(pos + 7, str.length());
		}
		return "";
	}
}
