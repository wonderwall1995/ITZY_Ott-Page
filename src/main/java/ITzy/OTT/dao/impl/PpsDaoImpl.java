package ITzy.OTT.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ITzy.OTT.dao.PpsDao;
import ITzy.OTT.dto.PpsParam;
import ITzy.OTT.dto.PpsDto;
@Repository
public class PpsDaoImpl implements PpsDao{
	@Autowired
	SqlSessionTemplate session;
	
	String ns = "Pps.";

	@Override
	public List<PpsDto> ppslist(PpsParam pps) {
		return session.selectList(ns + "ppslist", pps);
	}

	@Override
	public int uploadPps(PpsDto dto) {
		return session.insert(ns + "uploadPps", dto);
	}

	@Override
	public void downcount(int seq) {
		session.update(ns + "downcount", seq);
	}

	@Override
	public PpsDto getPps(int seq) {
		return session.selectOne(ns + "getPps", seq);
	}


	@Override
	public int getAllPps(PpsParam pps) {
		return session.selectOne(ns + "getAllPps", pps);
	}

	@Override
	public int updatePps(PpsDto dto) {
		return session.update(ns + "updatePps", dto);
	}

	@Override
	public PpsDto deletePps(int seq) {
		return session.selectOne(ns + "deletePps", seq);
	}

	
}
