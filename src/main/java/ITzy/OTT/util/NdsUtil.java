package ITzy.OTT.util;

import java.util.Date;

public class NdsUtil {

	// 파일명 -> 변환 as system time
	
	// myfile.txt -> 342456232.txt
	
	public static String getNewFileName(String filename) {
		String newfilename = "";
		String fpost = "";
		
		if (filename.indexOf('.') >= 0) {						// 확장자명이 있음 ex) myfile.txt
			fpost = filename.substring(filename.indexOf('.'));	// 확장자명 : .txt
			newfilename = new Date().getTime() + fpost;			// 3424562323.txt	
		
		
		}else {													// 확자자명이 없음 ex) myfile
			newfilename = new Date().getTime() + ".back";		// 342462323.back
		}
		return newfilename;
	}
}
