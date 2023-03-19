package ITzy.OTT.dao;

import java.util.List;

import ITzy.OTT.dto.NbsParam;
import ITzy.OTT.dto.NbsComment;
import ITzy.OTT.dto.NbsDto;

public interface NbsDao {
	List<NbsDto> nbslist(NbsParam nbs);
	int getAllNbs(NbsParam nbs);
	int uploadNbs(NbsDto dto);
	void downcount(int seq);
	NbsDto getNbs(int seq);
	int updateNbs(NbsDto dto);
	NbsDto deleteNbs(int seq);
	
	//TODO 답글
	int answerNbsUpdate(NbsDto dto);
	int answerNbsInsert(NbsDto dto);
	
	//TODO 댓글
	int commentWrite(NbsComment bc);
	List<NbsComment> commentList(int seq);


}
