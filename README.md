# cNGN-Kotlin-SDK
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white) ![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

> A minimal, opinionated Kotlin SDK for the CNGNApi.

This repository provides a Kotlin SDK for interacting with the CNGNApi. It is designed to be easy to use and provides a simple, intuitive API.

## 📋 Table of Contents
- [Features](#-features)
- [Installation](#-installation)
- [Usage](#-usage)
- [Documentation](#-documentation)
- [Folder Structure](#-folder-structure)
- [Testing](#-testing)
- [Contributing](#-contributing)

## ✨ Features
- Provides a simple, intuitive API for interacting with the CNGNApi
- Includes models and exceptions for handling API responses
- Supports Gradle build system

## 📦 Installation
To use this SDK in your project, you can add it as a dependency in your `build.gradle.kts` file.

## 📖 Usage
To use the SDK, you can import the `CngnClient` class and create an instance of it. You can then use the client to make API requests.

```kotlin
import com.example.cngn.CngnClient

// Create a new instance of the client
val client = CngnClient()

// Use the client to make API requests
```

## 🔗 Documentation
- [cmi0fvumy000002l65aocce3y.md](./cmi0fvumy000002l65aocce3y.md) - Documentation for the SDK

## 📁 Folder Structure
```
📁 .gradle/
  📁 9.0-milestone-1/
  📁 buildOutputCleanup/
📁 src/
  📁 main/
  📁 test/
  📄 config.properties
📄 build.gradle.kts
📄 cmi0fvumy000002l65aocce3y.md
📄 local.properties
📄 README.md
📄 settings.gradle.kts
```

## 📊 Testing
To run the tests, you can use the `test` task in Gradle.

```bash
./gradlew test
```

## 🤝 Contributing
Contributions are welcome! To contribute, you can fork this repository and submit a pull request.

1. Fork the repository: `https://github.com/Gideonjon/cNGN-Kotlin-SDK.git`
2. Create your feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add your feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a pull request

Please make sure to follow the existing code style and conventions.