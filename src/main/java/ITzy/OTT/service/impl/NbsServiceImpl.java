package ITzy.OTT.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ITzy.OTT.dao.NbsDao;
import ITzy.OTT.dto.NbsParam;
import ITzy.OTT.dto.NbsComment;
import ITzy.OTT.dto.NbsDto;
import ITzy.OTT.service.NbsService;

@Service
public class NbsServiceImpl implements NbsService{
	@Autowired
	NbsDao dao;

	@Override
	public List<NbsDto> nbslist(NbsParam nbs) {
		return dao.nbslist(nbs);
	}

	@Override
	public int getAllNbs(NbsParam nbs) {
		return dao.getAllNbs(nbs);
	}

	@Override
	public boolean uploadNbs(NbsDto dto) {
		int count = dao.uploadNbs(dto);
		return count>0?true:false;
	}

	@Override
	public void downcount(int seq) {
		dao.downcount(seq);		
	}

	@Override
	public NbsDto getNbs(int seq) {
		return dao.getNbs(seq);
	}

	@Override
	public boolean updateNbs(NbsDto dto) {
		int n = dao.updateNbs(dto); 
		return n>0?true:false;
	}

	@Override
	public NbsDto deleteNbs(int seq) {
		return dao.deleteNbs(seq);
	}

	@Override
	public boolean answerNbs(NbsDto dto) {
		dao.answerNbsUpdate(dto);
		int n = dao.answerNbsInsert(dto);
		return n>0?true:false;
	}

	@Override
	public boolean commentWrite(NbsComment bc) {
		int n = dao.commentWrite(bc);
		return n>0?true:false;
	}

	@Override
	public List<NbsComment> commentList(int seq) {
		return dao.commentList(seq);
	}

}
