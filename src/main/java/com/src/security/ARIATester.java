package com.src.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.Date;

/*
 *  @ ARIA TEST
 */
public class ARIATester
{
	public static void main(String[] args)
	{
		String msg = "서울시 서초구 삼성화재 빌딩 123";
		System.out.println("문자 : " + msg + " (" + msg.length() + " byte)" );
		System.out.println("---------------------------");

		try {
			//ariaEncrypt
			Date start1 = new Date();
			String encMsg = ARIAUtil.ariaEncrypt(msg);
			System.out.println("암호화 : " + encMsg);
			Date fin1 = new Date();

			//ariaDecrypt
			Date start2 = new Date();
			String decMsg = ARIAUtil.ariaDecrypt(encMsg);
			System.out.println("복호화 : " + decMsg);
			Date fin2 = new Date();

			System.out.println("---------------------------");
			float lapse = (float)(fin1.getTime()-start1.getTime())/1000;
			System.out.print("암호화 처리시간 : ");
			System.out.print(lapse);
			System.out.println(" sec.");
			lapse = (float)(fin2.getTime()-start2.getTime())/1000;
			System.out.print("복호화 처리시간 : ");
			System.out.print(lapse);
			System.out.println(" sec.");
			System.out.println("---------------------------");

			//ariaEncrypt
			//encMsg = ARIAUtil.ariaEncrypt(msg, "암호키") ;
			//System.out.println("enc:" + encMsg);
			//ariaDecrypt
			//decMsg = ARIAUtil.ariaDecrypt(encMsg,"암호키");
			//System.out.println("dec:" + decMsg);


		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


	}

}
