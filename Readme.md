# iSchedule Application

## Description

The goal of this project is to create a room/course schedule application for students and teachers, using object oriented programming principles. 

The project should cover the following functionalities:
1. Intuitive user interface
2. Consistency in assignments of courses to rooms
3. Students can sign in for courses and view their personal schedule
4. Administrators are responsible for maintenance activities  
5. Administrators have control over time schedules (adjust course times, check consistency)
6. Ahe teaching staff (administrators and assistants) can set preferences in regards to the start time of the courses and to the rooms

The project should also cover the following conditions:
1. It is not possible to book two courses in the same room, at the same time
2. Preferences of the teaching staff should be taken into account
3. Courses must not start before 8 a.m. and must end before 11 p.m.
4. Students are not allowed to sign in for courses that are held at the same time as a course they are already signed in for

## Assumptions

1. Example data for the application is loaded internally when signing in, with example profiles, courses and rooms (see details below)
2. The maximum number of rooms added to a schedule is 6
3. Courses can be held between 8 a.m. and 9 p.m.
4. Students can sign in for a course, but not sign out of it (will be added at later point)
5. Students cannot sign in for overlapping courses - the courses will be colored differently and unclickable
 
## Example Data

The following data is used as an example and will be loaded when the application is started.

Users:

The password is 1234 for all users and email format is username@mail.com

1. Administrator: user name: emayer; PW: 1234; email: emayer@mail.com
2. Assistant: user name: slubos
3. Assistant: user name: mgukesh
4. Student: user name: dhubmann
5. Student: user name: rhofer

Default preferences for the teaching staff are course times from 08:00 to 16:00 and a standard room equipment (desks, whiteboard, projector).

Rooms:

1. name: A-R1; equipment: computers
2. name: C-R3; equipment: space (is spacious)
3. name: B-R4; equipment: standard (desks, whiteboard, projector)

Courses:

1. MAT1 in A-R1 at 08:00 - 10:00, instructor: emayer
2. Webtechnology in A-R1 at 10:00 - 12:30, instructor: slubos
3. Social Medicine in C-R3 at 09:00 - 12:00, instructor: mgukesh
4. Social Work in C-R3 at 13:00 - 15:00, instructor: mgukesh


## Roadmap

There are still a lot of features that can/should be added.
- Responsive layout
- Implementing a calendar and a schedule per day
- Extending the functionality of the sidebar
- Warnings for the teacher staff if their preferences are not meant
- Feature to click on a room to get its information (name, building, equipment etc.)
- Feature to click on a course to get its information (name, instructor, capacity etc.)
- Option for students to sign out of courses
