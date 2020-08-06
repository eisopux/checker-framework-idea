# checker-framework-idea

There has not been a dedicated Checker Framework plugin for IntelliJ IDEA yet, but since there is a plugin that provides LSP support for IDEA, [LSP Support](https://plugins.jetbrains.com/plugin/10209-lsp-support/), it is possible to integrate the [LSP Language Server](https://github.com/eisopux/checker-framework-languageserver) using this plugin.

After installing LSP Support and downloading the language server and Checker Framework to your disk, please go to Preferences - Languages & Frameworks - Language Server Protocol - Server Deﬁnitions, then choose “Raw command”, and add the extension `java`. A sample command that will run the Nullness Checker is:

```
java \
    -cp /path/to/languageserver.jar:/path/to/checker-framework/checker/dist/checker.jar \
    org.checkerframework.languageserver.ServerMain \
    --frameworkPath /path/to/checker-framework \
    --checkers org.checkerframework.checker.nullness.NullnessChecker
```

After this, red squiggle lines will be drawn under errors detected by the Nullness Checker. For other options, please refer to the documentation of the language server.
