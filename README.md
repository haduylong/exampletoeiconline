# Introduction
Developing English Test website that helps:

* User can learn and do English tests online.
* Admin can manage and provide lessons and tests

# Overview
This section discusses some information about the project. More details are shown in [the report](ApplicationFile/assets/report.pdf).

## Demo
### Sign in/up
<img src="ApplicationFile/assets/login.png" alt="Login">

### Admin
<div style="display:flex";>
    <img style="width:49%;height:280px" src="ApplicationFile/assets/admin.png" alt="Admin">
    <img style="width:49%;height:280px" src="ApplicationFile/assets/admin_manage_listen_guideline.png" alt="admin_manage_listen_guideline">
</div>
<div style="display:flex";>
    <img style="width:49%;height:280px" src="ApplicationFile/assets/admin_manage_user.png" alt="admin_manage_user">
    <img style="width:49%;height:280px" src="ApplicationFile/assets/admin_manage_user_upload.png" alt="admin_manage_user_upload">
</div>

### User
<div style="display:flex";>
    <img style="width:32%;height:170px" src="ApplicationFile/assets/user.png" alt="User">
    <img style="width:32%;height:170px" src="ApplicationFile/assets/user_exam.png" alt="user_exam">
    <img style="width:32%;height:170px" src="ApplicationFile/assets/user_exam_2.png" alt="user_exam_2">
</div>
<div style="display:flex";>
    <img style="width:32%;height:170px" src="ApplicationFile/assets/user_exam_3.png" alt="user_exam_3">
    <img style="width:32%;height:170px" src="ApplicationFile/assets/user_exersite.png" alt="user_exersite">
    <img style="width:32%;height:170px" src="ApplicationFile/assets/user_exersite_1.png" alt="user_exersite_1">
</div>
<div style="display:flex";>
    <img style="width:32%;height:170px" src="ApplicationFile/assets/user_guideline.png" alt="user_guideline">
    <img style="width:32%;height:170px" src="ApplicationFile/assets/user_guideline_1.png" alt="user_guideline_1">
</div>

## Database
<figure style="text-align: center;">
    <img src="ApplicationFile/assets/erd_database.png"
         alt="ER Diagram" width="800" height="auto">
    <figcaption>ER Diagram</figcaption>
</figure>

## Directory organization
<figure style="text-align: center;">
    <img src="ApplicationFile/assets/directory_organization.png"
         alt="Directory organization">
    <figcaption> </figcaption>
</figure>


* [ApplicationFile/database](ApplicationFile/database) contains sql files. Excute them in the order given by their name (ex: 1, 2 ...).
* Business logic: dto, service, dao, utils, data
* View: web
* Controller: web-logic
* Model: persistence

## Design pattern
<b><i>toeic-core-data, toeic-core-dao, toeic-core-dao-impl</i></b>. These three packages are used to implement Data Access Object pattern.

Singleton pattern is implemented by class in different util folder.
