package ITzy.OTT.dto;

import java.io.Serializable;

/*
	alter database itzydb default character set = utf8mb4;
	drop table nbs;

	create table if not exists nbs(
	seq int auto_increment primary key,
	id varchar(50) not null,
	title varchar(200) not null,
	content varchar(4000) not null,
    filename varchar(50) not null,
    newfilename varchar(50) not null,
    
	readcount decimal(8) not null,
	downcount decimal(8) not null,
    regdate timestamp not null,
    
    del decimal(1) not null,
    ref decimal(8) not null,
	step decimal(8) not null,
	depth decimal(8) not null);
    
    alter table nbs add foreign key (id) references member(id);
*/

public class NbsDto implements Serializable{
	
		private int seq;
		private String id;
		private String title;
		private String content;
		
		private String filename;			
		private String newfilename;			
		
		private int readcount;
		private int downcount;
		
		private String regdate;
		
		private int del;
		private int ref;		
		private int step;		
		private int depth;		
		
		public NbsDto() {
		}

		public NbsDto(String id, String title, String content, String filename) {
			super();
			this.id = id;
			this.title = title;
			this.content = content;
			this.filename = filename;
		}

		public NbsDto(int seq, String id, String title, String content, String filename, String newfilename,
				int readcount, int downcount, String regdate, int del, int ref, int step, int depth) {
			super();
			this.seq = seq;
			this.id = id;
			this.title = title;
			this.content = content;
			this.filename = filename;
			this.newfilename = newfilename;
			this.readcount = readcount;
			this.downcount = downcount;
			this.regdate = regdate;
			this.del = del;
			this.ref = ref;
			this.step = step;
			this.depth = depth;
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

		public String getRegdate() {
			return regdate;
		}

		public void setRegdate(String regdate) {
			this.regdate = regdate;
		}

		public int getDel() {
			return del;
		}

		public void setDel(int del) {
			this.del = del;
		}

		public int getRef() {
			return ref;
		}

		public void setRef(int ref) {
			this.ref = ref;
		}

		public int getStep() {
			return step;
		}

		public void setStep(int step) {
			this.step = step;
		}

		public int getDepth() {
			return depth;
		}

		public void setDepth(int depth) {
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "NbsDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", filename="
					+ filename + ", newfilename=" + newfilename + ", readcount=" + readcount + ", downcount="
					+ downcount + ", regdate=" + regdate + ", del=" + del + ", ref=" + ref + ", step=" + step
					+ ", depth=" + depth + "]";
		}
		
}