package snippet;

public class Snippet {
	# Step 1: Build the application
	FROM maven:3.8.5-openjdk-17 AS build
	COPY . .
	RUN mvn clean 
}

