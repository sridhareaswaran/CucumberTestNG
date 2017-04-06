pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'I am at Built part'
      }
    }
    stage('Test') {
      steps {
        parallel(
          "Test": {
            echo 'I am at test part'
            
          },
          "Chrome": {
            echo 'chrome test'
            
          },
          "Firefox": {
            echo 'ff'
            
          },
          "safari": {
            echo 'safari'
            
          }
        )
      }
    }
    stage('Mobile QA') {
      steps {
        parallel(
          "Mobile QA": {
            echo 'mob'
            
          },
          "Android": {
            sleep 3
            echo 'android'
            
          },
          "IOS": {
            echo 'IOS'
            
          }
        )
      }
    }
    stage('PROD push') {
      steps {
        echo 'ta da !!!'
      }
    }
  }
}