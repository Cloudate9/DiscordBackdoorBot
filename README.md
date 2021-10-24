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
You could, but you would be lacking 2 major powers: removing administrator roles, and revoking an administrator role from a rogue user. 
This bot would allow only you to have those permissions, while the rest of your admins do not.

### Setting up the bot
1. Create a directory for the Bot on your computer. Anywhere that you can remember should be fine, for example your Desktop or Downloads directory
2. Download the latest .jar file from [releases](https://www.github.com/awesomemoder316/DiscordBackdoorBot/releases), or [compile the .jar from source](https://www.github.com/awesomemoder316/DiscordBackdoorBot#compiling-from-source).
3. Open the command prompt (Windows 10) or the terminal (Windows 11, macOS, Linux distros). All commands for this bot will be run from your command prompt/terminal.
4. Try to run the .jar file. **DO NOT** double-click on the file. Nothing will happen. Instead, enter the following command in your command prompt/terminal.  
```
java -jar (drag and drop .jar file to command prompt/terminal)
```
**OR**
```
java -jar (path to .jar file)
```
Both will have the same effect. If you see the message 
> Checking for configuration file...

you can continue on to step 5. If not, refer to [installing Java](https://www.github.com/awesomemoder316/DiscordBackdoorBotblob/main/docs/Installing-Java.md) and retry step 4.
5. You should be prompted to enter your Discord bot token and your server id. If you know what this is, and have it ready, you can enter it in. If not, refer to [getting my Discord bot token and server id](https://github.com/awesomemoder316/DiscordBackdoorBot/blob/main/docs/Discord-bot-token-and-server-id.md)
6. Your bot is almost set up! Enter the command "invite" into your command prompt/terminal in order to invite the bot to your server. Want to know the other commands? Enter the command "help".
7. You're done! You can shut down the bot with the command "exit". Whenever you need to do administrative tasks, you can just power on the bot but doing step 4, and do whatever you need to do!

### Compiling from source
If you want to compile the file from source, it is really easy to do so! Open your command prompt/terminal and enter these few commands.

```
git clone https://www.github.com/awesomemoder316/DiscordBackdoorBot
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
If you would like to contribute to the project, please take not of 2 main things.
* This project is written in Kotlin, and thus Kotlin contributions are preferred over Java.
* This project uses Koin for dependency injection, and Javacord as its Discord Api. 

If you do not enough experience to be confident in contributing, but still want something added, just open a feature request at [issues](https://github.com/awesomemoder316/DiscordBackdoorBot/issues).

### Closing
In the unlikely event that something bad occurs, I hope that this bot was of help! 

Like this project? Consider starring it! 
Love this project? Consider donating on my [PayPal](http://paypal.me/awesomemoder316) or on my [Liberapay](https://liberapay.com/awesomemoder316). Tell me if you donate, and I'll add your name here!

This project is licensed under the GNU-GPLv3. You can learn more about it [here](https://choosealicense.com/licenses/gpl-3.0/)
