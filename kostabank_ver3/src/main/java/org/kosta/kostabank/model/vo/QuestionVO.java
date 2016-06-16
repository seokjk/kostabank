package org.kosta.kostabank.model.vo;

public class QuestionVO {
	private int no;
	private String title;
	private String contents;
	private int hits;
	private String section;
	public QuestionVO(int no, String title, String contents, int hits,
			String section) {
		super();
		this.no = no;
		this.title = title;
		this.contents = contents;
		this.hits = hits;
		this.section = section;
	}
	public QuestionVO(String title, String contents, int hits, String section) {
		super();
		this.title = title;
		this.contents = contents;
		this.hits = hits;
		this.section = section;
	}
	
	public QuestionVO(int no, String title, int hits, String section) {
		super();
		this.no = no;
		this.title = title;
		this.hits = hits;
		this.section = section;
	}
	public QuestionVO(int no, String title, int hits) {
		super();
		this.no = no;
		this.title = title;
		this.hits = hits;
	}
	public QuestionVO() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	@Override
	public String toString() {
		return "QuestionVO [no=" + no + ", title=" + title + ", contents="
				+ contents + ", hits=" + hits + ", section=" + section + "]";
	}
	
	
}
