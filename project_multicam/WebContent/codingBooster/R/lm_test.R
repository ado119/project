#####################################################################
#### 1.F-test ####

###베이커리-매출
library(readxl)
setwd("C://Users//student//project_data//seoul")

seoul_sales_bakery <- read_excel("seoul_sales_bakery.xlsx")

seoul_sales_bakery <- as.data.frame(seoul_sales_bakery)
head(seoul_sales_bakery)
data(seoul_sales_bakery)


lm_bakery_sn<- lm(sales~num_bakery, data=seoul_sales_bakery)
lm_bakery_sr<- lm(sales~rent, data=seoul_sales_bakery)
lm_bakery_spop <- lm(sales~pop, data=seoul_sales_bakery)

summary(lm_bakery_sn)
summary(lm_bakery_sr)
summary(lm_bakery_spop)

###베이커리-영업기간

seoul_term_bakery <- read_excel("seoul_term_bakery.xlsx")

lm_bakery_tn<- lm(term~num_bakery, data=seoul_term_bakery)
lm_bakery_tr<- lm(term~rent, data=seoul_term_bakery)
lm_bakery_tpop <- lm(term~pop, data=seoul_term_bakery)

summary(lm_bakery_tn)
summary(lm_bakery_tr)
summary(lm_bakery_tpop)

###패스트푸드-매출

seoul_sales_fast <- read_excel("seoul_sales_fast.xlsx")

lm_fast_sn<- lm(sales~num_fast, data=seoul_sales_fast)
lm_fast_sr<- lm(sales~rent, data=seoul_sales_fast)
lm_fast_spop <- lm(sales~pop, data=seoul_sales_fast)

summary(lm_fast_sn)
summary(lm_fast_sr)
summary(lm_fast_spop)

###패스트푸드-영업기간

seoul_term_fast <- read_excel("seoul_term_fast.xlsx")

lm_fast_tn<- lm(term~num_fast, data=seoul_term_fast)
lm_fast_tr<- lm(term~rent, data=seoul_term_fast)
lm_fast_tpop <- lm(term~pop, data=seoul_term_fast)

summary(lm_fast_tn)
summary(lm_fast_tr)
summary(lm_fast_tpop)

###카페-매출

seoul_sales_cafe <- read_excel("seoul_sales_cafe.xlsx")

lm_cafe_sn<- lm(sales~num_cafe, data=seoul_sales_cafe)
lm_cafe_sr<- lm(sales~rent, data=seoul_sales_cafe)
lm_cafe_spop <- lm(sales~pop, data=seoul_sales_cafe)

summary(lm_cafe_sn)
summary(lm_cafe_sr)
summary(lm_cafe_spop)

###카페-영업기간

seoul_term_cafe <- read_excel("seoul_term_cafe.xlsx")

lm_cafe_tn<- lm(term~num_cafe, data=seoul_term_cafe)
lm_cafe_tr<- lm(term~rent, data=seoul_term_cafe)
lm_cafe_tpop <- lm(term~pop, data=seoul_term_cafe)

summary(lm_cafe_tn)
summary(lm_cafe_tr)
summary(lm_cafe_tpop)

###편의점-매출

seoul_sales_conv <- read_excel("seoul_sales_conv.xlsx")

lm_conv_sn<- lm(sales~num_conv, data=seoul_sales_conv)
lm_conv_sr<- lm(sales~rent, data=seoul_sales_conv)
lm_conv_spop <- lm(sales~pop, data=seoul_sales_conv)

summary(lm_conv_sn)
summary(lm_conv_sr)
summary(lm_conv_spop)


###편의점-영업기간

seoul_term_conv <- read_excel("seoul_term_conv.xlsx")

lm_conv_tn<- lm(term~num_conv, data=seoul_term_conv)
lm_conv_tr<- lm(term~rent, data=seoul_term_conv)
lm_conv_tpop <- lm(term~pop, data=seoul_term_conv)
                
summary(lm_conv_tn)
summary(lm_conv_tr)
summary(lm_conv_tpop)

#####################################################################

#### 2. 다중 회귀 분석 ####
# conv
multi_sales_conv <- lm(sales~num_conv+rent+pop, data=seoul_sales_conv)
multi_sales_conv

multi_term_conv <- lm(term~num_conv+rent+pop, data=seoul_term_conv)
multi_term_conv

# cafe
multi_sales_cafe <- lm(sales~num_cafe+rent+pop, data=seoul_sales_cafe)
multi_sales_cafe

multi_term_cafe <- lm(term~num_cafe+rent+pop, data=seoul_term_cafe)
multi_term_cafe

# bakery
multi_sales_bakery <- lm(sales~num_bakery+rent+pop, data=seoul_sales_bakery)
multi_sales_bakery

multi_term_bakery <- lm(term~num_bakery+rent+pop, data=seoul_term_bakery)
multi_term_bakery

# fastfood
multi_sales_fast <- lm(sales~num_fast+rent+pop, data=seoul_sales_fast)
multi_sales_fast

multi_term_fast <- lm(term~num_fast+rent+pop, data=seoul_term_fast)
multi_term_fast

######################################################################

#### 구별,업종별 ->  y1, y2값 도출 ####

setwd("C://Users//student//project_data")
library(readxl)
seoul_sales_conv <- read_excel("seoul_sales_conv.xlsx")

multi_sales_conv <- lm(sales~num_conv+rent+pop, data=seoul_sales_conv)
multi_sales_conv

coef_sales_conv <- as.data.frame(coef(multi_sales_conv))
coef_sales_conv
head(coef_sales_conv)
conv_data_final <- read_excel("conv_data_final.xlsx")
conv_data_final <- as.data.frame(conv_data_final)
head(conv_data_final)

y1_conv=c()
for(i in 1:25) {
  y1_conv[i]<-conv_data_final[i,4]*coef_sales_conv[[1]][2]+conv_data_final[i,5]*coef_sales_conv[[1]][3]+conv_data_final[i,6]*coef_sales_conv[[1]][4]
  #print(y1)
  
}
y1_conv
class(y1_conv)
y1_conv <-as.data.frame(y1_conv)

install.packages("writexl")
library(writexl)
writexl::write_xlsx(y1_conv, path = "C:/Users/student/Desktop/project_data/y1_conv.xlsx")


############conv의 term(y2)
seoul_term_conv <- read_excel("seoul_term_conv.xlsx")

multi_term_conv <- lm(term~num_conv+rent+pop, data=seoul_term_conv)
multi_term_conv

coef_term_conv <- as.data.frame(coef(multi_term_conv))
coef_term_conv
typeof(coef_term_conv)

conv_data_final <- read_excel("conv_data_final.xlsx")
conv_data_final <- as.data.frame(conv_data_final)
head(conv_data_final)
class(conv_data_final)

y2_conv=c()
for(i in 1:25) {
  y2_conv[i]<-
    conv_data_final[i,4]*coef_term_conv[[1]][2]+conv_data_final[i,5]*coef_term_conv[[1]][3]+conv_data_final[i,6]*coef_term_conv[[1]][4]
  #print(y2_conv)
}

y2_conv
y2_conv <-as.data.frame(y2_conv)

install.packages("writexl")
library(writexl)
writexl::write_xlsx(y2_conv, path = "C:/Users/student/Desktop/project_data/y2_conv.xlsx")




####cafe
seoul_sales_cafe <- read_excel("seoul_sales_cafe.xlsx")

multi_sales_cafe <- lm(sales~num_cafe+rent+pop, data=seoul_sales_cafe)
multi_sales_cafe

coef_sales_cafe <- as.data.frame(coef(multi_sales_cafe))
coef_sales_cafe
head(coef_sales_cafe)
cafe_data_final <- read_excel("cafe_data_final.xlsx")
cafe_data_final <- as.data.frame(cafe_data_final)
head(cafe_data_final)

y1_cafe=c()
for(i in 1:25) {
  y1_cafe[i]<-cafe_data_final[i,4]*coef_sales_cafe[[1]][2]+cafe_data_final[i,5]*coef_sales_cafe[[1]][3]+cafe_data_final[i,6]*coef_sales_cafe[[1]][4]
}
y1_cafe
y1_cafe <-as.data.frame(y1_cafe)

install.packages("writexl")
library(writexl)
writexl::write_xlsx(y1_cafe, path = "C:/Users/student/Desktop/project_data/y1_cafe.xlsx")


####cafe(term)
seoul_term_cafe <- read_excel("seoul_term_cafe.xlsx")

multi_term_cafe <- lm(term~num_cafe+rent+pop, data=seoul_term_cafe)
multi_term_cafe

coef_term_cafe <- as.data.frame(coef(multi_term_cafe))
coef_term_cafe
typeof(coef_term_cafe)


y2_cafe=c()
for(i in 1:25) {
  y2_cafe[i]<-
    cafe_data_final[i,4]*coef_term_cafe[[1]][2]+cafe_data_final[i,5]*coef_term_cafe[[1]][3]+cafe_data_final[i,6]*coef_term_cafe[[1]][4]
  #print(y2_conv)
}

y2_cafe
y2_cafe <-as.data.frame(y2_cafe)

install.packages("writexl")
library(writexl)
writexl::write_xlsx(y2_cafe, path = "C:/Users/student/Desktop/project_data/y2_cafe.xlsx")


#####bakery
seoul_sales_bakery <- read_excel("seoul_sales_bakery.xlsx")

multi_sales_bakery <- lm(sales~num_bakery+rent+pop, data=seoul_sales_bakery)
multi_sales_bakery

coef_sales_bakery <- as.data.frame(coef(multi_sales_bakery))
coef_sales_bakery

bakery_data_final <- read_excel("bakery_data_final.xlsx")
bakery_data_final <- as.data.frame(bakery_data_final)
head(bakery_data_final)

y1_bakery=c()
for(i in 1:25) {
  y1_bakery[i]<-bakery_data_final[i,4]*coef_sales_bakery[[1]][2]+bakery_data_final[i,5]*coef_sales_bakery[[1]][3]+bakery_data_final[i,6]*coef_sales_bakery[[1]][4]
}
y1_bakery
y1_bakery <-as.data.frame(y1_bakery)

install.packages("writexl")
library(writexl)
writexl::write_xlsx(y1_bakery, path = "C:/Users/student/Desktop/project_data/y1_bakery.xlsx")


###bakery(term)
seoul_term_bakery <- read_excel("seoul_term_bakery.xlsx")

multi_term_bakery <- lm(term~num_bakery+rent+pop, data=seoul_term_bakery)
multi_term_bakery

coef_term_bakery <- as.data.frame(coef(multi_term_bakery))
coef_term_bakery

y2_bakery=c()
for(i in 1:25) {
  y2_bakery[i]<-
    bakery_data_final[i,4]*coef_term_bakery[[1]][2]+bakery_data_final[i,5]*coef_term_bakery[[1]][3]+bakery_data_final[i,6]*coef_term_bakery[[1]][4]
}

y2_bakery
y2_bakery <-as.data.frame(y2_bakery)

install.packages("writexl")
library(writexl)
writexl::write_xlsx(y2_bakery, path = "C:/Users/student/Desktop/project_data/y2_bakery.xlsx")


####fastfood
seoul_sales_fast <- read_excel("seoul_sales_fast.xlsx")

multi_sales_fast <- lm(sales~num_fast+rent+pop, data=seoul_sales_fast)
multi_sales_fast

coef_sales_fast <- as.data.frame(coef(multi_sales_fast))
coef_sales_fast
head(coef_sales_fast)
fast_data_final <- read_excel("fast_data_final.xlsx")
fast_data_final <- as.data.frame(fast_data_final)
head(fast_data_final)

y1_fast=c()
for(i in 1:25) {
  y1_fast[i]<-fast_data_final[i,4]*coef_sales_fast[[1]][2]+fast_data_final[i,5]*coef_sales_fast[[1]][3]+fast_data_final[i,6]*coef_sales_fast[[1]][4]
}
y1_fast
y1_fast <-as.data.frame(y1_fast)

install.packages("writexl")
library(writexl)
writexl::write_xlsx(y1_fast, path = "C:/Users/student/Desktop/project_data/y1_fast.xlsx")



####fast(term)
seoul_term_fast <- read_excel("seoul_term_fast.xlsx")

multi_term_fast <- lm(term~num_fast+rent+pop, data=seoul_term_fast)
multi_term_fast

coef_term_fast <- as.data.frame(coef(multi_term_fast))
coef_term_fast

y2_fast=c()
for(i in 1:25) {
  y2_fast[i]<-
    fast_data_final[i,4]*coef_term_fast[[1]][2]+fast_data_final[i,5]*coef_term_fast[[1]][3]+fast_data_final[i,6]*coef_term_fast[[1]][4]
}

y2_fast
y2_fast <-as.data.frame(y2_fast)

install.packages("writexl")
library(writexl)
writexl::write_xlsx(y2_fast, path = "C:/Users/student/Desktop/project_data/y2_fast.xlsx")

######################################################################

#### 누적분포함수 : pnorm 함수 ####
## y1 y2값을 누적분포함수로 표준화시켜 y1 y2의 비중을 같게해 
## 최종 위치값인 z를 도출해냄.

setwd("C:/Users/student/Desktop/project_data")
library(readxl)

y_bakery <- read_excel("y_bakery.xlsx")
head(y_bakery)

z_bakery <- transform(y_bakery, z.y1_bakery=scale(y1_bakery), z.y2_bakery=scale(y2_bakery))

head(z_bakery)
pnorm(z_bakery$z.y1_bakery)
pnorm(z_bakery$z.y2_bakery)

#########conv
y_conv <- read_excel("y_conv.xlsx")
z_conv <- transform(y_conv, z.y1_conv=scale(y1_conv), z.y2_conv=scale(y2_conv))
head(z_conv)
pnorm_y1_conv <- pnorm(z_conv$z.y1_conv)
pnorm_y1_conv <- as.data.frame(pnorm_y1_conv)
writexl::write_xlsx(pnorm_y1_conv, path = "C:/Users/student/Desktop/project_data/pnorm_y1_conv.xlsx")

pnorm_y2_conv <- pnorm(z_conv$z.y2_conv)
pnorm_y2_conv <- as.data.frame(pnorm_y2_conv)
writexl::write_xlsx(pnorm_y2_conv, path = "C:/Users/student/Desktop/project_data/pnorm_y2_conv.xlsx")


##########cafe
y_cafe <- read_excel("y_cafe.xlsx")
z_cafe <- transform(y_cafe, z.y1_cafe=scale(y1_cafe), z.y2_cafe=scale(y2_cafe))
z_cafe
pnorm_y1_cafe <- pnorm(z_cafe$z.y1_cafe)
pnorm_y1_cafe <- as.data.frame(pnorm_y1_cafe)
writexl::write_xlsx(pnorm_y1_cafe, path = "C:/Users/student/Desktop/project_data/pnorm_y1_cafe.xlsx")

pnorm_y2_cafe <- pnorm(z_cafe$z.y2_cafe)
pnorm_y2_cafe <- as.data.frame(pnorm_y2_cafe)
writexl::write_xlsx(pnorm_y2_cafe, path = "C:/Users/student/Desktop/project_data/pnorm_y2_cafe.xlsx")


########bakery
y_bakery <- read_excel("y_bakery.xlsx")
z_bakery <- transform(y_bakery, z.y1_bakery=scale(y1_bakery), z.y2_bakery=scale(y2_bakery))
z_bakery
pnorm_y1_bakery <- pnorm(z_bakery$z.y1_bakery)
pnorm_y1_bakery <- as.data.frame(pnorm_y1_bakery)
writexl::write_xlsx(pnorm_y1_bakery, path = "C:/Users/student/Desktop/project_data/pnorm_y1_bakery.xlsx")

pnorm_y2_bakery <- pnorm(z_bakery$z.y2_bakery)
pnorm_y2_bakery <- as.data.frame(pnorm_y2_bakery)
writexl::write_xlsx(pnorm_y2_bakery, path = "C:/Users/student/Desktop/project_data/pnorm_y2_bakery.xlsx")



########fastfood

y_fast <- read_excel("y_fast.xlsx")
z_fast <- transform(y_fast, z.y1_fast=scale(y1_fast), z.y2_fast=scale(y2_fast))
z_fast
pnorm_y1_fast <- pnorm(z_fast$z.y1_fast)
pnorm_y1_fast <- as.data.frame(pnorm_y1_fast)
writexl::write_xlsx(pnorm_y1_fast, path = "C:/Users/student/Desktop/project_data/pnorm_y1_fast.xlsx")

pnorm_y2_fast <- pnorm(z_fast$z.y2_fast)
pnorm_y2_fast <- as.data.frame(pnorm_y2_fast)
writexl::write_xlsx(pnorm_y2_fast, path = "C:/Users/student/Desktop/project_data/pnorm_y2_fast.xlsx")

#######################################################################

setwd("C://Users//student//project_data")

kind_conv = read_excel("rent_z_conv.xlsx")
kind_cafe = read_excel("rent_z_cafe.xlsx")
kind_bakery = read_excel("rent_z_bakery.xlsx")
kind_fast = read_excel("rent_z_fast.xlsx")

### kind 변수생성 ###

# kind는 웹페이지에서 선택하는 4가지 업종의 총 업종 변수
# kind 변수 만들어줘야함
ifelse(kind == conv, kind_conv,
       ifelse(kind == cafe, kind_cafe,
              ifelse(kind == bakery, kind_bakery,
                     ifelse(kind == fast, kind_fast))))

### asset 변수생성 ###

# 1차필터링(임대료 시세로 거르는)을 위해
# asset이라는 예산 변수 생성해야함
library(dplyr)
kind_conv %>% filter(rent <= asset)
kind_cafe %>% filter(rent <= asset)
kind_bakery %>% filter(rent <= asset)
kind_fast %>% filter(rent <= asset)

#











