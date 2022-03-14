# DiscordBackdoorBot
Allowing server admins to backdoor their own server!

### What does Discord Backdoor Bot do?
Discord Backdoor bot allows the bot owner to create roles, delete roles, assign roles, and revoke roles, helping to give an administrator powers closer to that of the server owner.
If this bot has been given administrator permissions, it can even create new Administrator roles!

### But why?
Discord Backdoor bot was created due to my Discord account being banned for "suspicious activity". While I could rejoin all the servers I was in, 
I was unable to regain admin permissions in the server I owned. This lead to having to create a new Discord server, and asking all the members to join the new one, causing
inconvenience for all. 

Discord Backdoor Bot aims to allow you to continue having ownership of your server on an alternate account, even if your original account is lost/banned.

You will need to self-host this bot, but it can remain off most of the time, until you want to do admin stuff.

### Can't I just get my admins to create a new role for me and promote me?
You could, but you would be lacking 2 major powers: removing administrator roles from a role list, and revoking an administrator role from a rogue user. 
This bot would allow only you to have those permissions through the bot, while the rest of your admins do not.

### Setting up the bot

It is recommended that you use Docker for less hassle. Manual mode **IS** found to have issues on Windows.
As such, I will only be providing instructions for Docker.

1. Install Docker. This can be done by going to [Docker's download page](https://docs.docker.com/get-docker/)
2. Obtain the [Dockerfile](https://raw.githubusercontent.com/Cloudate9/discordbackdoorbot/master/Dockerfile) of this project.
This can be done by using curl, wget, or by simply copying and pasting the contents of that page into a file called "Dockerfile"
3. Open a terminal/command prompt in the directory where you have the Dockerfile, and proceed to do the command 
``` 
docker build -t discordbackdoorbot . --no-cache
```
This can take some time depending on your machine and your internet connection. Specifically, you may see that "Gradle" may take longer than other steps.
4. You should be prompted to enter your Discord bot token and your server id. If you know what this is, and have it ready, you can enter it in. If not, refer to [getting my Discord bot token and server id](https://github.com/awesomemoder316/DiscordBackdoorBot/blob/master/docs/Discord-bot-token-and-server-id.md)  
5. Your bot is almost set up! Enter the command "invite" into your command prompt/terminal in order to invite the bot to your server. Want to know the other commands? Enter the command "help".  
6. You're done! You can shut down the bot with the command "exit". Whenever you need to do administrative tasks, you can just power on the bot but doing step 4, and do whatever you need to do!  

### Compiling from source
If you want to compile the file from source, it is really easy to do so! Open your command prompt/terminal and enter these few commands.

```
git clone https://www.github.com/Cloudate9/DiscordBackdoorBot
cd DiscordBackdoorBot
```

On Windows, continue with this command.
```
gradlew.bat shadowJar
```

On macOS or Linux distros, use these commands instead.
```
chmod +x gradlew
./gradlew shadowJar
```

You will then be able to find the .jar file in build/libs (or build\libs if you use Windows). Continue the setup steps as per normal.

### Contributing
If you would like to contribute to the project, please take note of 2 main things.
* This project is written in Kotlin, and thus Kotlin contributions are preferred over Java.
* This project uses Koin for dependency injection, and Javacord as its Discord Api. 

If you do not enough experience to be confident in contributing, but still want something added, just open a feature request at [issues](https://github.com/awesomemoder316/DiscordBackdoorBot/issues).

### Closing
In the unlikely event that something bad occurs, I hope that this bot was of help! 

Like this project? Consider starring it! 
Love this project? Consider donating on my [Ko-fi](https://ko-fi.com/cloudate9).

This project is licensed under the GNU-GPLv3. You can learn more about it [here](https://choosealicense.com/licenses/gpl-3.0/)
