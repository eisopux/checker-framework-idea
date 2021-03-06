# checker-framework-idea

There is no dedicated Checker Framework plugin for IntelliJ IDEA (yet), but
there is a plugin that provides LSP support for IDEA, [LSP
Support](https://plugins.jetbrains.com/plugin/10209-lsp-support/), which makes
it possible to integrate the [Checker Framework Language
Server](https://github.com/eisopux/checker-framework-languageserver).

After installing LSP Support and downloading the [Checker Framework Language
Server](https://github.com/eisopux/checker-framework-languageserver) and
[Checker Framework](https://github.com/eisop/checker-framework) to your system,
go to Preferences - Languages & Frameworks - Language Server Protocol - Server
Deﬁnitions, then choose “Raw command”, and add the extension `java`. A sample
command that will run the Nullness Checker is:

```
java \
    -cp /path/to/languageserver.jar:/path/to/checker-framework/checker/dist/checker.jar \
    org.checkerframework.languageserver.ServerMain \
    --frameworkPath /path/to/checker-framework \
    --checkers org.checkerframework.checker.nullness.NullnessChecker
```

After this, red squiggle lines will be drawn under errors detected by the
Nullness Checker. For other options, please refer to the documentation of the
language server.
