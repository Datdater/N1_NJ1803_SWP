pipeline {
 agent any
 stages {
 stage('Checkout') {
 steps {
 script {
 // Checkout the code
 checkout scm

 // Extract Jira issue key from the latest commit message
 def commitMessage = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
 def jiraIssueKey = commitMessage.find(/PROJECT-\d+/) // Adjust the regex to match your Jira issue key pattern and
replace PROJECT to PROJECT KEY

 if (!jiraIssueKey) {
 error "Jira issue key not found in the commit message."
 }

 env.JIRA_ISSUE_KEY = jiraIssueKey
 }
 }
 }
 stage('Build') {
 steps {
 sh 'mvn clean test'
}
 post {
 always {
 junit 'target/surefire-reports/*.xml'
 }
 }
 }
 stage('Update Jira') {
 steps {
 script {
 def testResults = junit 'target/surefire-reports/*.xml'
 def jiraIssueKey = env.JIRA_ISSUE_KEY
 def jiraAuth = "Basic " + "username:api-token".bytes.encodeBase64().toString()
 def status = testResults.failCount == 0 ? "Pass" : "Fail"
 def attachment = "target/surefire-reports/testng.xml"
 // Update the custom field "Testcase Result" on Jira
 httpRequest(
 url: "https://your-domain.atlassian.net/rest/api/2/issue/${jiraIssueKey}/transitions",
 httpMode: 'POST',
 customHeaders: [
 [name: 'Authorization', value: jiraAuth],
 [name: 'Content-Type', value: 'application/json']
 ],
 requestBody: """
 {
 "fields": {
 "customfield_12345": "${status}" // Replace 'customfield_12345' with the ID of the 'Testcase Result' field
 }
 }
 """
 )
 // Attach test result file to Jira issue
 httpRequest(
 url: "https://your-domain.atlassian.net/rest/api/2/issue/${jiraIssueKey}/attachments",
 httpMode: 'POST',
 customHeaders: [
 [name: 'Authorization', value: jiraAuth],
 [name: 'X-Atlassian-Token', value: 'no-check']
 ],
 uploadFile: attachment
 )
 }
 }
 }
 }
}
