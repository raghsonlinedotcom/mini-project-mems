## RAD

Rapid Application Development - by IDEs

Integrated
Development
Environment

## Git Branching

Different flavors

## Git Branch Commands

## List down the local branches
`git branch`
`git branch --list`

```sh
* main
```

> The * indicates the current / active branch in the repository

## List down all the branches including Remote

`git branch -a`

```sh
* main
  remotes/origin/HEAD -> origin/main
  remotes/origin/main
```

## Create a new branch

`git branch <branchName>` - just creates a branch and stays in the current/same branch.
We need to manually switch to the newly created branch using `git checkout <branch>` command.

## Create a new branch and switch

`git checkout -b <branchName>`

```sh
➜  mini-project-mems git:(main) git checkout -b MEMS-1-SignUp
Switched to a new branch 'MEMS-1-SignUp'
➜  mini-project-mems git:(MEMS-1-SignUp)
```

Now issue a `git branch` to list down all the local branches

```sh
➜  mini-project-mems git:(MEMS-1-SignUp) git branch
* MEMS-1-SignUp
  main
```

```sh
➜  mini-project-mems git:(MEMS-1-SignUp) git status
On branch MEMS-1-SignUp
nothing to commit, working tree clean
```

```sh
➜  mini-project-mems git:(MEMS-1-SignUp) cp -r /Users/raghavan.muthu/eclipse-workspace/MEMS/* .
➜  mini-project-mems git:(MEMS-1-SignUp) ✗ git status
On branch MEMS-1-SignUp
Untracked files:
  (use "git add <file>..." to include in what will be committed)
	src/

nothing added to commit but untracked files present (use "git add" to track)
➜  mini-project-mems git:(MEMS-1-SignUp) ✗
```


```sh
➜  mini-project-mems git:(MEMS-1-SignUp) git fetch
From github.com:raghsonlinedotcom/mini-project-mems
 * [new branch]      MEMS-1-SignUp -> origin/MEMS-1-SignUp
➜  mini-project-mems git:(MEMS-1-SignUp) git branch  
➜  mini-project-mems git:(MEMS-1-SignUp) git branch -a
➜  mini-project-mems git:(MEMS-1-SignUp) git pull
There is no tracking information for the current branch.
Please specify which branch you want to merge with.
See git-pull(1) for details.

    git pull <remote> <branch>

If you wish to set tracking information for this branch you can do so with:

    git branch --set-upstream-to=origin/<branch> MEMS-1-SignUp

➜  mini-project-mems git:(MEMS-1-SignUp) git branch --set-upstream-to=origin/MEMS-1-SignUp MEMS-1-SignUp
branch 'MEMS-1-SignUp' set up to track 'origin/MEMS-1-SignUp'.
➜  mini-project-mems git:(MEMS-1-SignUp) git log --oneline
2879e8b (HEAD -> MEMS-1-SignUp) MEMS-11 | SignUp - UI changes
281e6a0 MEMS-12 | SignUp - Backend changes
fa3e7e4 MEMS-10 | DB Design Completed
df647e7 (origin/main, origin/MEMS-1-SignUp, origin/HEAD, main) Initial commit
```

```sh
➜  mini-project-mems git:(MEMS-1-SignUp) git push
Enumerating objects: 51, done.
Counting objects: 100% (51/51), done.
Delta compression using up to 8 threads
Compressing objects: 100% (26/26), done.
Writing objects: 100% (50/50), 8.29 KiB | 4.14 MiB/s, done.
Total 50 (delta 4), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (4/4), done.
To github.com:raghsonlinedotcom/mini-project-mems.git
   df647e7..2879e8b  MEMS-1-SignUp -> MEMS-1-SignUp
➜  mini-project-mems git:(MEMS-1-SignUp) git stats
git: 'stats' is not a git command. See 'git --help'.

The most similar command is
	status
➜  mini-project-mems git:(MEMS-1-SignUp) git status
On branch MEMS-1-SignUp
Your branch is up to date with 'origin/MEMS-1-SignUp'.

nothing to commit, working tree clean
➜  mini-project-mems git:(MEMS-1-SignUp)
```

```sh
➜  mini-project-mems git:(MEMS-1-SignUp) git log --oneline

2879e8b (HEAD -> MEMS-1-SignUp, origin/MEMS-1-SignUp) MEMS-11 | SignUp - UI changes
281e6a0 MEMS-12 | SignUp - Backend changes
fa3e7e4 MEMS-10 | DB Design Completed
df647e7 (origin/main, origin/HEAD, main) Initial commit
```

## Pull Requests

`https://github.com/raghsonlinedotcom/mini-project-mems/pull/1`

```sh
➜  mini-project-mems git:(MEMS-1-SignUp) git fetch
remote: Enumerating objects: 1, done.
remote: Counting objects: 100% (1/1), done.
remote: Total 1 (delta 0), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (1/1), 644 bytes | 644.00 KiB/s, done.
From github.com:raghsonlinedotcom/mini-project-mems
   df647e7..2c0769c  main       -> origin/main
➜  mini-project-mems git:(MEMS-1-SignUp) git pull
Already up to date.
➜  mini-project-mems git:(MEMS-1-SignUp)
```

```sh
➜  mini-project-mems git:(MEMS-1-SignUp) git checkout main
Switched to branch 'main'
Your branch is behind 'origin/main' by 4 commits, and can be fast-forwarded.
  (use "git pull" to update your local branch)
➜  mini-project-mems git:(main) git status
On branch main
Your branch is behind 'origin/main' by 4 commits, and can be fast-forwarded.
  (use "git pull" to update your local branch)

nothing to commit, working tree clean
➜  mini-project-mems git:(main) git log --oneline
➜  mini-project-mems git:(main) git pull
Updating df647e7..2c0769c
Fast-forward
 doc/MEMS - DB Design.md                                          |  37 +++++++++++
 doc/MiniProject - MEMS - Agile Fashioned.md                      |  78 +++++++++++++++++++++++
 README.md => doc/README.md                                       |   0
 src/main/db/MEMS - DB Design.sql                                 |  23 +++++++
 src/main/java/com/assignments/java/mems/bo/UserBO.java           | 136 ++++++++++++++++++++++++++++++++++++++++
 src/main/java/com/assignments/java/mems/dao/UserDAO.java         |   9 +++
 src/main/java/com/assignments/java/mems/dao/UserDAOImpl.java     |  80 +++++++++++++++++++++++
 src/main/java/com/assignments/java/mems/db/DBConnection.java     |  37 +++++++++++
 src/main/java/com/assignments/java/mems/web/RegisterServlet.java |  60 ++++++++++++++++++
 src/main/test/test/assignments/java/mems/bo/UserBOTest.java      |  12 ++++
 src/main/test/test/assignments/java/mems/dao/UserDAOTest.java    |  31 +++++++++
 src/main/webapp/META-INF/MANIFEST.MF                             |   3 +
 src/main/webapp/index.jsp                                        |  12 ++++
 src/main/webapp/register.jsp                                     |  68 ++++++++++++++++++++
 14 files changed, 586 insertions(+)
 create mode 100644 doc/MEMS - DB Design.md
 create mode 100644 doc/MiniProject - MEMS - Agile Fashioned.md
 rename README.md => doc/README.md (100%)
 create mode 100644 src/main/db/MEMS - DB Design.sql
 create mode 100644 src/main/java/com/assignments/java/mems/bo/UserBO.java
 create mode 100644 src/main/java/com/assignments/java/mems/dao/UserDAO.java
 create mode 100644 src/main/java/com/assignments/java/mems/dao/UserDAOImpl.java
 create mode 100644 src/main/java/com/assignments/java/mems/db/DBConnection.java
 create mode 100644 src/main/java/com/assignments/java/mems/web/RegisterServlet.java
 create mode 100644 src/main/test/test/assignments/java/mems/bo/UserBOTest.java
 create mode 100644 src/main/test/test/assignments/java/mems/dao/UserDAOTest.java
 create mode 100644 src/main/webapp/META-INF/MANIFEST.MF
 create mode 100644 src/main/webapp/index.jsp
 create mode 100644 src/main/webapp/register.jsp
➜  mini-project-mems git:(main)
```

```sh
➜  mini-project-mems git:(main) git status
On branch main
Your branch is up to date with 'origin/main'.

nothing to commit, working tree clean
➜  mini-project-mems git:(main) git log --oneline
➜  mini-project-mems git:(main) git log --oneline

2c0769c (HEAD -> main, origin/main, origin/HEAD) Merge pull request #1 from raghsonlinedotcom/MEMS-1-SignUp
2879e8b (origin/MEMS-1-SignUp, MEMS-1-SignUp) MEMS-11 | SignUp - UI changes
281e6a0 MEMS-12 | SignUp - Backend changes
fa3e7e4 MEMS-10 | DB Design Completed
df647e7 Initial commit
```

```sh
➜  mini-project-mems git:(main) git branch -d MEMS-1_SignUp
error: branch 'MEMS-1_SignUp' not found.
➜  mini-project-mems git:(main) git branch -d MEMS-1-SignUp
Deleted branch MEMS-1-SignUp (was 2879e8b).
➜  mini-project-mems git:(main) git branch -a
➜  mini-project-mems git:(main) git branch -a

* main
  remotes/origin/HEAD -> origin/main
  remotes/origin/MEMS-1-SignUp
  remotes/origin/main
```

### Git Log - Graph - with  a feature branch

```sh
➜  mini-project-mems git:(main) ✗ git log --oneline --graph

*   2c0769c (HEAD -> main, origin/main, origin/HEAD) Merge pull request #1 from raghsonlinedotcom/MEMS-1-SignUp
|\  
| * 2879e8b (origin/MEMS-1-SignUp) MEMS-11 | SignUp - UI changes
| * 281e6a0 MEMS-12 | SignUp - Backend changes
| * fa3e7e4 MEMS-10 | DB Design Completed
|/  
* df647e7 Initial commit
```

## Util Command

`➜  mini-project-mems git:(main) ✗ history | grep cp`

> to get the copy command issued earlier - from the  history. 
