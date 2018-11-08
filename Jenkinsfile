#!groovy
def statuscolor = { currentBuild ->
  def status = "good"
  if (currentBuild.previousBuild != "SUCCESS") {
    if (currentBuild.result == "UNSTABLE") {
      status = "warning"
    }
    if (currentBuild.result == "FAILURE") {
      status = "danger"
    }
  }
  status
}
echo("Hello from Pipeline");
node {
  withEnv(['PATH+EXTRA=/usr/local/bin:/opt/ant/bin']) {
    stage("Environment") {
      slackSend message: "started ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)",
          //color: statuscolor(currentBuild),
          channel: "#ENTERSLACKCHANNEL", teamDomain: "perfectcommerce",
          token: "bTbF7NzpZlbyCWzd0ZY9tQ7G"

      sh 'echo $PATH'
      sh 'mvn -version'
    }
    stage('Checkout WPQATools') {
      checkout scm
      //sh 'git status'   
      sh 'mvn -V -U -e clean test'

      slackSend message: "finished ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)",
          //color: statuscolor(currentBuild),
          channel: "#ENTERSLACKCHANNEL", teamDomain: "perfectcommerce",
          token: "bTbF7NzpZlbyCWzd0ZY9tQ7G"
    }
  }
}
echo("Pipeline Completed");
