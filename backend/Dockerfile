# ---------- FRONTEND BUILD ----------
FROM node:18 AS frontend
WORKDIR /app/frontend
COPY frontend/ .
RUN npm install
RUN npm run build


# ---------- BACKEND BUILD ----------
FROM maven:3.9.6-eclipse-temurin-17 AS backend
WORKDIR /app/backend
COPY backend/ .
COPY --from=frontend /app/frontend/dist ./src/main/resources/static
RUN mvn -q -DskipTests package


# ---------- FINAL RUNTIME IMAGE ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=backend /app/backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

