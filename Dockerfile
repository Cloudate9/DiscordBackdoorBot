FROM archlinux:latest

ENV JAVA_HOME=/opt/java/openjdk
COPY --from=eclipse-temurin:latest $JAVA_HOME $JAVA_HOME
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Install git, which is needed for DiscordBackdoorBot
RUN pacman -Sy git --noconfirm

# Proceed to clone the actual project
RUN git clone https://github.com/Cloudate9/DiscordBackdoorBot

# And lastly, build.
# renameJar.sh is a hacky way to rename the built file into something constant. If you know how to do this within the Dockerfile, please submit a PR!
RUN cd DiscordBackdoorBot && chmod +x gradlew && ./gradlew clean shadowJar && chmod +x renameJar.sh && ./renameJar.sh

ENTRYPOINT ["java", "-jar", "DiscordBackdoorBot/build/libs/DiscordBackdoorBot-DockerRenamed.jar"]