# aws 람다 적용 방법

- handler.js - 실제 동작하는 코드 파일
- node_modules.zip - 의존성 파일 목록 압축 파일(sharp 라이브러리 의존성 문제가 있어서 해당 파일 그대로 사용하는걸 추천)
- package.json - 의존성 파일 목록
- package-lock.json - 의존성 파일 버전 목록
- serverless.yml - 서버리스 설정 파일

# Serverless Framework

람다에 배포하기 위해 Serverless Framework를 사용한다.
```
npm install -g serverless
```
이후 aws계정과 연동  
node_modules.zip을 압출 해제한다.   

handler.js 파일의 버킷명, serverless.yml 파일의 빈 값을 추가 후 배포

# 람다 배포

```
serverless deploy
```


# 참고
노드 모듈이 잘못되었을 경우 다시 생성 하는 방법   
[링크](https://velog.io/@ycoding/람다와-ARM-아키텍처-오류-해결)
