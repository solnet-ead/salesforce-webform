# Please refer to README files of sub-project(s).

### Front End Update
#####  2015-11-02
1. Scafolding grunt and bower onto app
2. Core Angular module is used for form validation, there are more coming
3. update email input to use Angular directive
4. before mvn clean install, please run following

  - npm install
  - bower install
  - grunt


#####  2015-11-05
1. add bootstrap validator to form
2. restructured css for UI
3. new branch 'origin_no_frontend' is created for later reference
4. defect found: thymeleaf data binding no longer working, which need further root cause debugging
5. steps to install

  - npm install
  - bower install
  - grunt -v
  - mvn clean install
  - mvn spring-boot:run