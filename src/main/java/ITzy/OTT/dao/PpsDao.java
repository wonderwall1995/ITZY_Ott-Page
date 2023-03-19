package ITzy.OTT.dao;

import java.util.List;

import ITzy.OTT.dto.PpsParam;
import ITzy.OTT.dto.PpsDto;

public interface PpsDao {
	List<PpsDto> ppslist(PpsParam pps);
	int getAllPps(PpsParam pps);
	int uploadPps(PpsDto dto);
	void downcount(int seq);
	PpsDto getPps(int seq);
	int updatePps(PpsDto dto);
	PpsDto deletePps(int seq);
}
