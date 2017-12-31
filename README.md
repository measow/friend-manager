Requirements:
------------------------

1.) Login and Registration with validations. Validation errors should appear on the page.

    Login fields: Email, Password    
    Registration fields: Name, Alias, Email, Password (8 chars min), DOB

2.) View each user's profile

3.) If the logged user does not have friends yet, the table/top part should say "You don't have friends yet."

4.) Add/Remove Friend feature. When a user has been added as a friend. 
    The logged user is also added on the newly added user's list of friends.
    
5.) Updating of two tables if a user has been added as a friend or has been removed as a friend.

6.) Logout

API Endpoints:
------------------------

POST
log in user
on success, returns 200 and creates session cookie 
on failure, returns 401 
/api/security/login

POST
log in user
/api/security/logout

POST
create new user
/api/users

GET
get all users
/api/users

GET
get single user
/api/users/{userId}

GET
get friends for user 
/api/{userId}/friends

GET
get non-friends for user 
/api/{userId}/others