package properties;
import java.net.URLEncoder;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import org.jsoup.Jsoup;
	import org.jsoup.nodes.Document;
	import org.jsoup.nodes.Element;
	import org.jsoup.select.Elements;

import dao.STR_DAO;


	public class Search_Keyword{
		
		
		public String[] Search(String category, String keyword) // 네이버 페이지에서 category로 검색한 결과를 긁어오는 함수
		{
			System.out.println("searcy_keyword");
			String[] best = new String[3];
			List<String> dic = null;
			List<Integer> total_count = new ArrayList<>();
			
			STR_DAO dao = new STR_DAO();
			System.out.println(category);
			dic = dao.searchDB(category);
			
			
			String str = ""; // 검색결과 읽어온 스트링값을 담기 위한 변수
			try {
				String utf8 = URLEncoder.encode(keyword, "UTF-8");// 쿼리문에들어갈 한글인코딩
				String url = "";
					url = "http://search.naver.com/search.naver?" + "where=nexearch&query=" + utf8
			                  + "&display=10&start=1&target=webkr&sm=top_hty&fbm=1&ie=utf8&display=10&start=1&target=webkr";
					Document doc = Jsoup.connect(url)
							.header( // 헤더
									"Accept",
									"image/gif, image/xxbitmap, image/jpeg, image/pjpeg,application/xshockwaveflash, application/vnd.msexcel,application/vnd.mspowerpoint, application/msword, */*").get();
					Elements links = doc.getElementsByTag("a"); // a태그 모두
					Elements links1 = doc.getElementsByTag("dd"); // dd태그 모두w
					Elements links_Ptag = doc.getElementsByTag("p");
					String temp = "";
					for(Element ptag:links_Ptag){

						temp += links_Ptag.text();						
					}
					if(temp.contains("검색결과가 없")){
						System.out.println("검색겨로가");
						return null;
					}
					
					for (Element link : links) {
						str += link.text(); // a태그에 텍스트
					}
					for (Element link : links1) {
						str += link.text(); // dd태그에 텍스트
					}
//					for(Element link : links)
//					{
//						str = str + "," + link.text().replaceAll("[^\uAC00-\uD7AF\u1100-\u11FF\u3130-\u318F]", "");
//					}
//					str = str.replaceAll(" ", "");
					int count=0;
					for (int j = 0; j < dic.size(); j++) {
						if(dic.get(j).equals(keyword)){
							dic.remove(j);
							continue;
						}
						Pattern pattern = Pattern.compile(dic.get(j)); //오라클에서 받아온 단어 나중에 dic.... 변경
						Matcher matcher = pattern.matcher(str); //검색결과
						while (matcher.find()) {
							count++; //검색결과가 오라클에서 받아온 단어와 일치하면 count증가
						}
						total_count.add(count);
						//System.out.println("[" + resSearch + "] 의 " + dic.get(j) + "의 개수 : " + count);
						count = 0;
					}
		
//					for (int i = 0; i < dic.size(); i++) {
//						int reee = total_count.get(i);
//						//   System.out.println(dic.get(i)+"의 갯수 : " +reee);
//		
//		
//					}
					int tmp = 0;
					String tmp_str = "";
		
					for(int index = 0; index < total_count.size(); index++) 
					{
						for (int j = 0; j < total_count.size() - 1; j++) 
						{
							count++;
							if(total_count.get(j) < total_count.get(j+1)) {
		
								tmp = total_count.get(j);
								total_count.set(j, total_count.get(j+1));
								total_count.set(j+1, tmp);
		
								tmp_str = dic.get(j);
								dic.set(j, dic.get(j+1));
								dic.set(j+1, tmp_str);
							}
							}
					}
		
					int best3 = best.length;
					System.out.println("twtewt");
				for(int index = 1 ; index<=best3; index++){
					best[index] = dic.get(index);
					System.out.println(best[index]+"          for문");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return best; // 결과값 반환
		}



}
