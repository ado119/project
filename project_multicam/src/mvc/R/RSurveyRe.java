package mvc.R;

import java.util.ArrayList;
import org.rosuda.JRI.*;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine.*;

public class RSurveyRe {

	static {
		System.loadLibrary("jri"); 
	}
	static Rengine rengine = new Rengine(null, false, null);
	
	public ArrayList<String> survey_re(String kind,int asset) {

		ArrayList<String> survey_list = new ArrayList<>();

		System.out.println(System.getProperty("java.library.path"));

		System.out.println(rengine.versionCheck());
		REXP ex1 = rengine.eval("getwd()"); 
		System.out.println(ex1);
		rengine.eval("setwd('C:/java_class/r_workspace')");
		REXP ex2 = rengine.eval("getwd()"); 
		System.out.println(ex2);
		rengine.eval("library(readxl)");
		rengine.eval("library(dplyr)");
		rengine.eval("rent_z_bakery = read_excel('rent_z_bakery.xlsx')");
		rengine.eval("rent_z_cafe = read_excel('rent_z_cafe.xlsx')");
		rengine.eval("rent_z_conv = read_excel('rent_z_conv.xlsx')");
		rengine.eval("rent_z_fast = read_excel('rent_z_fast.xlsx')");
		if(kind.equals("베이커리")) { 
			System.out.println("경우3-1");
			for(int i=1; i<=25; i++) { 
				rengine.eval("final_bakery = rent_z_bakery %>% filter(rent<=" + asset + ")");
				rengine.eval("final_bakery = final_bakery %>% arrange(desc(z_bakery))"); 
			}
			REXP top_bakery = rengine.eval("top_bakery = head(final_bakery,n=3)$gu");
			String[] result = top_bakery.asStringArray();
			for(String result_bakery: result) {
				survey_list.add(result_bakery);
			}
		}else if(kind.equals("카페")){ 
			System.out.println("경우3-2");
			for(int i=1;i<=25;i++){ 
				rengine.eval("final_cafe = rent_z_cafe %>% filter(rent<=" + asset + ")");
				rengine.eval("final_cafe = final_cafe %>% arrange(desc(z_caf))"); 
			} 
			REXP top_cafe = rengine.eval("top_cafe = head(final_cafe,n=3)$gu");
			System.out.println(top_cafe);
			String[] result = top_cafe.asStringArray();
			for(String result_cafe: result) {
				survey_list.add(result_cafe);
			}
		}else if(kind.equals("편의점")){ 
			System.out.println("경우3-3");
			for(int i=1;i<=25;i++) { 
				rengine.eval("final_conv = rent_z_conv %>% filter(rent<="+asset+")");
				rengine.eval("final_conv <- final_conv %>% arrange(desc(z_conv))");  
			} 
			REXP top_conv = rengine.eval("top_conv = head(final_conv,n=3)$gu");
			String[] result = top_conv.asStringArray();
			for(String result_conv: result) {
				survey_list.add(result_conv);
			}
		}else if(kind.equals("패스트푸드")) { 
			System.out.println("경우3-4");
			for(int i=1;i<=25;i++){ 
				rengine.eval("final_fast = rent_z_fast %>% filter(rent<="+asset+")");
				rengine.eval("final_fast <- final_fast %>% arrange(desc(z_fast))");  
			} 
			REXP top_fast = rengine.eval("top_fast = head(final_fast,n=3)$gu");
			String[] result = top_fast.asStringArray();
			for(String result_fast: result) {
				survey_list.add(result_fast);
			}
		}else { 
			survey_list.add("틀림");
			System.out.println("올바른 업종을 입력하세요!"); 
		}
		rengine.end();
		return survey_list;
	}
}
