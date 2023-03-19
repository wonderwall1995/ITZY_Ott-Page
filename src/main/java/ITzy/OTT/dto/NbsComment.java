package ITzy.OTT.dto;

import java.io.Serializable;

/*
	create table nbscomment(
		seq decimal(5) not null,
		id varchar(50),
		content varchar(1000) not null,
		regdate timestamp not null
	);

	alter table nbscomment
	add foreign key(id) references member(id);
*/
public class NbsComment implements Serializable{
	private int seq;
	private String id;
	private String content;
	private String regdate;
	
	public NbsComment() {
	}

	public NbsComment(int seq, String id, String content, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.content = content;
		this.regdate = regdate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getregdate() {
		return regdate;
	}

	public void setregdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "NbsComment [seq=" + seq + ", id=" + id + ", content=" + content + ", regdate=" + regdate + "]";
	}
	
	
	
}
