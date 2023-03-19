package ITzy.OTT.service;

import java.util.List;

import ITzy.OTT.dto.NbsParam;
import ITzy.OTT.dto.NbsComment;
import ITzy.OTT.dto.NbsDto;

public interface NbsService {
	public List<NbsDto> nbslist(NbsParam nbs);
	int getAllNbs(NbsParam nbs);
	boolean uploadNbs(NbsDto dto);
	void downcount(int seq);	
	NbsDto getNbs(int seq);
	boolean updateNbs(NbsDto dto);
	NbsDto deleteNbs(int seq);
	
	boolean answerNbs(NbsDto dto);
	boolean commentWrite(NbsComment bc);
	public List<NbsComment> commentList(int seq);
}
