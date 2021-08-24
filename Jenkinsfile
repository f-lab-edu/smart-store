pipeline {
    agent any
    
    tools{
    	maven "M3"
        jdk "java 1.8"
    }
 
    stages {
        stage('build'){
        	steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install clean package' 
            }
        }
        
        stage('copy app file to was'){
        	steps{
        		sh 'scp -P 1235 /var/lib/jenkins/workspace/smartstore_isaac2/target/smart-store-0.0.1-SNAPSHOT.jar root@106.10.45.18:/root/smart-store/app/smart-store.jar'
        	}
        }
        
        stage('connect to deploy server and deploy'){
        	steps{
        		sh 'ssh -p 1235 -i /var/lib/jenkins/.ssh/id_rsa root@106.10.45.18 "/root/smart-store/app/deploy.sh"' 
        	}
        }
    }
}
