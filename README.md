# Apps Rendering API Models

![npm](https://img.shields.io/npm/v/@guardian/apps-rendering-api-models)
[![apps-rendering-api-models Scala version support](https://index.scala-lang.org/guardian/apps-rendering-api-models/apps-rendering-api-models/latest-by-scala-version.svg?platform=jvm)](https://index.scala-lang.org/guardian/apps-rendering-api-models/apps-rendering-api-models)

This project contains Thrift models and a way to publish them as Scala and TypeScript packages. MAPI uses the Scala package to send data to the Apps Rendering API, which uses the TypeScript package to deserialise the data. These two projects can be found here:

- MAPI (uses the Scala package): https://github.com/guardian/mobile-apps-api
- Apps-Rendering (uses the TypeScript package): https://github.com/guardian/dotcom-rendering/tree/main/apps-rendering

## How to run the tests

If you run `sbt test` it will fail, you can only run `sbt compile` to check that it's working correctly. This project doesn't have tests because it's used to auto-generate Scala and TypeScript packages from Thrift definitions, there's no Scala or TS source code.
