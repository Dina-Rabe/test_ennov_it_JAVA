# Ticket Controller

This is a Spring Boot controller responsible for managing tickets.

## Endpoints

### Retrieve All Tickets

- **URL**: `/tickets`
- **Method**: GET
- **Description**: Get all tickets.
- **Response**: Returns a list of all tickets.

### Get Ticket by ID

- **URL**: `/tickets/{id}`
- **Method**: GET
- **Description**: Get a ticket by its ID.
- **Parameters**:
  - `{id}`: The ID of the ticket.
- **Response**: Returns the ticket with the specified ID.

### Create Ticket

- **URL**: `/tickets`
- **Method**: POST
- **Description**: Create a new ticket.
- **Request Body**: JSON representation of the ticket object.
- **Response**: Returns the created ticket.

### Update Ticket

- **URL**: `/tickets/{id}`
- **Method**: PUT
- **Description**: Update a ticket by its ID.
- **Parameters**:
  - `{id}`: The ID of the ticket to update.
- **Request Body**: JSON representation of the updated ticket object.
- **Response**: Returns the updated ticket.

### Assign Ticket to User

- **URL**: `/tickets/{id}/assign/{userId}`
- **Method**: PUT
- **Description**: Assign a ticket to a user.
- **Parameters**:
  - `{id}`: The ID of the ticket to assign.
  - `{userId}`: The ID of the user to assign the ticket to.
- **Response**: Returns the updated ticket.

### Delete Ticket

- **URL**: `/tickets/{id}`
- **Method**: DELETE
- **Description**: Delete a ticket by its ID.
- **Parameters**:
  - `{id}`: The ID of the ticket to delete.
- **Response**: No content.

# User Controller

This is a Spring Boot controller responsible for managing users.

## Endpoints

### Retrieve All Users

- **URL**: `/users`
- **Method**: GET
- **Description**: Get all users.
- **Response**: Returns a list of all users.

### Get Tickets by User ID

- **URL**: `/users/{id}/ticket`
- **Method**: GET
- **Description**: Get all tickets associated with a user.
- **Parameters**:
  - `{id}`: The ID of the user.
- **Response**: Returns a list of tickets associated with the specified user.

### Create User

- **URL**: `/users`
- **Method**: POST
- **Description**: Create a new user.
- **Request Body**: JSON representation of the user object.
- **Response**: Returns the created user.

### Update User

- **URL**: `/users/{id}`
- **Method**: PUT
- **Description**: Update a user by its ID.
- **Parameters**:
  - `{id}`: The ID of the user to update.
- **Request Body**: JSON representation of the updated user object.
- **Response**: Returns the updated user.

Please note that the actual implementation of the `TicketService` and `UserService` classes is not provided in this README.md file. You will need to implement these services separately according to your application's requirements.