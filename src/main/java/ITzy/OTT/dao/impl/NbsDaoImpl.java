package ITzy.OTT.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ITzy.OTT.dto.NbsComment;
import ITzy.OTT.dao.NbsDao;
import ITzy.OTT.dto.NbsParam;
import ITzy.OTT.dto.NbsDto;

@Repository
public class NbsDaoImpl implements NbsDao{
	@Autowired
	SqlSessionTemplate session;
	
	String ns = "Nbs.";

	@Override
	public List<NbsDto> nbslist(NbsParam nbs) {
		return session.selectList(ns + "nbslist", nbs);
	}

	@Override
	public int uploadNbs(NbsDto dto) {
		return session.insert(ns + "uploadNbs", dto);
	}

	@Override
	public void downcount(int seq) {
		session.update(ns + "downcount", seq);
	}

	@Override
	public NbsDto getNbs(int seq) {
		return session.selectOne(ns + "getNbs", seq);
	}


	@Override
	public int getAllNbs(NbsParam nbs) {
		return session.selectOne(ns + "getAllNbs", nbs);
	}

	@Override
	public int updateNbs(NbsDto dto) {
		return session.update(ns + "updateNbs", dto);
	}

	@Override
	public NbsDto deleteNbs(int seq) {
		return session.selectOne(ns + "deleteNbs", seq);
	}

	@Override
	public int answerNbsUpdate(NbsDto dto) {
		return session.update(ns + "answerNbsUpdate", dto);
	}

	@Override
	public int answerNbsInsert(NbsDto dto) {
		return session.insert(ns + "answerNbsInsert", dto);
	}

	@Override
	public int commentWrite(NbsComment bc) {
		return session.insert(ns + "commentWrite", bc);
	}

	@Override
	public List<NbsComment> commentList(int seq) {
		return session.selectList(ns + "commentList", seq);
	}

	
}
