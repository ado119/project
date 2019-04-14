'''
Created on 2019. 2. 18.

@author: student
'''

import re
import time
import urllib.request
from bs4 import BeautifulSoup

from selenium import webdriver
chromedriver_path = "C:\java_class\workspace\samplepy\chromedriver.exe"

driver = webdriver.Chrome(chromedriver_path)

url = "https://store.naver.com/restaurants/list?sortingOrder=precision"
url_menu = "&query="
url_page = "&page="
page = 1

item = ['감자탕','돼지갈비','라면','짜장면','보쌈','김치찌개','회','생선구이','샤브샤브','비빔밥',
'떡볶이','돈가스','알탕','곱창','막창','갈비탕','삼겹살','게장','짬뽕','스테이크',
'부대찌개','수제비','해물찜','족발','냉면','아귀찜','피자','순댓국','카레','치킨',
'닭발','꽃게탕','청국장','탕수육','닭볶음탕','닭갈비','메밀국수','문어','콩국수','쌀국수',
'오리고기','양꼬치','매운탕','불고기','전','갈치','추어탕','라멘','찜닭','순대볶음',
'설렁탕','만두','콩나물국밥','곰탕','두부김치','백숙','쌈밥','칼국수','잔치국수','코다리',
'오징어볶음','조개찜','삼계탕','더덕','연어','우동','육회','황태','생태','동태',
'굴','과메기','햄버거','꼬막','김치찜','등갈비','선지','딤섬','파스타',
'골뱅이','초밥','쫄면','미역국','낙지볶음','바비큐','꼼장어','도토리','고로케','마카롱',
'아이스크림','컵밥','새우','전어','굴비','가츠동','해물탕','떡국','홍어','똠양꿍',
'오코노미야끼','스키야키','다코야끼','월남쌈','빙수','학센','타코','김밥','염소','김치볶음밥',
'랍스타','장어구이','장어탕','대게','양장피','마라샹궈','훠궈','떡갈비','오므라이스','샐러드',
'조개구이','백반','순두부찌개','쭈꾸미','고기국수','제육볶음','핫도그','소갈비','초계국수','홍합',
'도넛','와플','야끼니꾸','닭꼬치','펍','된장찌개','차돌박이','밥버거','닭강정','탕수육',
'쿼사디아','초콜릿','커피','타르트','호두과자','생과일주스','토스트','샌드위치','케이크','소바']
# 음식 리스트 

item_count = []
# 음식 리뷰 수 리스트

for menu_count in range(1, len(item)+1):
    sum=0
    url_menu_item = item[menu_count-1]
    print(url_menu_item)
    url_menu_no = url + url_menu + url_menu_item
    
    for page in range(1, 21):
            
        current_url = url_menu_no + url_page + str(page)
        
        driver.get(current_url)
        time.sleep(3)
        
        source = driver.page_source
        
        result_bs = BeautifulSoup(source, "html.parser")
        result = result_bs.find_all("div", class_ = "etc_area")
        
        for i in result:
            if i.find('span') != None:
                if '리뷰 ' in i.find('span').text :        
                    review_all = re.sub("리뷰 ", "", i.find('span').text)
                    review_all = re.sub(",", "", review_all)
                    review_count = int(review_all)
                    sum = sum + review_count
            
    item_count.append(sum)   
print("---------------------------------------")
print(item)
print(item_count)
print("완료")

driver.close()