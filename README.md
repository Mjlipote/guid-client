guid-client
=============
GUID client for Java.

##Maven Repo
```xml
<dependencies>
	<dependency>
		<groupId>tw.edu.ym.guid</groupId>
		<artifactId>guid-client</artifactId>
		<version>1.3.5</version>
	</dependency>
</dependencies>

<repositories>
	<repository>
		<id>guid-client-mvn-repo</id>
		<url>https://raw.github.com/twbinfo/guid-client/1.3.5/</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>

```
##Gradle
```gradle
dependencies {
    compile "tw.edu.ym.guid:guid-client:1.3.5"
}

repositories {
    maven {
        url "https://raw.github.com/twbinfo/guid-client/1.3.5/"
    }
}
```

##Quick Start
####GUID Client
```java
import static tw.edu.ym.guid.client.field.Name.NamePart.*;
  
PII pii = new PII.Builder(
        NameSplitter.split(<FULL_NAME_HERE>), Sex.MALE,
        new Birthday(<BIRTH_YEAR_HERE>, <BIRTH_MONTH>, <BIRTH_DAY>), new TWNationalId(<SOCIAL_NUMBER_HERE>)).build();

GuidClient gc = new GuidClient(new URI(<GUID_SERVER_URI_HERE>),
                  <USERNAME_HERE>, <PASSWORD_HERE>, <PREFIX_HERE>);

System.out.println(gc.create(pii));
```
