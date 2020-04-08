# trackmypints

### The premise
This is a rather simple application and that's all it's meant to be, I wanted to develop something just to practice
 doing something the whole way from front-end to database layer, and what more controversial way to do it as an Irish
 man that to actually track how much money I'm spending on pints.

Please don't tell me extended family about this or I won't be invited to weddings and stuff for fear I'll track how much they drink too.

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

#### Authentication
Using JWT: Currently only supported for one user as I havent implemented a DB for users yet, want to focus on having all the
 functionality running on back end and front end first.
 Basically the front end client will handle the tokens, but any endpoint access redirects via Spring Security,
 this prompts the client for a username and password to POST to /auth. If a valid user: a JWT is returned.

All requests are intercepted by a filter and validated for the JWT, if not found they are denied.

#### Model
Each PintUser has a corresponding map of PintData which tracks count and cost of each Pint, the key being the name of
the pint. Rather simple, this app just performs fetches, updates and puts.

#### Architecture

Controller > Service > DAO

### API Reference

| Enpdoint | Request Method | Parameters | Response |
| ------ | ------ | ------ | ------ |
| /auth | POST | Request body must contain username, password json | 200 with token, 403 Access Denied |
| /pints | GET | None | Map of <String, PintData> key is name of pint|
| /pint/{name}| POST | name: String (name of pint) | void (for now)|