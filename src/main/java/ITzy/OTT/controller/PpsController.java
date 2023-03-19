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

import ITzy.OTT.dto.PpsParam;
import ITzy.OTT.dto.PpsDto;
import ITzy.OTT.service.PpsService;
import ITzy.OTT.util.PpsUtil;

@Controller
public class PpsController {
		@Autowired
		//PdsService service;
		PpsService service;
		
		@GetMapping(value = "ppslist.do")
		public String ppslist(PpsParam param, Model model) {

			// 글의 시작과 끝
			int pn = param.getPageNumber();
			int start = 1 + pn * 10; 
			int end = (pn + 1) * 10;
			param.setStart(start);
			param.setEnd(end);
			List<PpsDto> list = service.ppslist(param);

			int len = service.getAllPps(param);
			int pagePps = len / 10;
			if ((len % 10) > 0) {
				pagePps = pagePps + 1;
			}

			if (param.getChoice() == null || param.getChoice().equals("") || param.getSearch() == null
					|| param.getSearch().equals("")) {
				param.setChoice("검색");
				param.setSearch("");
			}

			model.addAttribute("ppslist", list); 
			model.addAttribute("pagePps", pagePps); // 총 페이지 수
			model.addAttribute("pageNumber", param.getPageNumber()); // 현재 페이지
			model.addAttribute("choice", param.getChoice());
			model.addAttribute("search", param.getSearch());

			return "ppslist";
		}
		
		
		
		@GetMapping(value = "ppswrite.do")
		public String ppswrite() {
			return "ppswrite";
		}
		
		
		
		
		@PostMapping(value = "ppsupload.do")
		public String ppsupload(PpsDto dto,
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
			String newfilename = PpsUtil.getNewFileName(filename);
			// setting the converted name to DTO
			dto.setNewfilename(newfilename);
			
			File file = new File(fupload + "/" + newfilename);
			try {
				// 실제로 파일 생성 + 기입 = 업로드
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());				
				// db에 저장
				service.uploadPps(dto);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			return "redirect:/ppslist.do";
		}
		
		
		
		
		@PostMapping(value = "ppfiledownload.do") //나는 구별을 위한 p가 하나 더 붙는다
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
		
		
		
		
		@GetMapping(value = "ppsdetail.do")
		public String ppsdetail(Model model, int seq) {
			PpsDto pps = service.getPps(seq);
			model.addAttribute("pps", pps);
			
			return "ppsdetail";
		}
		
		
		
		@GetMapping(value = "ppsupdate.do")
		public String ppsupdate(Model model, int seq) {
			PpsDto dto = service.getPps(seq);
			model.addAttribute("dto", dto);
			
			return "ppsupdate";
		}
		
		@PostMapping(value = "ppsupdateAf.do")
		public String ppsupdateAf(Model model,PpsDto dto, 
								@RequestParam(value = "fileload", required = false)
								MultipartFile fileload,
								HttpServletRequest req) {				
			String filename = fileload.getOriginalFilename();	
			
			if (filename != null && !filename.equals("")) {		//파일 변경 시
				String newfilename = PpsUtil.getNewFileName(filename);
				dto.setFilename(filename);
				dto.setNewfilename(newfilename);
				
				
				String fupload = req.getServletContext().getRealPath("/upload");			
				File file = new File(fupload + "/" + newfilename);					 
				try {// 새로운 파일로 업로드
					FileUtils.writeByteArrayToFile(file, fileload.getBytes());		
					// db에 갱신
					service.updatePps(dto);				
				} catch (IOException e) {			
					e.printStackTrace();
				}
				
			}else {												// 파일 변경 없을 시
				service.updatePps(dto);
			}
			return "redirect:/ppsdetail.do?seq=" + dto.getSeq();
		}
			
		
		@GetMapping(value = "ppsdeleteAf.do")
		public String ppsdelete(Model model, int seq) {
			PpsDto dto = service.deletePps(seq);
			model.addAttribute("dto", dto);

			return "redirect:/ppslist.do";
		}
		
}