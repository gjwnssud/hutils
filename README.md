# Hutils

개인적으로 사용하는 개발에 유용한 모듈 모음 입니다.

## Features

- http client
- request / response holder
- exception logging
- retry handler
- async
- empty checker
- constraints / mimetype / file size validator
- ip checker
- string masker
- multipart-file to file converter

## Tech Stack 📚

<div style="margin-left: 1em">
   <img src="https://img.shields.io/badge/language-121011?style=for-the-badge" alt=""><img src="https://img.shields.io/badge/java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt=""><img src="https://img.shields.io/badge/17-515151?style=for-the-badge" alt="">
</div>
<div style="margin-left: 1em">
   <img src="https://img.shields.io/badge/build-121011?style=for-the-badge" alt=""><img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt=""><img src="https://img.shields.io/badge/8.5-515151?style=for-the-badge" alt="">
</div>
<div style="margin-left: 1em">
   <img src="https://img.shields.io/badge/dependencies-121011?style=for-the-badge" alt=""><img src="https://img.shields.io/badge/jakarta_servlet--api-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt=""><img src="https://img.shields.io/badge/6.0.0-515151?style=for-the-badge" alt="">
</div>
<div style="margin-left: 1em">
   <img src="https://img.shields.io/badge/dependencies-121011?style=for-the-badge" alt=""><img src="https://img.shields.io/badge/jackson-0E83CD?style=for-the-badge&logo=json&logoColor=white" alt=""><img src="https://img.shields.io/badge/2.15.0-515151?style=for-the-badge" alt="">
</div>
<div style="margin-left: 1em">
   <img src="https://img.shields.io/badge/dependencies-121011?style=for-the-badge" alt=""><img src="https://img.shields.io/badge/slf4j-0E83CD?style=for-the-badge&logo=&logoColor=white" alt=""><img src="https://img.shields.io/badge/2.0.9-515151?style=for-the-badge" alt="">
</div>
<div style="margin-left: 1em">
   <img src="https://img.shields.io/badge/dependencies-121011?style=for-the-badge" alt=""><img src="https://img.shields.io/badge/spring--web-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt=""><img src="https://img.shields.io/badge/6.1.14-515151?style=for-the-badge" alt="">
</div>
<div style="margin-left: 1em">
   <img src="https://img.shields.io/badge/dependencies-121011?style=for-the-badge" alt=""><img src="https://img.shields.io/badge/spring--context-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt=""><img src="https://img.shields.io/badge/6.1.14-515151?style=for-the-badge" alt="">
</div>

## Usage

### Maven

###### Install 1/2: Add this to pom.xml:

```xml
<project>
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/gjwnssud/hutils</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.hzn</groupId>
    <artifactId>hutils</artifactId>
    <version>1.0.5c2d708</version>
</dependency>
</project>
```

###### Install 2/2: Run via command line:

```shell
mvn install
```

### Gradle

###### Install 1/2: Add this to build.gradle:

```groovy
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/gjwnssud/hutils")
    }
}

dependencies {
    implementation 'com.hzn:hutils:1.0.5c2d708'
}
```

###### Install 2/2: Run via command line:

```shell
./gradlew build
```
