package ITzy.OTT.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ITzy.OTT.dto.BbsParam;
import ITzy.OTT.dto.PdsDto;
import ITzy.OTT.service.PdsSerivce;
import ITzy.OTT.util.PdsUtil;

@Controller
public class PdsController {
		@Autowired
		PdsSerivce service;
		
		@GetMapping(value = "pdslist.do")
		public String pdslist(BbsParam param, Model model) {

			// 글의 시작과 끝
			int pn = param.getPageNumber();
			int start = 1 + pn * 10; 
			int end = (pn + 1) * 10;
			param.setStart(start);
			param.setEnd(end);
			List<PdsDto> list = service.pdslist(param);

			int len = service.getAllPds(param);
			int pagePds = len / 10;
			if ((len % 10) > 0) {
				pagePds = pagePds + 1;
			}

			if (param.getChoice() == null || param.getChoice().equals("") || param.getSearch() == null
					|| param.getSearch().equals("")) {
				param.setChoice("검색");
				param.setSearch("");
			}

			model.addAttribute("pdslist", list); 
			model.addAttribute("pagePds", pagePds); // 총 페이지 수
			model.addAttribute("pageNumber", param.getPageNumber()); // 현재 페이지
			model.addAttribute("choice", param.getChoice());
			model.addAttribute("search", param.getSearch());

			return "pdslist";
		}
		
		
		
		@GetMapping(value = "pdswrite.do")
		public String pdswrite() {
			return "pdswrite";
		}
		
		
		
		
		@PostMapping(value = "pdsupload.do")
		public String pdsupload(PdsDto dto,
								@RequestParam(value = "fileload", required = false)
								MultipartFile fileload,
								HttpServletRequest req) {
			// getting original file name
			String filename = fileload.getOriginalFilename();
			
			// setting file name to DTO
			dto.setFilename(filename);
			
			// configuration of the path of upload to server of folder
				// server
			String fupload = req.getServletContext().getRealPath("/upload");
			
				// folder
//			String fupload = "c:\\temp";
			
			System.out.println("fupload:" +fupload);
			
			// 충돌되지 않는 파일명으로 변환
			String newfilename = PdsUtil.getNewFileName(filename);
			// setting the converted name to DTO
			dto.setNewfilename(newfilename);
			
			File file = new File(fupload + "/" + newfilename);
			try {
				// 실제로 파일 생성 + 기입 = 업로드
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());				
				// db에 저장
				service.uploadPds(dto);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			return "redirect:/pdslist.do";
		}
		
		
		
		
		@PostMapping(value = "pfiledownload.do")
		public String filedownload(int seq, String filename, String newfilename,
						Model model, HttpServletRequest req) {
			// 경로
			// server
						String fupload = req.getServletContext().getRealPath("/upload");
						
			// folder
//						String fupload = "c:\\temp";
						
						
						
			
			// 다운로드 받을 파일
			File downloadFile = new File(fupload + "/" + newfilename);
			
			model.addAttribute("downloadFile", downloadFile);
			model.addAttribute("filename", filename);
			model.addAttribute("seq", seq);					// 다운로드 카운트 증가
			
			return "DownloadView";
		}
		
		
		
		
		@GetMapping(value = "pdsdetail.do")
		public String pdsdetail(Model model, int seq) {
			PdsDto pds = service.getPds(seq);
			model.addAttribute("pds", pds);
			
			return "pdsdetail";
		}
		
		
		
		@GetMapping(value = "pdsupdate.do")
		public String pdsupdate(Model model, int seq) {
			PdsDto dto = service.getPds(seq);
			model.addAttribute("dto", dto);
			
			return "pdsupdate";
		}
		
		@PostMapping(value = "pdsupdateAf.do")
		public String pdsupdateAf(Model model,PdsDto dto, 
								@RequestParam(value = "fileload", required = false)
								MultipartFile fileload,
								HttpServletRequest req) {				
			String filename = fileload.getOriginalFilename();	
			
			if (filename != null && !filename.equals("")) {		//파일 변경 시
				String newfilename = PdsUtil.getNewFileName(filename);
				dto.setFilename(filename);
				dto.setNewfilename(newfilename);
				
				
				String fupload = req.getServletContext().getRealPath("/upload");			
				File file = new File(fupload + "/" + newfilename);					 
				try {// 새로운 파일로 업로드
					FileUtils.writeByteArrayToFile(file, fileload.getBytes());		
					// db에 갱신
					service.updatePds(dto);				
				} catch (IOException e) {			
					e.printStackTrace();
				}
				
			}else {												// 파일 변경 없을 시
				service.updatePds(dto);
			}
			return "redirect:/pdsdetail.do?seq=" + dto.getSeq();
		}
			
		
		@GetMapping(value = "pdsdeleteAf.do")
		public String pdsdelete(Model model, int seq) {
			PdsDto dto = service.deletePds(seq);
			model.addAttribute("dto", dto);

			return "redirect:/pdslist.do";
		}
		
}