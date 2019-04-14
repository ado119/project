install.packages("stringi")
install.packages("devtools")
install.packages("ggplot2")
library(rethinking)
devtools::install_github("cardiomoon/Kormaps",force=TRUE)
devtools::install_github("rmcelreath/rethinking", force = TRUE)
require(Kormaps)
require(tmap)

korea.seoul <- subset(korpopmap2, korpopmap2@data$code.1 %in% c("11010","11020","11030","11040","11050","11060","11070","11080","11090","11100","11110","11120","11130","11140","11150","11160","11170","11180","11190","11200","11210","11220","11230","11240","11250"))


kind <- readline('업종을 입력하세요: ')

asset <- as.numeric(readline('예산을 입력하세요: '))

library(readxl)

rent_z_bakery<-read_excel("rent_z_bakery.xlsx")
rent_z_cafe<-read_excel("rent_z_cafe.xlsx")
rent_z_conv<-read_excel("rent_z_conv.xlsx")
rent_z_fast<-read_excel("rent_z_fast.xlsx")

library(dplyr)

if(kind=='bakery') {
  for(i in 1:25){
    final_bakery <- rent_z_bakery %>% filter(rent<=asset)
    final_bakery <- final_bakery %>% arrange(desc(z_bakery))
    #final_bakery_id <- final_bakery$id
  }
  final_bakery <- head(final_bakery,n=3)
  print(final_bakery$gu)
  final_bakery_id <- final_bakery$id
  #print(head(final_bakery,n=3)$gu)
  new_korea <- subset(korpopmap2, korpopmap2@data$code.1 %in% c(final_bakery_id))
  plot(korea.seoul)
  plot(new_korea, col="orange",add=TRUE)
  
} else if(kind=='cafe'){
  for(i in 1:25){
    final_cafe <- rent_z_cafe %>% filter(rent<=asset)
    final_cafe <- final_cafe %>% arrange(desc(z_caf))
  }
  final_cafe <- head(final_cafe,n=3)
  print(head(final_cafe,n=3)$gu)
  final_cafe_id <- final_cafe$id
  new_korea <- subset(korpopmap2, korpopmap2@data$code.1 %in% c(final_cafe_id))
  plot(korea.seoul)
  plot(new_korea, col="orange",add=TRUE)
  
} else if(kind=='conv'){
  for(i in 1:25){
    final_conv <- rent_z_conv %>% filter(rent<=asset)
    final_conv <- final_conv %>% arrange(desc(z_conv))
  }
  final_conv <- head(final_conv,n=3)
  print(head(final_conv,n=3)$gu)
  final_conv_id <- final_conv$id
  new_korea <- subset(korpopmap2, korpopmap2@data$code.1 %in% c(final_conv_id))
  plot(korea.seoul)
  plot(new_korea, col="orange",add=TRUE)
  
} else if(kind=='fast'){
  for(i in 1:25){
    final_fast <- rent_z_fast %>% filter(rent<=asset)
    final_fast <- final_fast %>% arrange(desc(z_fast))
  }
  final_fast <- head(final_fast,n=3)
  print(head(final_fast,n=3)$gu)
  final_fast_id <- final_fast$id
  new_korea <- subset(korpopmap2, korpopmap2@data$code.1 %in% c(final_fast_id))
  plot(korea.seoul)
  plot(new_korea, col="orange",add=TRUE)
  
}else{
  print("올바른 업종을 입력하세요")
}