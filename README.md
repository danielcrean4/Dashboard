# Dashboard
Note organizer tool built with javafx
*******************************************************************************************************************************

The Dashboard GUI allows users to create Subject folders, then allows them to populate these folders with Notes.

To create a new Subject, type the title of the Subject in the left-hand box and click the "Add New Subject" button.

This will populate a link to the Subject, that when clicked will populate the center pane with a list of all Notes that have been created 
for this Subject. Users can then create additional Notes for the subject by typing in a title in the input field at the top center
and clicking the "Create New Note" button. This will create a link to the Note, which will appear on the right of the screen when clicked. 

The note allows users to save text content within it. This content defaults to "(To add content, click 'edit text' button below)", but can 
be updated by clicking 'toggle edit mode' and when the user is done adding the text they want in the content field, they 
save by clicking the 'Save updates to Note' Button



The Subjects/Notes/Content input by the users will be retained between runs in the file "saveData.txt" which will be created in the same
filepath used to store the Java project


**known bugs**
- Saving Note content with multiple lines will be consolidated into one line upon closing and restarting the program

** next steps **
- review and clean up comments
- test for additional bugs