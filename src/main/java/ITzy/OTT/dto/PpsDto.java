package ITzy.OTT.dto;

import java.io.Serializable;

public class PpsDto implements Serializable{

	private int seq;		// sequence 글번호
	private String id;
	
	private String title;
	private String content;
	//private String pwd; //에러뿜
	
	private String filename;	// 원본 파일명		abc.txt
	private String newfilename;	// 업로드 파일명   4543464.txt
	
	private int love;
	private int filesize;
	private int hit;
	private String wip;
	
	private int readcount;
	private int downcount;
	private String regdate;
	
	private int del;
	private int ref;		
	private int step;		
	private int depth;
	
	public PpsDto() {
		
	}
	
	public PpsDto(String id, String title, String content, String filename) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
	}
	

	public PpsDto(int seq, String id, String title, String content, String filename, String newfilename, 
			int love, int filesize, int hit, String wip, int readcount, int downcount, String regdate, int del, int ref, int step, int depth) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
		this.love = love;
		this.filesize = filesize;
		this.hit = hit;
		this.wip = wip;
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

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public int getLove() {
		return love;
	}

	public void setLove(int love) {
		this.love = love;
	}
	
	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getWip() {
		return wip;
	}

	public void setWip(String wip) {
		this.wip = wip;
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

	public void setRegidate(String regdate) {
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
		return "PpsDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ","
				+ " filename=" + filename + ", newfilename=" + newfilename + ","
						+ " love=\" + love + \", filesize=" + filesize + ", hit=" + hit + ", wip=" + wip + ","
								+ " readcount=" + readcount + ", downcount=" + downcount + ", regdate=" + regdate + ""
										+ ", del=" + del + ", ref=" + ref + ", step=" + step + ", depth=" + depth + "]";
	}
	
	
	
}
