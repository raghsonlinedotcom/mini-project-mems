# MEMS - Bugs

## System.exit() placed in the Application

| Factor | Remarks |
| ------ | ------- |
| Behavior | The entire Tomcat Server was shutdown |
| Root Cause | There was a `System.exit(-1)` called in the `UserDAOImpl.registerUser()` method  |
| Explanation | The call to `System` is commmon across all the application in the same JVM. Hence the intention to stop the application, will cause the rest of all the other applications as well to be impacted. It is a huge negative/adverse effect, and should be totally avoided especially when we work on a central Server. |

* Ticket Id : https://raghsonlinedotcom.atlassian.net/browse/MEMS-18
* Summary : *System was shutdown when the JDBC Driver class was not loaded*.

## Dupliate User not handled properly

* Ticket Id: `https://raghsonlinedotcom.atlassian.net/browse/MEMS-14`
* Summary : *Duplicate Users are not handled properly*

## Workflow - Defect Fixing

Two flavors

* Flavor 1 - Make a commit for each bug (1:1)
  - PROS:
    * Have a better clarity on the delta changes you make on the code
    * Better Control - Maintenance and Administration
      - Easy to remove a particular defect fix on demand - with the *Commit Hash*.
  - CONS:
    * Might potentially become voluminous
* Flavor 2 - Make a single commit for all / more than 1 bugs (1:m)
  - PROS:
    - Less in Volume
    - Easy maintenance
  - CONS:
    - Lack of clarity on the fixes
    - Difficult to remove the code change pertaining to a particular defect on demand.

* Decision
  - We choose any one of the flavors depending on the list of available bugs and the bandwidth.

# Fixing a defect

## Focus on Defect

* Understanding the issue
  - Actual Issue
  - Scenario being tested
  - Understanding of the Application Flow

## Focus on Infra
* Details you should have on hand
  - Repository (Remote)
  - Branch (Remote)
    - Mostly it may be the `main` branch
    - There are chances that the target branch in remote repo is a *feature* branch.
  - Do you have write permissions in to the Remote Repo?
  - Do you have the permission to create a Remote branch?
    - If not, contact the Dev Ops or the Admin.

## Focus on the Workflow

Once you get the details,

* Jira - change the status of the Jira ticket from `TODO` or `OPEN` to `IN-PROGRESS`
* `git clone` to clone the remote repo - if you are doing it for the *first time* in your local machine.
  - You would be cloning the `main` branch always.
* Issue a `git pull` - when  you already cloned the repo in your machine.
    - Issuing a `git pull` on the `main` branch does *NOT* hurt!
    - Safe to issue anytime and everytime when you think it is appropriate.
* Create a new local branch for the bugfix, with the name of the branch relevant to the issue.
  - Recommended prefix : `bugfix/<XYZ>`
  - Sample: `bugfix/<JiraID>` OR `bugfix/<JiraID>-<PreciseTitle>`
* Switch to the newly created local branch if needed
  - `git branch <branch>` - will only create a branch but does *NOT* _switch_ or _checkout_ to the newly created branch.
  - `git checkout -b <branch>` - will create a new branch and also switches to the new branch.
* Make the code changes in Development Workspace
* Test the changes locally
* Ensure you are on the right branch (bugfix branch)
  - `git branch` - will show all the local branches
  - Ensure the branch being starred is the bugfix branch. - current branch.
* Copy the changes from Development workspace to the Github Workspace (Version Control)
* Status check  & Add & Commit
  - `git status`
  - `git diff` OR `git diff <fileName>` to verify whether the delta changes being added are intact
  - `git add .` OR `git add <file1> <file2> ....`
  - `git status` - to verify that all the files being untracked are now tracked and nothing is left out.
  - `git commit -m <MeaningfulCommitMessage>`
  - `git log --oneline` to verify the commit in the bugfix branch
  - `git checkout main` && `git log --oneline` - switch over to the `main` branch once and see the log if you want.
  > There will be obvious differences as the commit what was made in the `bugfix` branch will NOT be visible in the `main` branch.

* Its time to push to the remote repo.
* Create a *Remote* bugfix branch in the Remote Repository - from the remote `main` branch OR a `feature` branch in Remote.
* Add a link to the remote bugfix branch for the local bugfix branch
  - `git branch set-upstream-to=origin/<RemoteBugfixBranch> LocalBugfixBranch`
* Push to the remote repo.
  `git push`
* `git status` - check once in the local branch to ensure that everything is committed
* `git log --oneline` OR `git log` to read the commit history
* Create a Pull Request (PR) in the Remote Repository
  - Source : Remote `bugfix`
  - Target : Remote `main` OR the `feature` branch
    > You are _pulling_ the changes from the bugfix branch to the `main` or a `feature` branch.

* Review the PR
* Merging the source code - automatically done by GitHub
  - Remote `main`  or `feature` => `main` or `feature` + `bugfix`
* Close the PR - Automatically done by GitHub
* Sync up the Local `main` with the Remote `main`
  - `git pull`
* `git status`
* `git log --oneline`
* `git log --oneline --graph` - beause it was merged from a different branch
* Optional but recommended - Delete the local bugfix branch
  `git branch -d <bugfix/<JiraID>/<branchName>`
* Mandatory - Delete the remote bug fix branch
  - Mostly you do  NOT have a command line option
  - Rather you have a "X" or "Trash Icon" to delete the branch in the GUI/Webpage.
* Deploy the code to the Server
* Test - Sanity or Regression
  - Once the tesing is succesful, you can close the ticket.
  - If the testing is NOT successful, go back to the testing in the Develpment Environment
* * Jira - change the status of the Jira ticket from `IN-PROGRESS` to `FIXED`.  

## Testing

```java
RegisterServlet - doPost() invoked
UserBO : UserBO [hashCode()=1438664425, id=0, firstName=ThejaswiniThePowerfulIronLadyInTheUniverse
, lastName=RN, userName=theju@3, password=theju@3, isActive=true]
Exception while registering the User
Error Message : Data truncation: Data too long for column 'FirstName' at row 1
Error Code : 1406
SQL State : 22001
com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'FirstName' at row 1
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:104)
	at com.mysql.cj.jdbc.ClientPreparedStatement.executeInternal(ClientPreparedStatement.java:916)
```

```java
RegisterServlet - doPost() invoked
UserBO : UserBO [hashCode()=238324188, id=0, firstName=Thejaswini
, lastName=RN, userName=theju@3, password=theju@3, isActive=true]
Exception while registering the User
Error Message : Duplicate entry 'theju@3' for key 'UserName'
Error Code : 1062
SQL State : 23000
java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'theju@3' for key 'UserName'
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:117)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
	at com.mysql.cj.jdbc.ClientPreparedStatement.executeInternal(ClientPreparedStatement.java:916)
	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1061)
	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1009)
	at com.mysql.cj.jdbc.ClientPreparedStatement.executeLargeUpdate(ClientPreparedStatement.java:1320)
	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdate(ClientPreparedStatement.java:994)
	at com.assignments.java.mems.dao.UserDAOImpl.registerUser(UserDAOImpl.java:58)
	at com.assignments.java.mems.web.RegisterServlet.doPost(RegisterServlet.java:61)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:681)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
```

## Console Logs

```sh
➜  mini-project-mems git:(bugfix/MEMS-14_DuplicateUsersHandling) git checkout main
Switched to branch 'main'
Your branch is up to date with 'origin/main'.
➜  mini-project-mems git:(main) git log --oneline
➜  mini-project-mems git:(main) ✗ git log --oneline

2c0769c (HEAD -> main, origin/main, origin/HEAD) Merge pull request #1 from raghsonlinedotcom/MEMS-1-SignUp
2879e8b (origin/MEMS-1-SignUp) MEMS-11 | SignUp - UI changes
281e6a0 MEMS-12 | SignUp - Backend changes
fa3e7e4 MEMS-10 | DB Design Completed
df647e7 Initial commit
(END)
```

```sh
➜  doc git:(bugfix/MEMS-14_DuplicateUsersHandling) ✗ git log --oneline

36c80ed (HEAD -> bugfix/MEMS-14_DuplicateUsersHandling) MEMS-14 | Bug fixed - Dupliate Users Handling
2c0769c (origin/main, origin/HEAD, main) Merge pull request #1 from raghsonlinedotcom/MEMS-1-SignUp
2879e8b (origin/MEMS-1-SignUp) MEMS-11 | SignUp - UI changes
281e6a0 MEMS-12 | SignUp - Backend changes
fa3e7e4 MEMS-10 | DB Design Completed
df647e7 Initial commit
(END)
```
## Remote Branch

`https://github.com/raghsonlinedotcom/mini-project-mems/tree/MEMS-14_DuplicateUsersHandling`

```sh
➜  doc git:(bugfix/MEMS-14_DuplicateUsersHandling) git status
On branch bugfix/MEMS-14_DuplicateUsersHandling
nothing to commit, working tree clean
➜  doc git:(bugfix/MEMS-14_DuplicateUsersHandling) git pull
From github.com:raghsonlinedotcom/mini-project-mems
 * [new branch]      MEMS-14_DuplicateUsersHandling -> origin/MEMS-14_DuplicateUsersHandling
There is no tracking information for the current branch.
Please specify which branch you want to merge with.
See git-pull(1) for details.

    git pull <remote> <branch>

If you wish to set tracking information for this branch you can do so with:

    git branch --set-upstream-to=origin/<branch> bugfix/MEMS-14_DuplicateUsersHandling

➜  doc git:(bugfix/MEMS-14_DuplicateUsersHandling) ✗
```

Verify the remote branch in the local repository.

```sh
➜  doc git:(bugfix/MEMS-14_DuplicateUsersHandling) ✗ git branch -a                       

* bugfix/MEMS-14_DuplicateUsersHandling
  main
  remotes/origin/HEAD -> origin/main
  remotes/origin/MEMS-1-SignUp
  remotes/origin/MEMS-14_DuplicateUsersHandling
  remotes/origin/main
(END)
```

```sh
➜  doc git:(bugfix/MEMS-14_DuplicateUsersHandling) ✗ git branch --set-upstream-to=origin/MEMS-14_DuplicateUsersHandling bugfix/MEMS-14_DuplicateUsersHandling
branch \'bugfix/MEMS-14_DuplicateUsersHandling\' set up to track \'origin/MEMS-14_DuplicateUsersHandling\'.
➜  doc git:(bugfix/MEMS-14_DuplicateUsersHandling) ✗ git push
fatal: The upstream branch of your current branch does not match
the name of your current branch.  To push to the upstream branch
on the remote, use

    git push origin HEAD:MEMS-14_DuplicateUsersHandling

To push to the branch of the same name on the remote, use

    git push origin HEAD

To choose either option permanently, see push.default in \'git help config\'.

To avoid automatically configuring upstream branches when their name
doesn\'t match the local branch, see option \'simple\' of branch.autoSetupMerge
in \'git help config\'.

➜  doc git:(bugfix/MEMS-14_DuplicateUsersHandling) ✗ git push origin HEAD:MEMS-14_DuplicateUsersHandling
Enumerating objects: 50, done.
Counting objects: 100% (50/50), done.
Delta compression using up to 8 threads
Compressing objects: 100% (21/21), done.
Writing objects: 100% (32/32), 11.88 KiB | 5.94 MiB/s, done.
Total 32 (delta 7), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (7/7), completed with 4 local objects.
To github.com:raghsonlinedotcom/mini-project-mems.git
   2c0769c..31a706d  HEAD -> MEMS-14_DuplicateUsersHandling
➜  doc git:(bugfix/MEMS-14_DuplicateUsersHandling) ✗
```

## Open Issues

While testing the `MEMS-18`
