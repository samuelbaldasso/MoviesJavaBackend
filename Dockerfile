# Use uma imagem base oficial do OpenJDK 17
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo .jar compilado (do diretório 'target') para o contêiner
# O Maven ou Gradle deve ter gerado este arquivo antes de construir a imagem.
COPY target/*.jar application.jar

# Exponha a porta em que a aplicação Spring Boot roda
EXPOSE 8080

# Comando para executar a aplicação quando o contêiner iniciar
ENTRYPOINT ["java", "-jar", "application.jar"]
