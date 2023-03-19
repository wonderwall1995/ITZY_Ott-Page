package ITzy.OTT.service;

import java.util.List;

import ITzy.OTT.dao.PpsDao;
import ITzy.OTT.dto.PpsParam;
import ITzy.OTT.dto.PpsDto;

public interface PpsService {
	public List<PpsDto> ppslist(PpsParam pps);
	int getAllPps(PpsParam pps);
	boolean uploadPps(PpsDto dto);
	void downcount(int seq);	
	PpsDto getPps(int seq);
	boolean updatePps(PpsDto dto);
	PpsDto deletePps(int seq);
}
