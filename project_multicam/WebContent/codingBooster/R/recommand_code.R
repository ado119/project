

kind <- readline('업종을 입력하세요: ')
kind

asset <- as.numeric(readline('예산을 입력하세요: '))


rent_z_bakery<-read_excel("rent_z_bakery.xlsx")
rent_z_cafe<-read_excel("rent_z_cafe.xlsx")
rent_z_conv<-read_excel("rent_z_conv.xlsx")
rent_z_fast<-read_excel("rent_z_fast.xlsx")

library(dplyr)


if(kind=='bakery') {
  for(i in 1:25){
    final_bakery <- rent_z_bakery %>% filter(rent<=asset)
    final_bakery <- final_bakery %>% arrange(desc(z_bakery))
  }
  print(head(final_bakery,n=3)$gu)
} else if(kind=='cafe'){
  for(i in 1:25){
    final_cafe <- rent_z_cafe %>% filter(rent<=asset)
    final_cafe <- final_cafe %>% arrange(desc(z_caf))
  }
  print(head(final_cafe,n=3)$gu)
} else if(kind=='conv'){
  for(i in 1:25){
    final_conv <- rent_z_conv %>% filter(rent<=asset)
    final_conv <- final_conv %>% arrange(desc(z_conv))
  }
  print(head(final_conv,n=3)$gu)
} else if(kind=='fast'){
  for(i in 1:25){
    final_fast <- rent_z_fast %>% filter(rent<=asset)
    final_fast <- final_fast %>% arrange(desc(z_fast))
  }
  print(head(final_fast,n=3)$gu)
}else{
  print("올바른 업종을 입력하세요")
}
