# TDA367
Project in Chalmers course TDA367

How to run project
-------------------------
1. In IntelliJ: Import project -> build.gradle
2. Run DesktopLauncher.java. It will fail the first time, don't worry (wrong working directory)
3. Go to Run -> Edit configuration -> change working directory to ...<reponame>/core/assets
4. To configure JUnit, go to File -> Project Structure -> Libraries and add the JUnit and Hamcrest jars, which are located in the JUnit folder in the repo
5. Everything should now work fine.
