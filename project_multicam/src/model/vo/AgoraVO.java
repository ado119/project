package model.vo;

public class AgoraVO {
	private String id;
	private int agora_num;
	private String title;
	private String content;
	private String writedate;
	private int cnt;
	private int page_cnt;


	public int getPage_cnt() {
		return page_cnt;
	}
	public void setPage_cnt(int page_cnt) {
		this.page_cnt = page_cnt;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAgora_num() {
		return agora_num;
	}
	public void setAgora_num(int agora_num) {
		this.agora_num = agora_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
