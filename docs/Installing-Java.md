# Installing Java

This project uses Java. Without it, the .jar file will not run. 
The .jar file also has a minimum requirement of Java 11. If you have a lower version of Java, this will also not run.

### Checking for other Java installations. 
Sometimes, machines can have more than 1 version of Java installed, but defaults to an older version. 
You can check for your Java versions by doing the following

Windows
> * Open file explorer
> * Navigate to ```C:\Program Files\Java```
>   * If you see a folder that says you have Java jdk/jre 11 or higher, open the folder, and navigate to the "bin" folder.
      > You should find a file called "java.exe". Continue with the installation tutorial, but everytime you are asked to use the command "java.exe", drag and drop the java file you just located instead.
>   * If you do not see a folder that says you have Java jdk/jre 11 or higher, continue on to instructions on downloading Java below.

macOS/Linux distros
> * Open finder, files, dolphin, or whatever the file explorer is called on your machine.
> * Navigate to ```/usr/lib/jvm```. 
>   * If you see a folder that says you have Java jdk/jre 11 or higher, open the folder, and navigate to the "bin" folder. 
      > You should find a file called "java". Continue with the installation tutorial, but everytime you are asked to use the command "java", drag and drop the java file you just located instead.
>   * If you do not see a folder that says you have Java jdk/jre 11 or higher, continue on to instructions on downloading Java below.

### Downloading Java
Different machines have different ways of installing Java. If you already have an outdated version of Java installed, you may need to follow the above steps again checking for older java installations.

Windows and macOS
> * Install Java from sites such as [AdoptOpenJdk](https://adoptopenjdk.net/) or from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html). You are recommended to download Java 11, or Java 17, though any versions in between would work too. Java Jdk or Jre are both fine.
>   * Download the .exe file if you are on Windows, or .dmg if you are on macOS. Run the installer, and Java will be installed. You can then re-attempt the setup steps in the [README](https://github.com/awesomemoder316/DiscordBackdoorBot#readme)


Linux madness  
Linux has many ways to install Java, depending on your distribution. I will list some ways you can install Java, but if your distribution is not listed, please do a web search on how to install Java. 

Ubuntu/Debian
> Instructions taken from [https://adoptopenjdk.net/installation.html#linux-pkg](https://adoptopenjdk.net/installation.html#linux-pkg)
>```
>sudo apt-get install -y wget apt-transport-https gnupg
>wget https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public
>gpg --no-default-keyring --keyring ./adoptopenjdk-keyring.gpg --import <dowloaded-keyfile-name><.ext>
>gpg --no-default-keyring --keyring ./adoptopenjdk-keyring.gpg --export --output adoptopenjdk-archive-keyring.gpg 
>rm adoptopenjdk-keyring.gpg
>sudo mv adoptopenjdk-archive-keyring.gpg /usr/share/keyrings && sudo chown root:root /usr/share/keyrings/adoptopenjdk-archive-keyring.gpg 
>echo "deb [signed-by=/usr/share/keyrings/adoptopenjdk-archive-keyring.gpg] https://adoptopenjdk.jfrog.io/adoptopenjdk/deb <codename> main" | sudo tee /etc/apt/sources.list.d/adoptopenjdk.list
>sudo apt-get update
>sudo apt-cache search adoptopenjdk
>sudo apt-get install adoptopenjdk-11-hotspot
>```

Arch linux
> pamac install jdk11-adoptopenjdk

Fedora/Red Hat
> dnf install java-11-openjdk

Java Jdk or Jre are both fine to use. After installing, you can then re-attempt the setup steps in the [README](https://github.com/awesomemoder316/DiscordBackdoorBot/)




