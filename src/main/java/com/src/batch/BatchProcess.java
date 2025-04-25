package com.src.batch;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.src.auto.AutoService;
import com.src.vo.auto_send;
import com.src.vo.auto_send_log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableScheduling
public class BatchProcess {

	@Autowired
	private AutoService autoService;

	//private static final Logger logger = LoggerFactory.getLogger(BatchProcess.class);

	@Scheduled(fixedDelay = 60000L)
	public void auto_data_check() throws Exception
	{
		log.debug("[BATCH] batch process start ~~~");

		String day = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-E");
		Date date = new Date();
		String time = dateFormat.format(date);
		String[] YMDHMS = time.split("-");
		Date nDate = dateFormat.parse(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);

		int dayNum = cal.get(7);
		log.debug("=================--->> dayNum     : " + dayNum);

		String YYYY = YMDHMS[0];
		String MONT = YMDHMS[1];
		String DDDD = YMDHMS[2];
		String HHHH = YMDHMS[3];
		String MINU = YMDHMS[4];
		String SSSS = YMDHMS[5];

		switch (dayNum)
		{
		case 1: day = "일"; break;
		case 2: day = "월"; break;
		case 3: day = "화"; break;
		case 4: day = "수"; break;
		case 5: day = "목"; break;
		case 6: day = "금"; break;
		case 7: day = "토"; break;
		}
		String php_week_num = week_number(dayNum);
		String date_now = String.valueOf(YYYY) + MONT + DDDD + HHHH + MINU + SSSS;
		log.debug("=================--->> time     : " + time);
		log.debug("*****************--->> date_now : " + date_now);
		log.debug("*****************--->> week     : " + day + "( " + php_week_num + " )");

		auto_send auto = new auto_send();

		auto_send_log logs = new auto_send_log();
		auto.setAst_fd_03(DDDD);
		auto.setAst_fd_04(php_week_num);
		auto.setAst_fd_05(String.valueOf(HHHH) + MINU);
		auto.setDate_now(date_now);

		List<auto_send> list = this.autoService.auto_check(auto);

		// Init
		int    CALL_RES   = 0;
		int    ast_num    = 0;;
		int    ast_siteNo = 0;
		String ast_part    ; //
		String ast_fd_00   ; //
		String ast_fd_01   ; //
		String ast_fd_02   ; //
		String ast_fd_03   ; //
		String ast_fd_04   ; //
		String ast_fd_05   ; //
		String ast_fd_06   ; //
		String ast_fd_07   ; //
		String ast_fd_08   ; //
		String ast_fd_09   ; //
		String ast_fd_10   ; //
		String ast_fd_11   ; //
		String ast_fd_12   ; //
		String ast_create_d; //

		for (int i = 0; i < list.size(); i++)
		{
			ast_num      = list.get(i).getAst_num();
			ast_siteNo   = list.get(i).getAst_siteNo();

			ast_part     = list.get(i).getAst_part()    ; // [_] 예비
			ast_fd_00    = list.get(i).getAst_fd_00()   ; // [_] 취소여부
			ast_fd_01    = list.get(i).getAst_fd_01()   ; // [_] 실행완료여부
			ast_fd_02    = list.get(i).getAst_fd_02()   ; // [_] 실행시작일시
			ast_fd_03    = list.get(i).getAst_fd_03()   ; // [_] 반복실행매월일
			ast_fd_04    = list.get(i).getAst_fd_04()   ; // [_] 반복실행매주요일
			ast_fd_05    = list.get(i).getAst_fd_05()   ; // [_] 반복실행시분초
			ast_fd_06    = list.get(i).getAst_fd_06()   ; // [_] 제목(설명)
			ast_fd_07    = list.get(i).getAst_fd_07()   ; // [_] 실행프로그램
			ast_fd_08    = list.get(i).getAst_fd_08()   ; // [P] 넘겨줄 파리미터 값 ***
			ast_fd_09    = list.get(i).getAst_fd_09()   ; // [_] 생성위치 ***
			ast_fd_10    = list.get(i).getAst_fd_10()   ; // [_] 예비
			ast_fd_11    = list.get(i).getAst_fd_11()   ; // [_] 예비 ***
			ast_fd_12    = list.get(i).getAst_fd_12()   ; // [_] 예비
			ast_create_d = list.get(i).getAst_create_d(); // [_] 생성일

			log.debug("CALL_NUM    : " + ast_num);
			log.debug("CALL_STATUS : " + ast_fd_01);
			log.debug("CALL_URL    : " + ast_fd_11);
			log.debug("CALL_FILE   : " + ast_fd_08);
			log.debug("CALL_PARAM  : " + ast_fd_09);

			if ("A".equals(ast_fd_01) && ("".equals(ast_fd_02) || "".equals(ast_fd_05) || "".equals(ast_fd_06))) {
				log.debug("ast_fd_01 = A,  ast_fd_00 : Y .... !!!!!! ");
				auto.setAst_fd_00("Y");
				CALL_RES = 200;
			} else {
				CALL_RES = url_called(ast_fd_11, ast_fd_08, ast_fd_09);
				auto.setAst_fd_01("Y");
			}

			// 성공인 경우
			if (CALL_RES == 200) {
				if ("N".equals(ast_fd_01)) {
					log.debug("success (200) & status (N) : => auto_update");
					auto.setAst_num(ast_num);
					this.autoService.auto_update(auto);
				}
				logs.setAst_num(ast_num);
				logs.setAst_siteNo(ast_siteNo);
				logs.setAst_part(ast_part);
				logs.setAst_fd_00(ast_fd_00);
				logs.setAst_fd_01(ast_fd_01);
				logs.setAst_fd_02(ast_fd_02);
				logs.setAst_fd_03(ast_fd_03);
				logs.setAst_fd_04(ast_fd_04);
				logs.setAst_fd_05(ast_fd_05);
				logs.setAst_fd_06(ast_fd_06);
				logs.setAst_fd_07(ast_fd_07);
				logs.setAst_fd_08(ast_fd_08);
				logs.setAst_fd_09(ast_fd_09);
				logs.setAst_fd_10(ast_fd_10);
				logs.setAst_fd_11(ast_fd_11);
				logs.setAst_fd_12(ast_fd_12);
				logs.setAst_create_u("+system");
				logs.setAst_create_d(ast_create_d);
				this.autoService.auto_log_insert(logs);
			}
		}
		log.debug("################[BATCH] batch process end ~~~");
	}

	public int url_called(String CALL_URL, String CALL_FILE, String CALL_PARAM) throws IOException
	{
		log.debug("[BATCH] URI_Call ~~~");
		String url = String.valueOf(CALL_URL) + "/" + CALL_FILE;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection)obj.openConnection();
		log.debug("access url : " + url + "?" + CALL_PARAM);
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(CALL_PARAM);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		log.debug("request to URL  : " + url);
		log.debug("Post parameters : " + CALL_PARAM);
		log.debug("Response Code   : " + responseCode + "\n\n");
		if (responseCode == 200)
			log.debug("[BATCH] process complete ~~~");
		return responseCode;
	}

	public String week_number(int num) throws Exception {
		log.debug("[BATCH] Week_Number Call ~~~");
		int res = num - 1;
		return String.valueOf(res);
	}
}
