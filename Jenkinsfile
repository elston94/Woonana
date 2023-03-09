pipeline { // 파이프라인의 시작, 여기에 파이프라인 작성할 거다!
    // 스테이지 별로 다른 거
    agent any // 어떤 노드를 쓸 건지. any 아무거나 쓰라는 의미

// 파이프라인 짜기 시작
    stages { 
        // 레포지토리를 다운로드 받음
        stage('Git Clone') { 
            agent any // agent 아무거나 쓰도록 함
            
            steps {
                echo 'Clonning Repository'

                git url: 'https://lab.ssafy.com/s06-final/S06P31B104.git',  // 다운로드 받을 깃 url
                    branch: 'master', // 다운받을 브랜치
                    credentialsId: 'tokenforjenkins'
            } // 깃을 pull 받았음

            post {
                success {
                    echo 'Successfully Cloned Repository'
                } // pull 성공한 경우 

                failure {
                  echo "Git Clone failed..."
                } // 성공하든 실패하든 보여줌

                cleanup {
                  echo "after all other post condition"
                } // post 다 끝나고 나서 보여줄 로그
            }
        }        

        stage('Build Backend Docker') {
          agent any
          steps {
            echo 'Build Backend'

            dir ('./server'){
                sh """
                pwd
                docker build -f ../backend/Dockerfile -t backend:latest ../backend
                docker build -f ../backend_chat/Dockerfile -t backendchat:latest ../backend_chat
                """
            }// 도커를 만들어서 빌드하기 위해서 docker build 명령어. 
             // 도커 컨테이너를 미리 실행시켜둬야 한다.
          }

          // step 끝나고 나서 실행
          post {
            failure { // 서버 빌드하다가 실패한 경우 에러 뱉고 나머지 파이프라인 종료시킨다
              error 'This pipeline stops here...'
            }
          }
        }
        
        stage('Docker Run') {
          agent any

          steps {
            echo 'Build Backend'

            dir ('./backend'){
                sh '''
                docker rm -f backend
                docker run -d --name backend -p 8081:8081 backend:latest
                docker rm -f backendchat
                docker run -d --name backendchat -p 8082:8082 backendchat:latest
                '''
            } // 원래 돌고 있던 도커 이미지 지우고 도커 server 도커를 켜준다
            // 위의 줄은 처음 배포할 때에는 지워줘야 한다
          }

          post {
            success {
              echo 'backend successfuly build'
            }
          }
        }

        stage('Build Frontend'){
          agent any
          steps {
            echo 'Build Frontend'

            dir('./frontend'){
              sh """
              sudo npm install
              sudo npm run build
              """
            }
          }
        }

        stage('Run Frontend'){
          agent any
          steps{
            echo 'Run Frontend'

            dir('./frontend'){
              sh """
              pwd
              sudo rm -rf /var/www/woonana/dist
              sudo mv ./dist /var/www/woonana/dist
              sudo systemctl restart nginx
              """
            }
          }
        }
        
    }
}
