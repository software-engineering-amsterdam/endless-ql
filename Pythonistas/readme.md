# Software Construction 

```
Sjoerd van den Heijden - 10336001
Dylan Bartels - 10607072
```


# Requirements

- Python 3.6.4

# Setup MacOs

Requirements:
- pyenv (brew install pyenv)
- antlr4

pyenv:
navigate to folder

```
pyenv install 3.6.4
pyenv virtualenv 3.6.4 pythonistas3
pyenv local pythonistas3
pip install -r requirements.txt
python run.py
```

antlr:
```
cd /usr/local/lib
curl -O http://www.antlr.org/download/antlr-4.7.1-complete.jar
export CLASSPATH=".:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"
alias antlr4='java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
alias grun='java org.antlr.v4.gui.TestRig'
```

# Running

```
usage: Python Questionnaire Language Parser [-h] [-v] [-t] [-g path]
                                            [-p {QL,QLS}]

CLI tool of the python QL/QLS parser with gui. No arguments runs GUI

optional arguments:
  -h, --help                      show this help message and exit
  -v, --version                   Prints the program version.
  -t, --test                      Runs the testsuite.
  -g path, --grammar path         Debug grammar. example: python run_app.py
                                  tests/forms/ql/pass/money_declare.ql
  -p {QL,QLS}, --parser {QL,QLS}  Generate antlr4 parser.
```

run_main: python run_app.py

# Testing

```
pytest
```