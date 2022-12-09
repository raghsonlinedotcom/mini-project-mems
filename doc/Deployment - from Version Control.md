# Deploying the code

Deploying - Shifting/Promoting the code from your
local / development workspace to the Server.

## Flavors

* From Development workspace
* From Version Control (Github) Workspace
  - Recommended
  - To catch up the files if any being missed from the Development Workspace

## From Dev Workspace

* Export the Project as a WAR file (`.war`) - Web ARchive from within Eclipse to your preferred location - recommended you keep it inside the `build` directory
* Use `SCP` command to copy the file from the local folder to the Server (Remote)
  > Copy the file in to your HOME directory (NOT inside the Application Server's Deploy directory).It will help you as a backup for a future reference.

* In the Server (Remote)
  - Assume the necessary components/software are already present - like Tomcat (Web/App Server), MySQL (Database) etc.,
  - Execute the DB Scripts if needed on the MySQL Database
  - Deploy the `.war` file from your HOME directory (where you have copied the file from your local machine via `scp`) to the server's DEPLOY directory - `<TOMCAT_HOME>/webapps` in case of Tomcat.
  - Ensure the required libraries are present in the `.war` file (shipped along), In our case `mysql-jdbc-connector-v8.X.jar` is needed to connect to the MySQL Database.
  - Start the Server
  - Hit the URL in the browser `http://<IPAddress:Port>/<APP_ROOT>/`. Example, `http://10.121.1.241:8080/JDBCWebApp`


## Steps

```sh
➜  build pwd
/Users/raghavan.muthu/eclipse-workspace/MEMS/build
➜  build ls
MEMS.war classes
➜  build ls -ltrh
total 4688
drwxr-xr-x  4 raghavan.muthu  staff   128B Dec  7 13:33 classes
-rw-r--r--  1 raghavan.muthu  staff   2.3M Dec  8 14:00 MEMS.war
```
> The size of the `.war` file is 2.3M - that is 2.3 MB, which means
the Archive file has got the necessary library as a dependency - in our
case MySQL JDBC Connector.

Now we will remove this library in the Eclipse from 'Deployment Assembly' - in
the `Project -> Properties -> Deployment Assembly` and then *Export* the project
as a *WAR* file again.

```sh
➜  build exa -la
drwxr-xr-x   - raghavan.muthu  7 Dec 13:33 classes
.rw-r--r-- 16k raghavan.muthu  8 Dec 14:15 MEMS.war
➜  build ls -ltrh
total 32
drwxr-xr-x  4 raghavan.muthu  staff   128B Dec  7 13:33 classes
-rw-r--r--  1 raghavan.muthu  staff    15K Dec  8 14:15 MEMS.war
➜  build
```

> If you observe the file size of the Archive, it is just 16 KB what is
actually coming from the source code which is an ASCII text and with a
limited content in each file. Quite appropriate, and slim :)

## SCP file to copy the .war file

```sh
➜  build pwd   
/Users/raghavan.muthu/eclipse-workspace/MEMS/build
➜  build pwd                                                   
/Users/raghavan.muthu/eclipse-workspace/MEMS/build
➜  build scp MEMS.war raghavan.muthu@10.121.1.241:/home/raghavan.muthu/scp-files
MEMS.war                                                                                100%   15KB 133.3KB/s   00:00    
➜  build
```

### Verification in the EC2 Sandbox

```sh
raghavan.muthu@ip-10-121-1-241:~/scp-files$ hostname
ip-10-121-1-241
raghavan.muthu@ip-10-121-1-241:~/scp-files$ pwd
/home/raghavan.muthu/scp-files
raghavan.muthu@ip-10-121-1-241:~/scp-files$ ls -ltrh
total 2.4M
-rw-r--r-- 1 raghavan.muthu raghavan.muthu   22 Sep 12 14:26 scp-demo.txt
-rw-r--r-- 1 raghavan.muthu raghavan.muthu   22 Sep 12 14:28 sco-demo-new.txt
-rw-r--r-- 1 raghavan.muthu raghavan.muthu 2.4M Nov  9 09:58 JDBCWebApp.war
-rw-r--r-- 1 raghavan.muthu raghavan.muthu  16K Dec  8 08:52 MEMS.war
raghavan.muthu@ip-10-121-1-241:~/scp-files$
```

```sh
raghavan.muthu@ip-10-121-1-241:~/scp-files$ hostname
ip-10-121-1-241
raghavan.muthu@ip-10-121-1-241:~/scp-files$ pwd
/home/raghavan.muthu/scp-files
raghavan.muthu@ip-10-121-1-241:~/scp-files$ ls -ltrh
total 2.4M
-rw-r--r-- 1 raghavan.muthu raghavan.muthu   22 Sep 12 14:26 scp-demo.txt
-rw-r--r-- 1 raghavan.muthu raghavan.muthu   22 Sep 12 14:28 sco-demo-new.txt
-rw-r--r-- 1 raghavan.muthu raghavan.muthu 2.4M Nov  9 09:58 JDBCWebApp.war
-rw-r--r-- 1 raghavan.muthu raghavan.muthu  16K Dec  8 08:52 MEMS.war
raghavan.muthu@ip-10-121-1-241:~/scp-files$
```

`http://10.121.1.241:8080/MEMS/`
`http://10.121.1.241:8080/MEMS/register.jsp`

## Promoting the library file separately

You can paste the library /dependency file (MySQL JDBC Connector) in two diff locations

* Common `lib` folder - which is outside the webapps directory and all the `.war` files / applications present in side the `webapps` directory will have access to.

 - `/home/raghavan.muthu/soft/apache-tomcat-9.0.65/lib`


* The `lib` folder present inside a specific application/project having the same name of the `.war` file, accessible ONLY to the concerned project.

 - `/home/raghavan.muthu/soft/apache-tomcat-9.0.65/webapps/MEMS/WEB-INF/lib`

 > The `lib` folder will be present under the `WEB-INF` directory of the application.


## Sanity Testing

`http://10.121.1.241:8080/MEMS/register`

### Output

`Registration successful. Your User Id : 3`

## Verify?

Manually had to login to the Database Console and query the table - `SELECT * FROM User;`

### Alternative

* List Functionality - *Common*
* Read Functionality (View)
* Login with that newly created user
* Register the same user once again and see if you get an error - because we have an `UNIQUE` constraint applied on the `userName` column. -- *Perfect*, *Efficient*, and *Simple*

## Bugs ?

* The exception if any while registering the user was NOT handled appropriately. The log file had the information - because we have properly handled the exception in the DAO class and logged the messages as well (but unfortunately NOT in the Servlet).

*Root Cause* : We assumed that things will *always* be successful.

## What Next?

Report this bug in JIRA tool - as we are following the Agile Methodology and using Jira for managing the backlog, user stories etc.,

## Severity or Priority of the Defect

- P1 (Critical OR Priority 1) - Most critical - Application is down.
- P2 (High Severity) - System is functioning but impacting the flow (somewhere)
- P3 (Medium Severity) - The issue can better be addressed in sometime.
- P4 (Low Severity) - No greater impact. The issue can be addressed whenever possible.

## Decision

* Use Case : An User is not able to register if he had already been registered.
* System Behavior : The message says, `The Registration is successful. Your Id : 0` - which is *confusing*.
* Does it Impact the Application? No.
  - The positive cases - the first users can still register successfully.
  - The negative cases - The duplicate users are NOT able to register. It is still good.
  - Gap? The message being shown is alone confusing.

* What is the severity of this bug/defect?
  - As the message to the customer / user is not helpful and rather confusing, but still the System is functioning well, we would assess the priority of this defect as *P2*.
  - In case there is a shortage of bandwidth and the team is busy with other critical tasks, this can be addressed as *P3*, OR still classfied as *P2* but taken up later when there is a sufficient bandwidth.

> Rule of Thumb: When a product has got a list of open issues under P1 or P2, it is NOT recommended to be promoted to PROD (or to the market).
> Reason: it would have a customer dissatisfcation and/or an impact to the reputation of the business, or even a finanical impact.

* Can I produce this product (make it live) with this defect ?
 - Yes - Go ahead with the Go Live  / Prod Deployment.
 - No - No, you should fix this and then proceed further. *Decision*.
 - May be - as good as Yes.

# Raise a Bug in Jira

`https://raghsonlinedotcom.atlassian.net/browse/MEMS-14` - *Duplicate Users are not handled properly*

# New Requirement ?

Change in Requirement

* Having a necessity to have an *Admin Module* that will help managing the users of the System.
* Edit Profile - mainly for changing this password, OR he can even choose a new userName - provided if it is available.

> All these change in requirements should go *Product Backlog*.
