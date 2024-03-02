# Fabric HAProxy
Allows Fabric Minecraft servers to interpret HAProxy PROXY protocol messages, for IP forwarding.

## Usage
Drop the mod in your mods folder to install. When installed, the server MUST be accessed behind HAProxy (or another proxy
which supports the protocol, such as [MidnightProxy](https://github.com/wallenjos01/MidnightProxy)). If a player tries to
connect to the server directly, the connection will be closed or time out.