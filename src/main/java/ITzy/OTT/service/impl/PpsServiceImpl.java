package ITzy.OTT.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ITzy.OTT.dao.PpsDao;
import ITzy.OTT.dto.PpsParam;
import ITzy.OTT.dto.PpsDto;
import ITzy.OTT.service.PpsService;

@Service
public class PpsServiceImpl implements PpsService{
	@Autowired
	//PpsDao dao;
	PpsDao dao;

	@Override
	public List<PpsDto> ppslist(PpsParam pps) {
		return dao.ppslist(pps);
	}

	@Override
	public boolean uploadPps(PpsDto dto) {
		int count = dao.uploadPps(dto);
		return count>0?true:false;
	}

	@Override
	public void downcount(int seq) {
		dao.downcount(seq);
	}

	@Override
	public PpsDto getPps(int seq) {
		return dao.getPps(seq);
	}

	@Override
	public int getAllPps(PpsParam pps) {
		return dao.getAllPps(pps);
	}

	@Override
	public boolean updatePps(PpsDto dto) {
		int n = dao.updatePps(dto); 
		return n>0?true:false;
	}

	@Override
	public PpsDto deletePps(int seq) {
		return dao.deletePps(seq);
	}
}
