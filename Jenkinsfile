pipeline {
    agent any
    stages {
        stage('Compile Stage') {
            steps {
                withMaven(maven: 'MAVEN') {
                    sh 'mvn clean compile'
                }
            }
        }
        stage('Test Stage') {
            steps {
                withMaven(maven: 'MAVEN') {
                    sh 'mvn test -DactiveProfile=jenkins'
                }
            }
        }
        stage('Cucumber Reports') {
            steps {
              sh 'echo Generating Reports'
            }
            post {
                always {
                    script {
                        def moduleNames = ["integration", "functional"]
                        for (i = 0; i < moduleNames.size(); i++) {
                            publishHTML target: [allowMissing         : false,
                                                 alwaysLinkToLastBuild: false,
                                                 keepAll              : true,
                                                 reportDir            : moduleNames[i] + '/target/cucumber/',
                                                 reportFiles          : 'report.html',
                                                 reportName           : 'Test Report:' + moduleNames[i]]
                        }
                    }
                }
            }
        }
    }
}