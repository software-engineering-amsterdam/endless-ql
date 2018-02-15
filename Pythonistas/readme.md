# Software Construction 

```
Sjoerd van den Heijden
Dylan Bartels - 10607072
```

# Introduction

need to do

# Requirements

- Python 3.6.4

# Setup MacOs

Requirements:
- pyenv (brew install pyenv)

navigate to folder

```
pyenv install 3.6.4
pyenv virtualenv 3.6.4 pythonistas3
pyenv local pythonistas3
pip install -r requirements.txt
python run.py
```

# Running

```
usage: run_app.py [-h] [-l {debug,info,warn,error,critical}] [-v] [file_name]

Python Questionnaire Language

positional arguments:
  file_name             Python input file

optional arguments:
  -h, --help            show this help message and exit
  -l {debug,info,warn,error,critical}, --log-level {debug,info,warn,error,critical}
                        Log level. Only log messages with a level higher or
                        equal than this will be printed. Default: 'warn'
  -v, --version         Prints the program version.
```

example: python run_app.py forms/simple.ql

# Testing

```
pytest
```