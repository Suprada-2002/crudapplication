1. Install Java
```
sudo apt update
sudo apt install openjdk-17-jdk -y
java -version
```

2. Install Maven
```
sudo apt install maven -y
mvn -v
```

3. Install PostgresSQL
```
sudo apt install postgresql postgresql-contrib -y
//start postgresql
sudo systemctl enable postgresql
sudo systemctl start postgresql
// check status
sudo systemctl status postgresql

sudo -i -u username
psql
```

4. Create a project
```
Press Ctrl + Shift + P → type Spring Initializr: Create a Maven Project

Follow the prompts:
Spring Boot version: (latest 3.3.x)
Group Id: com.example
Artifact Id: projectname
Language: Java
Packaging: Jar
Java Version: 17
Dependencies:
✅ Spring Web
✅ Spring Data JPA
✅ PostgreSQL Driver
✅ Spring Boot DevTools (optional but great for auto-restart)
```

5. Required VS code extensions
- Extension Pack for Java - MS
- Spring Boot Extension Pack - vmware
- maven fo rjava
- Thunder Client


## TABLES
1. Users
2. Note

## RELATIONSHIPS
```
  ┌────────────┐          1      *        ┌────────────┐
  │   Users    │─────────────────────────>│   Note     │
  └────────────┘                          └────────────┘
  id (PK)                                 id (PK)
  userName                                title
  email                                   content
  phoneNo                                 createdAt
  notes (List<Note>) <------------------  user (ManyToOne)
```

## DTO
- DTO = Data Transfer Object
- It’s a simple plain Java object used to transfer data between layers (e.g., from frontend → backend or backend → frontend).
- It usually does not contain business logic, only fields and getters/setters.
- It helps decouple your entity from the API, so you don’t expose sensitive fields (like passwords or internal IDs) directly.
```
AuthRequest → represents the login request from the client (contains email and password).
AuthResponse → represents the login response sent back to the client (contains JWT token).
```