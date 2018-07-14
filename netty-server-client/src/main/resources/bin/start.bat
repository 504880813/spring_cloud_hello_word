@echo off       
set PATH=C:\Program Files\Java\jdk1.7.0_79\bin;C:\WINDOWS;C:\WINDOWS\COMMAND 
set classpath=.;C:\Program Files\Java\jdk1.7.0_79\lib\tools.jar;C:\Program Files\Java\jdk1.7.0_79\lib\dt.jar 

cd..
java -Xmx128m -Xms64m -Xmn32m -Xss16m -Ddubbo.shutdown.hook=true -jar hzx-user-service.jar 
   
pause 
