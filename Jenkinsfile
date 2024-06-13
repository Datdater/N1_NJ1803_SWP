pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Checkout the code
                    checkout scm

                    // Extract Jira issue key from the latest commit message
                    def commitMessage = bat(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                    def jiraIssueKey = commitMessage.find(/${env.JIRA_PROJECT_KEY}-\d+/) // Adjust regex as necessary

                    if (!jiraIssueKey) {
                        error "Jira issue key not found in the commit message."
                    }

                    env.JIRA_ISSUE_KEY = jiraIssueKey
                }
            }
        }

        stage('Build') {
            steps {
                dir('coding/be') {
                    bat 'mvn clean test'
                }
            }
            post {
                always {
                    dir('coding/be') {
                        junit 'target/surefire-reports/*.xml'
                    }
                }
            }
        }

        stage('Update Jira') {
            steps {
                script {
                    def testResults = junit 'coding/be/target/surefire-reports/*.xml'
                    def jiraIssueKey = env.JIRA_ISSUE_KEY
                    def status = testResults.failCount == 0 ? "Pass" : "Fail"
                    def attachment = 'coding/be/target/surefire-reports/testng.xml'

                    // Update the custom field "Testcase Result" on Jira
                    httpRequest(
                        url: "${env.JIRA_URL}/rest/api/2/issue/${jiraIssueKey}/transitions",
                        httpMode: 'POST',
                        customHeaders: [
                            [name: 'Authorization', value: "${env.JIRA_AUTH}"],
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
                        url: "${env.JIRA_URL}/rest/api/2/issue/${jiraIssueKey}/attachments",
                        httpMode: 'POST',
                        customHeaders: [
                            [name: 'Authorization', value: "${env.JIRA_AUTH}"],
                            [name: 'X-Atlassian-Token', value: 'no-check']
                        ],
                        uploadFile: attachment
                    )
                }
            }
        }
    }
}
