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