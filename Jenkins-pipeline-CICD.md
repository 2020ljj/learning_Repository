## Jenkins-pipeline-CI/CD

任务目标：基于pipeline实现在示例代码库中提交代码自动触发编译打包并上传介质到nexus

#### 五个小目标：

1.代码提交触发策略支持配置忽略制定后缀的文件，如*.sql。

2.支持执行结果信息邮件通知

3.支持代码扫描并查看返回的结果信息及质量门禁

4.支持生成bom.xml文件及制品扫描并查看结果信息

5.结构化，参数化过程。

#### JenkinsFile:

```groovy
pipeline{
    agent any
    environment {
        // 定义环境变量
        onlyIgnoreFilesModified = true
    }
    stages{
        stage('Pull code'){
            steps {
               git branch: '${branch}', credentialsId: '9c306fff-1755-4b43-8491-195a41e8b79c', url: 'http://10.15.15.86/demo1/demowar.git'
               echo "pull code finished - branch: ${branch}"
               script{
                    //def onlyIgnoreFilesModified = true
                    def changedFiles = sh(returnStdout: true, script: 'git diff --name-only HEAD~ HEAD').trim().split('\n')
                    echo "modified files: ${changedFiles}"
                    def IgnoreFilePostfix = "${ignore_file_postfix}".trim().split('/')
                    for (file in changedFiles) {
                        def fileExtension = file.substring(file.lastIndexOf('.') + 1)
                        if (!(fileExtension in IgnoreFilePostfix)) {
                            onlyIgnoreFilesModified = false
                            break
                        }
                    }
                    echo "是否跳过后续步骤: ${onlyIgnoreFilesModified}"
               }
            }
            
        }
        stage('Maven Build'){
            steps {
                 script {
                    if (onlyIgnoreFilesModified) {
                        echo 'No changes or Only changes to Ignored files. Skipping Maven compilation and packaging.'
                    } else {
                        // 执行 Maven 编译和打包
                        sh 'mvn clean ${mvn_option} -DartifactId=${artifact_id} -DjarVersion=${jar_version} -DversionType=${version_type}'
                        echo 'maven build finished'
                    }
                }
            }
        }
        stage('Sonarqube Check') {

            steps {
                script {
                    if (onlyIgnoreFilesModified) {
                        echo 'No changes or Only changes to Ignored files. Skipping Sonarqube Check.'
                    } else{
                        //引入了sonarqube-scanner工具
                        scannerHome = tool 'sonarqube-scanner'
                        //引入了sonarqube服务器系统环境
                        withSonarQubeEnv('sonarqube') {
                        sh "${scannerHome}/bin/sonar-scanner"
                        }
                }
            }
            }
        }
        stage("Quality Gate"){
			steps{
				script{
			        if (onlyIgnoreFilesModified) {
                        echo 'No changes or Only changes to Ignored files. Skipping "Quality Gate.'
                    } else{
                        timeout(time: 15, unit: 'MINUTES') {
    				    def qg = waitForQualityGate()
    					if(qg.status == 'OK')
					        echo 'Quality Gate Finished -the result is OK !'
					    else
					        error "Pipeline aborted - quality gate failure: ${qg.status}"
				        }
                    }
			    }
		    }
		}
		stage('Dependency Track'){
		    steps{
		        script {
                if (onlyIgnoreFilesModified) {
                        echo 'No changes or Only changes to Ignored files. Skipping Dependency Track.'
                    } else{
                        def dependencyTrackScan = 
		                dependencyTrackPublisher (
		                artifact: '${bom_path}', 
		                autoCreateProjects: true, 
		                dependencyTrackApiKey: '', 
		                dependencyTrackFrontendUrl: '',
		                dependencyTrackUrl: '', 
		                //projectId: '6e843c25-3d94-4e8b-a61b-ab6a77e3cb16',
		                projectName: '${JOB_NAME}', 
		                projectVersion: '${BUILD_DISPLAY_NAME}', 
		                synchronous: true
		                )
		                echo 'dependency track finished'
                    }
		        }
		    }
		}
        stage('Upload Nexus'){
            steps{
                script{
                    if (onlyIgnoreFilesModified) {
                        echo 'No changes or Only changes to Ignored files. Skipping Upload Nexus.'
                    } else{
                        nexusArtifactUploader artifacts: 
                            [[artifactId: '${artifact_id}', 
                            classifier: '', 
                            file: 'target/${artifact_id}-${jar_version}-${version_type}.jar', 
                            type: 'jar']], 
                            credentialsId: 'e20e21c0-2490-4890-970d-6c64a3960290', 
                            groupId: '${JOB_NAME}', 
                            nexusUrl: '10.15.15.86:8081', 
                            nexusVersion: 'nexus3', 
                            protocol: 'http', 
                            repository: '${nexus_repository}', 
                            version: '${jar_version}'
                        echo 'upload nexus finished'
                    }
                }
                
            }
        }
    }
    post {
	    always {
                emailext (
                    subject: '\'构建通知:${PROJECT_NAME} - Build # ${BUILD_NUMBER} - ${BUILD_STATUS}\'',
                    body: '${FILE,path="email_template.html"}',
                    to: '${recipients}'
                )
        }
    }

}
```

需要安装插件：[GitLab Plugin](https://plugins.jenkins.io/gitlab-plugin)、[Generic Webhook Trigger Plugin](https://plugins.jenkins.io/generic-webhook-trigger)、[Nexus Artifact Uploader版本](https://plugins.jenkins.io/nexus-artifact-uploader)、[SonarQube Scanner for Jenkins版本](https://plugins.jenkins.io/sonar)、[Sonar Quality Gates Plugin](https://plugins.jenkins.io/sonar-quality-gates)、[OWASP Dependency-Track](https://plugins.jenkins.io/dependency-track)和[Email Extension Template Plugin版本](https://plugins.jenkins.io/emailext-template)/[Email Extension Plugin](https://plugins.jenkins.io/email-ext)

#### steps: 

1.配置Jenkins环境变量，包括mvn，java等，jenkins若是镜像容器的话，需要在容器内部下载对应的java，mvn包

2.gitlab中设置对应的webhook：点进GitLab项目主页-左侧用户设置-WebHook

![image-20230721171819971](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230721171819971.png)

​																						图1：设置webHook

3.流水线Configure-构建触发器-Build when a change is pushed to GitLab. GitLab webhook URL: - 高级 - Secret token，填写gitlab处生成的token

4.配置Sonaqube server &Sonaqube scanner

![image-20230721172618240](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230721172618240.png)



![image-20230721172638656](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230721172638656.png)

5.配置邮箱 Extended E-mail Notification（需先去邮箱中开启SMTP服务）

参数设置如下（以QQ为例）

SMTP server：smtp.qq.com

SMTP Port：465

Credentials：邮箱账号/token

Default user e-mail suffix: @qq.com

Default Content Type: HTML

6.配置dependency-track，api-key只需token即可

![](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230721173132065.png)

7.编写脚本，提交代码触发命令

#### 需要注意的地方：

1.引用变量需要用双引号，如：

```
"${branch}"
```

2. stage内定义的变量只在其内部有效；environment定义的变量无法直接修改，但可以在script中（可以不同stage）修改，也只能在script中修改访问
3. 代码块可以通过流水线语法快速生成

#### 可以优化的地方

1. maven参数修改，artificialid直接在pom里改，不需要传参
2. sonar-property可以抽成参数
3. 可以从env.GITLAB_REPOSITORY中得到gitlab返回的参数
4. 拉取代码需考虑并发性：根据commit信息只拉取指定的版本或者取消掉旧的提交，只拉取执行最新的提交
5. 只有过滤文件被修改时不执行后续的步骤