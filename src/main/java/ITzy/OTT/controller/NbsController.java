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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ITzy.OTT.dto.NbsParam;
import ITzy.OTT.dto.NbsComment;
import ITzy.OTT.dto.NbsDto;
import ITzy.OTT.service.NbsService;
import ITzy.OTT.util.NdsUtil;

@Controller
public class NbsController {
		@Autowired
		NbsService service;
		
		@GetMapping(value = "nbslist.do")
		public String nbslist(NbsParam nbs, Model model) {

			// 글의 시작과 끝
			int pn = nbs.getNpageNumber();
			int start = 1 + pn * 10; 
			int end = (pn + 1) * 10;
			nbs.setStart(start);
			nbs.setEnd(end);
			List<NbsDto> list = service.nbslist(nbs);

			int len = service.getAllNbs(nbs);
			int pageNbs = len / 10;
			if ((len % 10) > 0) {
				pageNbs = pageNbs + 1;
			}

			if (nbs.getNchoice() == null || nbs.getNchoice().equals("") || nbs.getNsearch() == null
					|| nbs.getNsearch().equals("")) {
				nbs.setNchoice("검색");
				nbs.setNsearch("");
			}

			model.addAttribute("nbslist", list); 
			model.addAttribute("pageNbs", pageNbs); // 총 페이지 수
			model.addAttribute("npageNumber", nbs.getNpageNumber()); // 현재 페이지
			model.addAttribute("nchoice", nbs.getNchoice());
			model.addAttribute("nsearch", nbs.getNsearch());

			return "nbslist";
		}
		
		
		
		@GetMapping(value = "nbswrite.do")
		public String nbswrite() {
			return "nbswrite";
		}
		
		
		
		
		@PostMapping(value = "nbsupload.do")
		public String nbsupload(NbsDto dto,
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
			String newfilename = NdsUtil.getNewFileName(filename);
			// setting the converted name to DTO
			dto.setNewfilename(newfilename);
			
			File file = new File(fupload + "/" + newfilename);
			try {
				// 실제로 파일 생성 + 기입 = 업로드
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());				
				// db에 저장
				service.uploadNbs(dto);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			return "redirect:/nbslist.do";
		}
		
		
		
		
		@PostMapping(value = "filedownLoad.do")
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
			
			return "downloadView";
		}
		
		
		
		
		@GetMapping(value = "nbsdetail.do")
		public String nbsdetail(Model model, int seq) {
			NbsDto nbs = service.getNbs(seq);
			model.addAttribute("nbs", nbs);
			
			return "nbsdetail";
		}
		
		
		
		@GetMapping(value = "nbsupdate.do")
		public String nbsupdate(Model model, int seq) {
			NbsDto dto = service.getNbs(seq);
			model.addAttribute("dto", dto);
			
			return "nbsupdate";
		}
		
		@PostMapping(value = "nbsupdateAf.do")
		public String nbsupdateAf(Model model,NbsDto dto, 
								@RequestParam(value = "fileload", required = false)
								MultipartFile fileload,
								HttpServletRequest req) {				
			String filename = fileload.getOriginalFilename();	
			
			if (filename != null && !filename.equals("")) {		//파일 변경 시
				String newfilename = NdsUtil.getNewFileName(filename);
				dto.setFilename(filename);
				dto.setNewfilename(newfilename);
				
				
				String fupload = req.getServletContext().getRealPath("/upload");			
				File file = new File(fupload + "/" + newfilename);					 
				try {// 새로운 파일로 업로드
					FileUtils.writeByteArrayToFile(file, fileload.getBytes());		
					// db에 갱신
					service.updateNbs(dto);				
				} catch (IOException e) {			
					e.printStackTrace();
				}
				
			}else {												// 파일 변경 없을 시
				service.updateNbs(dto);
			}
			return "redirect:/nbsdetail.do?seq=" + dto.getSeq();
		}
			
		
		@GetMapping(value = "nbsdeleteAf.do")
		public String nbsdelete(Model model, int seq) {
			NbsDto dto = service.deleteNbs(seq);
			model.addAttribute("dto", dto);

			return "redirect:/nbslist.do";
		}
		
		
		
		
		// TODO 답글
		@GetMapping(value = "nanswer.do")
		public String nanswer(Model model, int seq) {
			NbsDto dto = service.getNbs(seq);
			model.addAttribute("dto", dto);

			return "nanswer";
		}

		@PostMapping(value = "nanswerAf.do")
		public String nanswerAf(Model model, int seq, NbsDto dto) {
			dto.setSeq(seq);
			boolean isS = service.answerNbs(dto);
			String nanswer = "NBS_ANSWER_OK";
			if (isS == false) {
				nanswer = "NBS_ANSWER_NO";
			}
			model.addAttribute("nanswer", nanswer);

			return "message";
		}
		

		// TODO 댓글
		@PostMapping(value = "ncommentWriteAf.do")
		public String commentWriteAf(NbsComment bc) {
			boolean isS = service.commentWrite(bc);
			if (isS) {
				System.out.println("댓글작성 성공");
			} else {
				System.out.println("댓글작성 실패");
			}
			return "redirect:/nbsdetail.do?seq=" + bc.getSeq();
		}

		@ResponseBody
		@GetMapping(value = "ncommentList.do")
		public List<NbsComment> commentList(int seq) {
			List<NbsComment> list = service.commentList(seq);
			return list;
		}

		
	}

		

