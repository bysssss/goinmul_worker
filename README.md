VM 아규먼트 지정필수!
 -Dspring.profiles.active= local 혹은 dev 혹은 prod
 
 실행
 java -server -Dspring.profiles.active=프로퍼티 -jar /app/.../worker.jar >> /app/.../worker.out 2>&1 &
 
 스크립트
 /app/.../start.sh 이름
 /app/.../stop.sh 이름
 