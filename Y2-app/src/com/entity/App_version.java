package com.entity;

import java.util.Date;

public class App_version {
	private int id;
	private int appId;
	private String versionNo;
	private String versionInfo;
	private int publishStatus;
	private String downloadLink;
	private float versionSize;
	private int createdBy;
	private Date creationDate;
	private int modifyBy;
	private Date modifyDate;
	private String apKLocPath;
	private String apKFileName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public String getVersionInfo() {
		return versionInfo;
	}
	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}
	public int getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(int publishStatus) {
		this.publishStatus = publishStatus;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public float getVersionSize() {
		return versionSize;
	}
	public void setVersionSize(float versionSize) {
		this.versionSize = versionSize;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getApKLocPath() {
		return apKLocPath;
	}
	public void setApKLocPath(String apKLocPath) {
		this.apKLocPath = apKLocPath;
	}
	public String getApKFileName() {
		return apKFileName;
	}
	public void setApKFileName(String apKFileName) {
		this.apKFileName = apKFileName;
	}
}
