package ITzy.OTT.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import ITzy.OTT.service.PdsSerivce;

public class DownloadView extends AbstractView{
	
	@Autowired
	PdsSerivce service;
//	NbsService service;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadView renderMergedOutputModel");
		
		File downloadFile = (File)model.get("downloadFile");
		String filename = (String)model.get("filename");
		int seq = (int)model.get("seq");
		// For 한글명 파일
		filename = URLEncoder.encode(filename, "utf-8");
		
		response.setContentType(this.getContentType());
		response.setContentLength((int)downloadFile.length());
		
		// 원본파일명으로 변환
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
		// 2진수로
		response.setHeader("Content-Transfer-Encoding", "binary;");
		// 
		response.setHeader("Content-Length", "" + downloadFile.length());
		// 저장X
		response.setHeader("Pragma", "no-cache;");
		// 기한설정
		response.setHeader("Expires", "-1;");
		
		
		FileInputStream fis = new FileInputStream(downloadFile);
		OutputStream os = response.getOutputStream();
		// 실제 데이터 기입
		FileCopyUtils.copy(fis, os);
		// download count증가
		service.downcount(seq);
		
		
		if (fis != null) {
			fis.close();
		}
	}

}
