#엑셀 데이터 받아오기
library(readxl)
getwd()
setwd("C:/Users/student/Desktop/프로젝트_데이터")


gucode<-read_excel("gucode.xlsx")
gucode.win <- gucode
names(gucode.win) <- c("order","id","kor","eng","ESRI_PK","x","y")
gucode.win$id <- sapply(gucode.win$id,as.character)


bakery_gu <- read_excel("map_bakery.xlsx")
bakery_gu$id <- sapply(bakery_gu$id, as.character)


#서울특별시에 해당하는 곳 추출
install.packages("stringi")
install.packages("devtools")
install.packages("ggplot2")
library(rethinking)
devtools::install_github("cardiomoon/Kormaps",force=TRUE)
devtools::install_github("rmcelreath/rethinking", force = TRUE)
require(Kormaps)
require(tmap)

korea.seoul <- subset(korpopmap2, korpopmap2@data$code.1 %in% c("11010","11020","11030","11040","11050","11060","11070","11080","11090","11100","11110","11120","11130","11140","11150","11160","11170","11180","11190","11200","11210","11220","11230","11240","11250"))

#plot(korea.seoul)


#ggplot 시각화위해 데이터프레임 변환
library(ggplot2)
korea.shp.f <- fortify(korea.seoul, region="region")



#서울시 지도와 병합

merge_bakery <- merge(korea.shp.f,bakery_gu,by="id",all=TRUE)
merge_bakery

library(ggiraphExtra)
library(ggplot2)
install.packages("RColorBrewer")
library(RColorBrewer)

display.brewer.all(n=10, exact.n=FALSE)

bmap <- ggChoropleth(data = merge_bakery, palette="RdPu" ,
                     aes(fill=점포수, map_id=id, tooltip=kor), title="베이커리 점포수 현황",map=korea.shp.f, interactive=T, color="black") 

bmap



#######cafe

cafe_gu <- read_excel("map_cafe.xlsx")
cafe_gu$id <- sapply(cafe_gu$id, as.character)

merge_cafe <- merge(korea.shp.f,cafe_gu,by="id",all=TRUE)
#merge_cafe

library(ggiraphExtra)
library(ggplot2)
#install.packages("RColorBrewer")
library(RColorBrewer)

display.brewer.all(n=10, exact.n=FALSE)

cmap <- ggChoropleth(data = merge_cafe, palette="YlGn" ,
                     aes(fill=점포수, map_id=id, tooltip=kor), title="카페 점포수 현황",map=korea.shp.f, interactive=T, color="black") 

cmap


########convenience

convenience_gu <- read_excel("map_convenience.xlsx")
convenience_gu$id <- sapply(convenience_gu$id, as.character)

merge_convenience <- merge(korea.shp.f,convenience_gu,by="id",all=TRUE)
#merge_cafe

library(ggiraphExtra)
library(ggplot2)
#install.packages("RColorBrewer")
library(RColorBrewer)

display.brewer.all(n=10, exact.n=FALSE)

conmap <- ggChoropleth(data = merge_convenience, palette="PuBu" ,
                     aes(fill=점포수, map_id=id, tooltip=kor), title="편의점 점포수 현황",map=korea.shp.f, interactive=T, color="black") 

conmap


#############fastfood


fastfood_gu <- read_excel("map_fastfood.xlsx")
fastfood_gu$id <- sapply(fastfood_gu$id, as.character)

merge_fastfood <- merge(korea.shp.f,fastfood_gu,by="id",all=TRUE)
#merge_fastfood

library(ggiraphExtra)
library(ggplot2)
#install.packages("RColorBrewer")
library(RColorBrewer)

display.brewer.all(n=10, exact.n=FALSE)

fmap <- ggChoropleth(data = merge_fastfood, palette="PuRd" ,
                       aes(fill=점포수, map_id=id, tooltip=kor), title="패스트푸드 점포수 현황",map=korea.shp.f, interactive=T, color="black") 

fmap


#############rent


rent_gu <- read_excel("map_rent.xlsx")
rent_gu$id <- sapply(rent_gu$id, as.character)

merge_rent <- merge(korea.shp.f,rent_gu,by="id",all=TRUE)
merge_rent

library(ggiraphExtra)
library(ggplot2)
#install.packages("RColorBrewer")
library(RColorBrewer)

display.brewer.all(n=10, exact.n=FALSE)

rmap <- ggChoropleth(data = merge_rent, palette="Greys" ,
                     aes(fill=평당가격, map_id=id, tooltip=kor), title="임대료 시세",map=korea.shp.f, interactive=T, color="black") 

rmap

######################################################################



























