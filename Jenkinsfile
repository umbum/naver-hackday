node {
    stage ('clone') {
        checkout scm
    }
    stage ('gradle wrap') {
        sh '/opt/gradle/gradle-5.2.1/bin/gradle wrap'
    }
    stage('gradlew build') {
        if (isUnix()) {
            sh './gradlew build'
        } else {
            bat 'gradlew.bat build'
        }
    }
}
