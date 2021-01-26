[![Build](https://img.shields.io/github/workflow/status/hectorbst/jsonschema2pojo-interfaces/Build?label=Build)](https://github.com/HectorBst/jsonschema2pojo-interfaces/actions?query=workflow%3ABuild)
[![Coverage](https://img.shields.io/sonar/coverage/HectorBst_jsonschema2pojo-interfaces?server=https%3A%2F%2Fsonarcloud.io&label=Coverage)](https://sonarcloud.io/dashboard?id=HectorBst_jsonschema2pojo-interfaces)
[![Violations](https://img.shields.io/sonar/violations/HectorBst_jsonschema2pojo-interfaces?server=https%3A%2F%2Fsonarcloud.io&label=Violations)](https://sonarcloud.io/dashboard?id=HectorBst_jsonschema2pojo-interfaces)
[![Maven Central](https://img.shields.io/maven-central/v/dev.hctbst/jsonschema2pojo-interfaces?label=Maven%20Central)](https://search.maven.org/artifact/dev.hctbst/jsonschema2pojo-interfaces)
[![License](https://img.shields.io/github/license/hectorbst/jsonschema2pojo-interfaces?label=License)](LICENSE)

# jsonschema2pojo-interfaces

This project is a [*jsonschema2pojo*](https://github.com/joelittlejohn/jsonschema2pojo) extension dedicated to
interface generation.

## Features

### Interfaces

This extension generate interfaces. It acts like a post-processing tool removing all field and method bodies.

E.g., this schema:
```json
{
	"title": "Sample entity",
	"type": "object",
	"properties": {
		"field": {
			"title": "A field",
			"type": "string"
		}
	}
}
```
Will produce:
```java
public interface Entity {

	String getField();

	void setField(String field);
}
```

## Maven configuration

Here is an example of how the extension can be added to the jsonschema2pojo Maven plugin.

```xml

<plugin>
	<groupId>org.jsonschema2pojo</groupId>
	<artifactId>jsonschema2pojo-maven-plugin</artifactId>
	<version>${jsonschema2pojo-interfaces.version}</version>
	<executions>
		<execution>
			<goals>
				<goal>generate</goal>
			</goals>
			<configuration>
				...
				<!-- Extension RuleFactory -->
				<customRuleFactory>
					dev.hctbst.jsonschema2pojo.interfaces.InterfacesRuleFactory
				</customRuleFactory>
			</configuration>
		</execution>
	</executions>
	<dependencies>
		<!-- Extension dependency -->
		<dependency>
			<groupId>dev.hctbst</groupId>
			<artifactId>jsonschema2pojo-interfaces</artifactId>
			<version>${jsonschema2pojo-interfaces.version}</version>
		</dependency>
</plugin>
```

A more complete example is available [here](example).

## License

This project is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
