# Please refer to README files of sub-project(s).

### Front End Update
#### All javascript updates / modules require Nodejs engine, which can be download from https://nodejs.org/

####  **2015-11-02**
1. Scafolding grunt and bower onto app
2. Core Angular module is used for form validation, there are more coming
3. update email input to use Angular directive
4. before mvn clean install, please run following

  - npm install
  - bower install
  - grunt


####  **2015-11-05**
1. add bootstrap validator to form
2. restructured css for UI update
3. new branch **'origin_no_frontend'** is created for later reference
4. steps to install

  - npm install
  - bower install
  - grunt -v
  - mvn clean install
  - mvn spring-boot:run


####  **2015-11-06**
1. minified **app.js** to **app.min.js** with solnet copyright banner 
2. updated package.json to run bower install **ONLY** once by npm:postinstall
3. deleted no-used files
4. steps to install
  - npm install
  - ~~bower install~~
  - grunt -v
  - mvn clean install
  - mvn spring-boot:run

##### **improvement space: integrate maven commands into grunt**

 
####  **2015-11-09**
plug Bootstrap modal into glyph-icon link with project **intro** pop-up

