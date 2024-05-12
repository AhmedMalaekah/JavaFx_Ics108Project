# KFUPM Event
## Table of Contents
- [Description](#description)
- [Technologies Used](#technologies-used)
- [Design Overview](#design-overview)
- [Usage](#usage)
## Description
KFUPM Event is a simple JavaFX application designed to manage events at KFUPM. It allows users to book events, view their tickets, and cancel events they have booked. The system also provides administrative
features for adding, updating and deleting events.
## Technologies Used
- **Java**: Core programming language used for building the application logic.
- **JavaFX**: Graphical User Interface (GUI) toolkit for Java used to create the desktop
application interface.
## Design Overview
The application is structured into several classes, each responsible for specific aspects of the
application:
- **Main**: The driver class of the application which launches the JavaFX application.
  - **Variables**: `double WIN_WIDTH, double WIN_HEIGHT, double HEADING_SIZE, Font HEADING_FONT, Insets PADDING, User currentUser`
  - **Methods**: `start(Stage applicationStage), stop(), loginPage(Stage applicationStage), registerPage(Stage applicationStage), homePage(Stage applicationStage), adminPage(Stage applicationStage), editCreateEventPage(Stage applicationStage, Event event),  myTicketPage(Stage applicationStage), navbar(Stage applicationStage), eventBox(Event event, boolean inMyTicket, Integer ticketNum, boolean inEvent, boolean adminPanel, Stage applicationStage), eventsBlock(Stage applicationStage), eventInput(Event event, Stage applicationStage)`
- **Event**: Event class for storing events details and modify them
  - **Variables**: `String title, String category, String description, LocalDate date, String time, String Location, ArrayList<Ticket> tickets, ArrayList<Integer> ticketsNum, int capacityNum, User user, ArrayList<Event> events`
  - **Methods**: `Event(String title, String category, String description, LocalDate date, String time, String location, int capacityNum,User user), editEvent(String title, String category, String description, LocalDate date, String time, String location ,User user), createEvent(String title, String category, String description, LocalDate date, String time, String location, int capacityNum, User user), addTicket(User user), delTicket(Integer seatNum), setTicketsNum(), getTicketNum(), clearTickets(), getEvents(), delEvent(Event event), isUpcoming(), getTitle(), setTitle(String title), getDateString(), getCategory(), setCategory(String category), getDescription(), setDescription(String description), getDate(), getCapacityNum(), setCapacityNum(int capacityNum), getNumTicketsAvailable(), getTime(), setTime(String time), getUser(), setUser(User user), getLocation(), setLocation(String location), getTickets(), loadEvents()`
- **User**: User class for storing user information
  - **Variables**: `String username, String password, boolean admin, ArrayList<User> users, ArrayList<String> usernames`
  - **Mehtods**: `User(String username, String passwaord, boolean admin), createUser(String username, String passwaord, boolean admin), checkPassword(String passwaord), validUsername(String username), isAdmin(), getUsers(), getUsernames(), getUsername(), toString(), loadUsers()`
- **Ticket**: Ticket class for storing tickets information
  - **Variables**: `String username, Integer seatNum`
  - **Mehtods**: `Ticket(String username, Integer seatNum), getUsername(), getSeatNum()` 
## Usage
### Running the Application
To run the application, you will need to have Java and JavaFX set up on your machine. Once
installed, you can compile and run the application from your IDE.
### Using the Application
1. **Register**: Start by register in the application using your user credentials.
2. **Login**: login in using your user credentials.
3. **Viewing Events**: Navigate to the 'Events' section to see available events.
4. **Book an Event**: Click on the book button for the event you wish to attend.
5. **View your tickets**: Under your 'My Tickets' section, view your booked events and cancel any you wish to not attend.
6. **Admin Panel**: If you are an admin, you can go to 'Admin Panel' section to create, edit or delete events.
7. **Admin Panel**: In the 'Admin panel' section, select 'Delete' button to delete the event.
8. **Create Event**: In the 'Admin panel' section, select 'Create Event' button and add your event details to add a new event.
9. **Edit Event**: In the 'Admin panel' section, select 'Edit' button and change the event values.
