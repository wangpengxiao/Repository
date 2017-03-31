package com.tydic.android.usp.common;

public abstract class Message{
	//列表简要显示的时候，显示的消息长度的最大长度
    private static final int PART_CONTENT_MAX_LENGTH = 30;
	public static final String BULLETIN = "bulletin";
	public static final String NOTICE = "notice";
	public static final String TIP = "tip";
	public static final int UNREAD = 0;
	public static final int READED = 1;
	public static final int UNDELETE = 0;
	public static final int DELETEED = 1;
	public static final int TOTOL = 0;
	public static final int TYPE_BULLETIN = 1;
	public static final int TYPE_NOTICE = 2;
	public static final int TYPE_TIP = 3;
	private int _id; //数据库ID
	private String messageId;
	private String content;
	private String updatetime;
	private String type;
	private boolean isRead;
	private boolean isDelete;
	private String userId;
	
	public Message(int id,String content,String messageid,String time,String userid,String type){
		this._id = id;
		this.content = content;
		this.messageId = messageid;
		this.updatetime = time;
		this.userId = userid;
		this.type = type;
	}
	
	public Message(String content,String messageid,String time,String userid,String type){
		this.content = content;
		this.messageId = messageid;
		this.updatetime = time;
		this.userId = userid;
		this.type = type;
	}
	
	public Message(){
		
	}
	
	public void set_id(int _id) {
		this._id = _id;
	}
	public int get_id() {
		return _id;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	
	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType(){
		return type;
	}
	
	/**
     * 返回 部分内容
     * 如果 content==null ,返回 ""(空字符串);
     * @return 
     */
	public String getPartContent(){
        String partContent = "";
        if(this.content!=null){
            partContent = (content.length()>PART_CONTENT_MAX_LENGTH ? (content.substring(0, PART_CONTENT_MAX_LENGTH)+"..."):(content));
        }
        return partContent;
    }

	
}
