package ITzy.OTT.service;

import java.util.List;

import ITzy.OTT.dto.BbsParam;
import ITzy.OTT.dto.PdsDto;

public interface PdsSerivce {
	public List<PdsDto> pdslist(BbsParam pds);
	int getAllPds(BbsParam pds);
	boolean uploadPds(PdsDto dto);
	void downcount(int seq);	
	PdsDto getPds(int seq);
	boolean updatePds(PdsDto dto);
	PdsDto deletePds(int seq);
}
