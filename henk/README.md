# Software Construction
## Members
- Hector Stenger, 10398872
- Jovan Maric, 10443762

## Requirements
The following pre-requicities are needed to setup the project

- ruby 2.5.0
- rbenv
- bundler

## installing
First install rbenv from https://github.com/rbenv/rbenv

```
# Install ruby 2.5.0
rbenv install 2.5.0
rbenv local 2.5.0
rbenv rehash

# Install bundler
gem install bundler

# Install project dependencies
bundle install
```

## Runing
...

## Testing
`rspec`

or

`rspec path/to/file`

## coverage
The coverage is automatically generated when running rspec. The generated
coverage report is found under coverage/index.html.
