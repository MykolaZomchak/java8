pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'tar xvzf /root/tools/hudson.model.JDK/java_9/jdk.sh'
        sh '/root/tools/hudson.model.JDK/java_9/bin/java -cp /root/maven35-agent.jar:/root/tools/hudson.tasks.Maven_MavenInstallation/maven/boot/plexus-classworlds-2.5.2.jar:/root/tools/hudson.tasks.Maven_MavenInstallation/maven/conf/logging jenkins.maven3.agent.Maven35Main /root/tools/hudson.tasks.Maven_MavenInstallation/maven /usr/share/jenkins/slave.jar /root/maven35-interceptor.jar /root/maven3-interceptor-commons.jar 43636'
      }
    }
  }
}