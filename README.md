![](https://img.shields.io/badge/STATUS-NOT%20CURRENTLY%20MAINTAINED-red.svg?longCache=true&style=flat)

# Important Notice
This public repository is read-only and no longer maintained.

SAP HANA Cloud Samples - HANA Scripted View Demo
==========================================

"HANA Scripted View Demo" is a web application showing how one could use HANA Scripted Views in SAP HANA Cloud. 
The application also aims to show the benefit of using "Remote Database Access" tools provided by "SAP HANA Cloud SDK 2.x"


Quick start
-----------

Read the how-to blog: <in progress>

Project Overview
----------------

Here is a basic description of the project. The structure is as follows:

com.sap.hana.cloud.samples.scriptview - contains ScriptViewDemoServlet which is used as demo
	db - classes which are used for database access
	
		
Application startup
-------------------

You can run "HANA Scripted View Demo" application only on the Cloud.

Running on the SAP HANA Cloud
1. Using Maven
 - go to your computer’s properties, Advanced System Settings, Environment variables and create a new system variable named “NW_CLOUD_SDK_PATH” and 
 enter the path to the directory where you have the downloaded the SAP HANA Cloud SDK 2.x to.
 - in pom.xml do update <remoteaccess.version> according the one provided by your SAP HANA Cloud SDK 2.x
 - from command prompt run "mvn clean install" to build war file
 - make sure that "dbaccess-beta.server-x.x.x.jar" is packaged into war file's WEB-INF\lib folder
 - deploy war file to the cloud
 - navigate to ScriptViewDemoServlet URL to get results from scripted view execution
 - import the remote HANA Cloud System into HANA studio
  
2. Using Eclipse
  - import project from git to Eclipse workspace: https://github.com/SAP/cloud-hana-scriptview-demo.git (consider installing EGit for your comfort)
  - Ready to deploy on the SAP HANA Cloud: from project's context menu "Run As"->"Run on Server"
 
Versioning
----------

For transparency and insight into our release cycle, and for striving to maintain backward compatibility, the SAP HANA Cloud - Samples project will be maintained under the Semantic Versioning guidelines as much as possible.

Releases will be numbered with the following format:

`<major>.<minor>.<patch>`

And constructed with the following guidelines:

* Breaking backward compatibility bumps the major (and resets the minor and patch)
* New additions without breaking backward compatibility bumps the minor (and resets the patch)
* Bug fixes and misc changes bumps the patch

For more information on SemVer, please visit http://semver.org/

Authors
-------

**Nikolay Stoichkov**
**Dimitar Tenev**

Copyright and license
---------------------

Copyright 2013 SAP AG

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Find the project description at documents/index.html
