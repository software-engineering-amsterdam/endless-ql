# Software Construction
## Members
- Hector Stenger, 10398872
- Jovan Maric, 10443762

## Requirements
The following prerequisite are needed to setup the project:

1. Latest Elixir: https://elixir-lang.org/install.html
2. Hex package manager: run `mix local.hex`

## installing
To install the required dependencies, run `mix deps.get`

## Runing
To run the webserver or debuggin mode do the following:

```
# Server will be hosted on: localhost:4000
mix phx.server
```

Or

```
# Interactive mode for debugging purposes
iex -S mix
```

## Code Analysis
To run code analysis, run `mix credo`

## Testing
To run the test suite do the following:

```
mix espec
```

Or

```
mix espec /path/to/file
```
