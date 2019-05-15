# Tave 회원들을 위한 챗봇 개발

- 챗봇 개발 툴은 오픈빌더를 활용하며
- 서버는 goormide를 활용하며 개발 언어는 파이썬이며 프레임 워크는 flask를 활용하였습니다.
- 한계점: goormide 무료 서버 이용으로 인한 로컬에서 서버를 계속 구동시켜야함

#### 개발 및 서버 연동된 플러스친구 주소

https://pf.kakao.com/_xfdxbtj

현재 오픈빌더를 통하여 Tave 동아리 회원들이 자주 질문을 하는 내용을 시나리오로 구현

오픈 api와 크롤링으로 데이터를 받아와 서버 연동

서버연동으로 구현한 기능

- 코스피
- 로또
- 주사위
- 환율
- 미세먼지
- 네이버 실시간 상위 20 검색어
- 더위 체감 지수
- 현재 기온


크롤링한 소스 간단 정리

``` python
@app.route("/kospi", methods=["POST"])
def kospi():
    ##kospi값 받아오는 함수

    url = 'https://finance.naver.com/sise/'
    ## 환율값을 받아올 url
    
    response = requests.get(url).text
	soup = BeautifulSoup(response, 'html.parser')
    ## 받아온 url 내용을 html 구조로 변경
    
    kospi = soup.select_one('#KOSPI_now').text
    ## kospi 값만 크롤링
    
    kospi = "현재 코스피 지수는 " + kospi + "입니다."
    
    ## 오픈빌더 연동시 답변 출력을 하기 위한 코드
    res = {
        "version": "2.0",
        "template": {
            "outputs": [
                {
                    "simpleText": {
                        "text": kospi
                    }
                }
            ]
        }
    }

    return jsonify(res)
```



Textrk 아닌 image 파일을 답변으로 보낼때 아래와 같은 방식으로 구현

``` python
    res = {
        "version": "2.0",
        "template": {
            "outputs": [
                {
                    "simpleImage": {
                        "imageUrl": DiceUrl,
                        "altText": "주사위입니다."
                    }
                }
            ]
        }
    }
```

