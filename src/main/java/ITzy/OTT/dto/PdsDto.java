package ITzy.OTT.dto;

import java.io.Serializable;
/*
   create table pds(seq int auto_increment primary key, id varchar(50) not null,
				title varchar(200) not null, content varchar(4000) not null,
                filename varchar(50) not null, newfilename varchar(50) not null,
                readcount decimal(8) not null, downcount decimal(8) not null,
                regidate timestamp not null);
                
	alter table pds add foreign key(id) references member(id); 
 
 */
// Public Data System
public class PdsDto implements Serializable{
	private int seq;
	private String id;
	private String title;
	private String content;
	
	private String filename;			//원본파일명			abc.txt
	private String newfilename;			//업로드 파일명			4543464.txt
	
	private int readcount;
	private int downcount;
	
	private String regidate;
	
	public PdsDto() {
	}
	
	public PdsDto(String id, String title, String content, String filename) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
	}
	

	public PdsDto(int seq, String id, String title, String content, String filename, String newfilename, int readcount,
			int downcount, String regidate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
		this.readcount = readcount;
		this.downcount = downcount;
		this.regidate = regidate;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getNewfilename() {
		return newfilename;
	}

	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getDowncount() {
		return downcount;
	}

	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}

	public String getRegidate() {
		return regidate;
	}

	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}

	@Override
	public String toString() {
		return "PdsDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", filename="
				+ filename + ", newfilename=" + newfilename + ", readcount=" + readcount + ", downcount=" + downcount + ", regdate=" + regidate + "]";
	}

}
