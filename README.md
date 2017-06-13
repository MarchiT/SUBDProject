# SUBDProject
Project for SUBD class in TUES


## CodedChat Database Structure
- Table: **Users**
  + Id
  + Name
  <br>
- Table: **MessageTypes**
  + Id
  + Name
  + `[CreatorUserId]`
  <br>
- Table: **Messages**
  - Id
  - Text
  - [UploadedDate]
  - `ToUserId`
  - `TypeId`
  <br>
- Table: **Solutions**
  - Id
  - DecodedText
  - `UserId`
  - `MessageId`
