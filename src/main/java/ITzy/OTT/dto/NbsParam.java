package ITzy.OTT.dto;

import java.io.Serializable;

public class NbsParam implements Serializable{
	private String nchoice;
	private String nsearch;
	private int npageNumber;
	
	
	private int start;
	private int end;
	
	public NbsParam() {
	}

	public NbsParam(String nchoice, String nsearch, int npageNumber, int start, int end) {
		super();
		this.nchoice = nchoice;
		this.nsearch = nsearch;
		this.npageNumber = npageNumber;
		this.start = start;
		this.end = end;
	}

	public String getNchoice() {
		return nchoice;
	}

	public void setNchoice(String nchoice) {
		this.nchoice = nchoice;
	}

	public String getNsearch() {
		return nsearch;
	}

	public void setNsearch(String nsearch) {
		this.nsearch = nsearch;
	}

	public int getNpageNumber() {
		return npageNumber;
	}

	public void setNpageNumber(int npageNumber) {
		this.npageNumber = npageNumber;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "BbsParam [nchoice=" + nchoice + ", nsearch=" + nsearch + ", npageNumber=" + npageNumber + ", start="
				+ start + ", end=" + end + "]";
	}

	
}
