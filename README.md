# ftptester
Small project to test FTP servers.


### Build project

In order to build the project you run maven in the main directory.
```
mvn package
```

### Running project

```
java -jar target/ftptester-1.0-SNAPSHOT-jar-with-dependencies.jar test [threadnum] [ipaddr] [username] [password] [localdirectory] [remote directory]
```

### Verify result

If you used the local directory of data you may change the first parameter ```test``` to ```data``` that will check that directory
and see that all files in the directory contains the name of the files. If you have issues
with the ftp upload the files name and the data within the file will differ.