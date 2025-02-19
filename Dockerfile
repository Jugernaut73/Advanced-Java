FROM openjdk:17
COPY "C:\Users\Admin\Desktop\d387-advanced-java\target\D387_sample_code-0.0.2-SNAPSHOT.jar" /tmp
ENTRYPOINT ["java","-jar","/app.jar"]
