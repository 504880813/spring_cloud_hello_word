FROM 192.168.2.247:5000/hzx/loan/lib/base:1.0
ADD lib/hzx*.jar /opt/boot/lib/
ADD lib/yyxb*.jar /opt/boot/lib/
ADD hzx-loan-service.jar /opt/boot/
ADD *.sh /opt/boot/
EXPOSE 20880
WORKDIR /opt/boot/
ENTRYPOINT /opt/boot/docker-startup.sh hzx-loan-service.jar loan.log && tail -f /home/logs/loan.log