# Mini Project - MEMS

```
Mini
Email
Messaging
System
```
## Version History

| Sl # | Version | Date | Changes |
| ---- | ------- | ---- | ------- |
| 1    | 1.0     | 05 Dec 22 | Initial Version |

## Modules / Backlog Entries

(*) Sign Up / Register
* Login
* Dashboard / Inbox
* Read Email  (R)
* Compose Email (C)
  - One to One
  - {*} One to Many (If time permits - Broadcast to a DL (Distribution List))
* Reply Email (C)
* Delete Email (D)
* Draft Email (C)
* Logout

## Strategy / Plan of Action

(*) Jira Account Creation
  - raghs.online.001@gmail.com / RaghsOnline001Jira!
  - Domain Name : `raghsonlinedotcom.atlassian.net`
    - https://raghsonlinedotcom.atlassian.net/jira/software/projects/MEMS/boards/1/backlog
    - Key : *MEMS* - Unique per project
* Confluence - subsequently
* Create a Sprint and Tasks for the first sprint (User Creation)
* Integration of tools (Slack, GitHub etc., ) with Jira - webhook

## Terminologies

* Board in Jira - Methodology and tool combined
  - Options - Kanban, Scrum etc.,
  - We chose Scrum.
* Backlog - A registry where all the requirements are stored
* Product Backlog
  - The items in the backlog can be prioritized for the timelines to pick up
  - The items can be groomed - detailed discussion can take place when it is ready with all the necessary details
  - The items in the backlog are generally of a long term goal, as long as there is no suitable clarity on the requirements
* Sprint - An Unit of time which is roughly 2 weeks (can go upto 4 weeks as well depends on the items in scope) with the list of items can be completed in the duration.
* Sprint Backlog
  - The number of items that can be completed in a particular sprint and all the items should have the necessary clarity.
* Roles
  - Produt Owner - The one who owns the product (Business), can operate the Jira board
  - Scrum Master - the one who runs the show and operates the Jira board
  - Scrum Team - the entire team (Dev, UI, Test)
* User Story
  - A particular requirement / task correponds to a Jira ID. (MEMS-1)
* EPIC
  - A logical grouping to cover up the related tasks

## Backlog

* SignUp - https://raghsonlinedotcom.atlassian.net/browse/MEMS-1


## Sprint Backlog

### Sign Up / Register

(*) Database Design
* Sign Up - UI
(*) Backend - Java
  - Create a BO (POJO)
  - Added a Test class
  - Add the JDBC Library
  - Create the DAO Class
  - Add the DAOTest Class
