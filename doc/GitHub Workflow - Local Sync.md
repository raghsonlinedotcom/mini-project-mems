# Git Workflow - Local Feature branch merge to Local main

Steps

* Local main - `git pull` - MUST to be in sync with Remote `main`
* Create a feature branch in local repo from the local `main` and switch to it
* Work on the changes in the Development Environment
* Test it locally
* Copy the code from the *Development* to the *Version Control* workspace
* `git status` on the feature branch to verify the contents
* `git add .`
* `git commit -m <Msg>`

> It's time to merge the changes from the feature branch to the `main` in the *local* repo.

* Switch to the `main` branch - `git checkout main`
* `git merge <featreBranch>` => `git checkout MEMS-23_Styling_MEMS26-Menu`
* `git status`
* `git log --oneline`
* `git log --oneline --graph`

## Console Outputs

*Git Status*

```sh
➜  mini-project-mems git:(MEMS-23_Styling_MEMS26-Menu) git checkout main
Switched to branch \'main\'
Your branch is up to date with \'origin/main\'.
```

> The local mais is in sync (up to date) with the remote main `origin/main`

*Git Merge*

```sh
➜  mini-project-mems git:(main) git merge MEMS-23_Styling_MEMS26-Menu
Updating 8b0b677..4f8f41f
Fast-forward
 assignment/GitHub Assignment - 10Dec2022.md |  19 +++++++++++++
 doc/.DS_Store                               | Bin 0 -> 6148 bytes
 src/main/webapp/footer.jsp                  |   6 ++++
 src/main/webapp/header.jsp                  |  11 ++++++++
 src/main/webapp/index.jsp                   |  14 ++-------
 src/main/webapp/menu.jsp                    |  22 +++++++++++++++
 src/main/webapp/register.jsp                |  10 +++++--
 src/main/webapp/style.css                   |  95 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 8 files changed, 164 insertions(+), 13 deletions(-)
 create mode 100644 assignment/GitHub Assignment - 10Dec2022.md
 create mode 100644 doc/.DS_Store
 create mode 100644 src/main/webapp/footer.jsp
 create mode 100644 src/main/webapp/header.jsp
 create mode 100644 src/main/webapp/menu.jsp
 create mode 100644 src/main/webapp/style.css
➜  mini-project-mems git:(main)
```

*Git status*

```sh
➜  mini-project-mems git:(main) git status
On branch main
Your branch is ahead of 'origin/main' by 1 commit.
  (use "git push" to publish your local commits)

nothing to commit, working tree clean
➜  mini-project-mems git:(main)
```

> Note: The local `main` is now *ahead* of 1 commit with the Remote `origin/main`

*Git Log Online*

```sh
➜  mini-project-mems git:(main) ✗ git log --oneline        

4f8f41f (HEAD -> main, MEMS-23_Styling_MEMS26-Menu) MEMS-23-Styling_MEMS-26-Menu Changes completed
8b0b677 (origin/test-branch, origin/main, origin/HEAD) Merge pull request #4 from raghsonlinedotcom/MEMS-18_MissingLib_MEMS-19_UIValidation
4341edc (origin/MEMS-18_MissingLib_MEMS-19_UIValidation) MEMS-18_MEMS-19_CodeCopyIssue-fixed
3f8a98b Merge pull request #3 from raghsonlinedotcom/MEMS-18_MissingLib_MEMS-19_UIValidation
125f242 MEMS-18_MissingLib_MEMS-19_UIValidation - completed
29a3e53 Merge pull request #2 from raghsonlinedotcom/MEMS-14_DuplicateUsersHandling
6bd0c11 (origin/MEMS-14_DuplicateUsersHandling) Bugs.md - Console logs added for Remote Add, push
31a706d Bugs.md - added with Console Logs
36c80ed MEMS-14 | Bug fixed - Dupliate Users Handling
2c0769c Merge pull request #1 from raghsonlinedotcom/MEMS-1-SignUp
2879e8b (origin/MEMS-1-SignUp) MEMS-11 | SignUp - UI changes
281e6a0 MEMS-12 | SignUp - Backend changes
fa3e7e4 MEMS-10 | DB Design Completed
df647e7 Initial commit
(END)
```

*Git Log Graph*

```sh
➜  mini-project-mems git:(main) git log --oneline --graph

* 4f8f41f (HEAD -> main, MEMS-23_Styling_MEMS26-Menu) MEMS-23-Styling_MEMS-26-Menu Changes completed
*   8b0b677 (origin/test-branch, origin/main, origin/HEAD) Merge pull request #4 from raghsonlinedotcom/MEMS-18_MissingLib_MEMS-19_UIValidation
|\  
| * 4341edc (origin/MEMS-18_MissingLib_MEMS-19_UIValidation) MEMS-18_MEMS-19_CodeCopyIssue-fixed
* | 3f8a98b Merge pull request #3 from raghsonlinedotcom/MEMS-18_MissingLib_MEMS-19_UIValidation
|\|
| * 125f242 MEMS-18_MissingLib_MEMS-19_UIValidation - completed
|/  
*   29a3e53 Merge pull request #2 from raghsonlinedotcom/MEMS-14_DuplicateUsersHandling
|\  
| * 6bd0c11 (origin/MEMS-14_DuplicateUsersHandling) Bugs.md - Console logs added for Remote Add, push
| * 31a706d Bugs.md - added with Console Logs
| * 36c80ed MEMS-14 | Bug fixed - Dupliate Users Handling
|/  
*   2c0769c Merge pull request #1 from raghsonlinedotcom/MEMS-1-SignUp
|\  
| * 2879e8b (origin/MEMS-1-SignUp) MEMS-11 | SignUp - UI changes
| * 281e6a0 MEMS-12 | SignUp - Backend changes
| * fa3e7e4 MEMS-10 | DB Design Completed
|/  
* df647e7 Initial commit
(END)
```
