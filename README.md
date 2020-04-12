# trackmypints

## Very much a WIP still, so please be gentle with criticisms x (any mention of front end not done yet)

### The premise
This is a rather simple application and that's all it's meant to be, I wanted to develop something just to practice
 doing something the whole way from front-end to database layer, and what more controversial way to do it as an Irish
 man that to actually track how much money I'm spending on pints.

Please don't tell my extended family about this or I won't be invited to weddings and stuff for fear I'll track how much they drink too.

### Stack
- Front end: React.js (Been mad to learn it for a while so why not)
- Back end: Spring Boot
- Database Layer: Mongodb modelled in Java

### Installation

trackmypints requires
- [Node.js](https://nodejs.org/) v6+ to run the front end.
- Java
- Maven for pkg mgmnt
- Mongodb

To install just compile the project with maven and run it, this is all only localy atm.


### Functionality

#### Registration
POST username and password to /register. Password is encoded and stored in credentials collection. User set as
unverified for now, will implement email verification, when this is done I will add a request filter. If the user exists
but is not verified they will get 403's until verified. If done, the can then successfully auth.

#### Authentication
Using JWT: Front end form to post credentials to /auth endpoint. If registered and verified, a JWT will be returned.
 This can then be used to access all other endpoints.

All requests are intercepted by a filter and validated for the JWT (must not be expired), if not found they are denied.

#### Model
Each PintUser has a corresponding map of PintData which tracks count and cost of each Pint, the key being the name of
the pint. Rather simple, this app just performs fetches, updates and puts.

#### Architecture

Controller > Service > DAO

### API Reference

| Enpdoint | Request Method | Parameters | Response |
| ------ | ------ | ------ | ------ |
| /register | POST | Request body must contain username, password json | 200 "User registered", 403 Access Denied |
| /auth | POST | Request body must contain username, password json | 200 with token, 403 Access Denied |
| /pints | GET | None | Map of <String, PintData> key is name of pint|
| /pint/{name}| GET | (path param) name: String (name of pint) | PintData for given pint name|
| /pint| POST | (Request params) name: String (name of pint), price: Double | void for now|
