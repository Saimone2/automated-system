# automated-system


## TASK

Implement a console application in Java. The subject area of application is apartment management. The application must be built according to the MVC pattern and contain the following layers:
* Interface (classes for receiving a request (data) from the user and outputting the result to the console)
* Controller (a class or classes that accept requests from the user, define tasks and refer to the Model by calculating the result that is passed to the Interface for display)
* A model that is divided into sublayers:
    - services (classes that perform the calculation of the user's request)
    - dao (a class or classes for accessing the data store)
    - data (class or classes describing the subject area)

The application should provide the user with the opportunity to:
* view the entire data set;
* add data;
* sort the data set according to any two properties (that is, sort according to the attributes proposed to the user; the choice of properties is arbitrary);
* filter the data set according to the specified criteria according to the option of the task;
* save data processed by the application in a file;
* save intermediate results to files as desired (ie sample result).

For the convenience of demonstrating the operation of the application, the interface should contain a menu for selecting actions by the user.

To monitor the operation of the application, provide it with a logging system.


## IMPLEMENTATION DETAILS:

* the display of the data set (all or the result of the sample) should be in tabular form;
* the interface should be user-friendly:
    - a menu for selecting actions (the presence of the "Exit" action);
    - a message with an invitation to enter; a message about the displayed result; provide a corresponding message in case of no result of data sampling;
    - entering data for filtering (sampling) or adding new data;
    - processing of incorrect data entry;
* if erroneous data is entered (that is, the entered value does not correspond to the possible range or does not correspond to the required format), provide for the output of a message about the type of error and offer to repeat the input;
* verification of entered data should be provided in validator classes (regular expressions can be used);
* describe custom exception types and apply them when handling data entry errors using the exception handling mechanism;
* log all errors and user actions;
* data to be processed by the application, save in a file:
    - the format can be text, object or JSON;
    - when starting the application, data is read from the file;
    - when the application is finished, the data is written to a file;
    - intermediate results are saved to the file specified by the user (the name is entered from the keyboard);
* to save a set of data in memory, use collections;
* to handle collections, use Streams and lambda expressions where necessary.
