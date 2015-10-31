# Customer Inquiry Form 
A proof of concept stand-alone website with a form where users can submit questions / express interest. The collected data will be directly stored in a Salesforce instance.

* Each valid form submission will be pushed into Salesforce as a new `Lead`. The following fields against `Lead` are considered; FirstName, LastName, Company, Phone, Email, Description, LeadSource and Rating.
* You will need a Salesforce account to be able to login and see new `Leads` being created.


## Obtaining
```sh
~$ git clone git://github.com/solnet-ead/salesforce-webform.git salesforce-webform
```


## Configuration
Locate `application.properties` under `src/main/resources/config` where configurations can be changed, the most relevant options will probably be the user credentials.
* `salesforce.user.name`
* `salesforce.user.password`
* `salesforce.user.securitytoken` - Leave this blank if the Salesforce organization has *"Relax IP restrictions"*, otherwise supply the token. If you do not know your security token, you need to login to Salesforce and click your name on the upper right -> My Settings -> Personal -> Reset My Security Token. The token will be emailed to you.


## Launching
```sh
# Build the project using Maven.
~$ cd salesforce-webform
~$ mvn clean install
# Launch with Maven.
~$ mvn spring-boot:run -Dserver.port=<Port>
# Alternatively, you can launch from a packaged JAR file.
~$ mvn package
~$ java -jar target/salesforce-webform-0.1.0.jar --server.port=<port>
```

You may omit *server.port=<Port>* in which case the port number will default to 8080. The website should be accessible at `http://localhost:<port>/contact`. After successfully submitted a form, login to Salesforce and navigate the dashboard to `Leads` - you should see new entries appear.


## Reference resources
* [Link](https://help.salesforce.com/apex/HTViewHelpDoc?id=connected_app_create.htm&language=en_US) - Creating a Connected App.
* [Link](https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/api_rest/intro_understanding_username_password_oauth_flow.htm) - Username-Password OAuth Authentication.
* [Link](https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/api_rest/using_resources_working_with_object_metadata.htm) - Utilize metadata to understand the structure of sObject we are intersted in.
* [Link](https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/api_rest/dome_sobject_create.htm) - Create new sObject in salesforce.
* [Link](https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/api_rest/errorcodes.htm) - Brief summary of api status and error responses.

