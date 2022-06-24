# project-b2-g101

Project Name: Pension-Management system.

Team Members:Devadharshan M, Prosenjit Singha, Bhanukiran Bhavirisetti, Sekh Abdul Kader, Sai Rama Krishna Potlakayala

Project Overview:Pension management system is created for State government aims to automate a portion of the Pension detail provisioning.
 		 This project covers pensioner detail provision, calculate provision and view for further processing.

This project covers pensioner detail provision, calculate provision and view for further processing.
About:

     Users will be able to:
        Login/SignUp to the portal.
        Can get the pension charges from their details.

Build details:

      Spring boot microservice architecture
      Eureka discovery server
      Api gateway for centralized routing
      Spring security for auth service
      JWT validation for extra security
      Run Application

Total Microservices:

A.Authorization Microservice

B.Pensioner detail Microservice

C.Process Pension Microservice

D.Api Gateway

E.Eureka Server

Authorization Microservice:

	Main Functionality:To Generate the token and Validate the token.

	Step-1:Open Postman and follow the steps below:

	Step-2:Go to Url section and paste---> http://localhost:8081/auth

	Method:POST

	Step-3: Body Section: { "user":"Manager1", "password":"pwd1" }

	Step-4: Click Send button, then a token will be generated. Copy the token.

	Step-5:And see the details of token in jwt.io

 Authorization Service This service is responsible to provide login access to the application and provide security to it with the help of stateless authentication using JWT Tokens.

Eureka Server:
 The Eureka Server is responsible for registering all the microservices together so that they can communicate with each other with the help of their application names instead of their IP address which may be dynamic in nature.

This service provides two controller END-POINTS: Open your spring boot application and run the service. Select the authorization controller header Login functionality Select login POST method and click try it out Then enter these correct username and password credentials as follows: { "username": "Manager1", "password": "pw1" } Then hit execute and you will see a JWT Token generated. Copy this token to be used in the next step. For these incorrect credentials: { "username": "process", "password": "wrongpassword" } Response { "message": "Incorrect Username or Password",  "fieldErrors": [ "Incorrect Username or Password" ] } Validation functionality Select validation POST method and click try it out Then enter previously generated valid Token that you had copied into the Authorization header. Then hit execute and you would see true in the response body. If the token in invalid the application throws an appropriate error response related to either Token expired, Token malformed or Token signature incorrect.

Pensioner Detail Service Description:
	This microservice is responsible for Provides information about the registered pensioner detail i.e., Pensioner name, PAN, bank name, bank account number, bank type – private or public Steps and Action.

 This Microservice is to fetch the pensioner detail by the Aadhaar number.Flat file(CSV file with pre-defined data) should be created as part of the Microservice. This file has to contain data for 20 Pensioners. This has to be read and loaded into List for ALL the operations of the microservice. Endpoint url- http://localhost:8082/Detail/878346475. This endpoint accept the user request and provides the Pensioner details. Access this using the POSTMAN client.
Input - Aadhaar Number => 878346475.
Valid Response:
{ "name": "G101", "dateOfBirth": "1945-06-07", "pan": "PQDS546", "salary": 26000, "allowance": 10000, "pensionType": "self", "bank": { "bankName": "ICICI", "accountNumber": 12345678, "bankType": "private" } } 
Invalid Response:
{ "message": "Aadhaar Number Not Found", "fieldErrors": [ "Aadhaar Number Not Found" ] }.

Process Pension Service Description: 
	This microservice is responsible for verifying pension amount and bank charges for provided aadhaar number.  Single endpoint is present on the controller to process this request

Endpoint: This endpoint only accept authenticated request so make sure that there is is valid token present in "Authentication" header. Use AUTH-SERVICE to generate tokens Valid Input { "aadhaarNumber":"878346475", "pensionAmount":"31600", "bankServiceCharge":"550" } Invalid Input { "aadhaarNumber":"878346475", "pensionAmount":"31000", "bankServiceCharge":"550" }.

If details are valid, pension amount and bankCharges is correct then user will following response with code "10" else "21"

Response: { "processPensionStatusCode": 10 } If request format is invalid then following error response is sent back

{ "message": "Invalid Request" "fieldErrors": [ "Aadhaar number can not be left blank", "pensionAmount is invalid" ] } If aadhaar number does not exist or there is some internal server error then it will send an following error resonse

{"message": "Invalid Request"}

Process Pension Service It takes in the pensioner detail like the name, aadhaar number, pan detail, self or family or both type of pension Verifies if the pensioner detail is accurate by getting the data from PensionerDetail Microservice or not. If not, validation message “Invalid pensioner detail provided, please provide valid detail.” If valid, then pension calculation is done and the pension detail is returned. This service provides two controller end-points: Open your spring boot application and run the service.
This endpoint only accept authenticated request so make sure that there is is valid token present in "Authentication" header. Use AUTH-SERVICE to generate tokens

Get Pension Details functionality Select /pensionerInput POST method and click try it out Valid Input

{ "aadhaarNumber": "878346475", "dateOfBirth": "1945-06-07", "name": "G101", "pan": "PQDS546", "pensionType": "self" } Response for valid input

{ "name": "DURAI", "dateOfBirth": "12/09/1956", "pan": "BHMER12436", "pensionType": "self", "pensionAmount": 76436 } Invalid Input

{ "aadhaarNumber": "878346475", "dateOfBirth": "1945-06-07", "name": "G101", "pan": "PQDS546", "pensionType": "family" } Response for invalid input

{ "message": "Details entered are incorrect", "timestamp": "2021-08-03T10:50:58.1047198", "fieldErrors": [ "Details entered are incorrect" ] } Invalid Input - wrong Aadhaar number

{ "aadhaarNumber": "878346434", "dateOfBirth": "1945-06-07", "name": "G101", "pan": "PQDS546", "pensionType": "family" } Response for invalid input

{ "message": "Aadhaar Number Not Found", "timestamp": "2021-08-03T10:51:36.344356", "fieldErrors": [ "Aadhaar Number Not Found" ] } Response for expired token

{ "message": "Token has been expired", "timestamp": "2021-08-03T10:54:10.5174319", "fieldErrors": [ "Token has been expired" ] } Process Pension functionality Select /processPension POST method and click try it out Status code of 10 for valid input.

{ "aadhaarNumber": "878346475", "bankServiceCharge": 550, "pensionAmount": 76436 } Response

{ "processPensionStatusCode": 10 } Invalid Input

{ "aadhaarNumber": "878346475", "bankServiceCharge": 550, "pensionAmount": 7326586 } Response

{ "processPensionStatusCode": 21 } Invalid Input - wrong aadhaar number

{ "aadhaarNumber": "878346434", "bankServiceCharge": 550, "pensionAmount": 76436 } Response

{ "message": "Aadhaar Number Not Found", "timestamp": "2021-08-03T10:58:31.3557242" }

Thanks
