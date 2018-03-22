# QL and QLS
## Contributors
- Jordy Bottlelier, 10747338
- Dennis Kruidenberg, 10780998

## Installation Guide
Follow this guide in order to be able to use our QL and QLS interpreter. Make sure you have [python3.6.3](https://www.python.org/downloads/) installed, as well as [pip](https://pip.pypa.io/en/stable/installing/) and [virtualenv](https://virtualenv.pypa.io/en/stable/installation/)

1. Pull the code from this repo.
2. Cd to the root of the folder
3. Execute the following commands:
```
virtualenv env --python=python3.6		# Or your most recent python version, at least python 3
source env/bin/activate
pip install -r requirements.txt
export CLASSPATH=".:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"  # Needed in order for antlr to work
```
4. When you have created a QL or QLS program, run it with the following command:
```
python path/to/parser.py path/to/qlFilename path/to/[qlsFilename]	# QLS file is optional
```

## Explenation
For explenation on the QL, QLS the GUI and Testing please go to their respective Readme's:

- [QL](https://github.com/software-engineering-amsterdam/endless-ql/tree/master/Jordy_Dennis/QL)
- [QLS](https://github.com/software-engineering-amsterdam/endless-ql/tree/master/Jordy_Dennis/QLS)
- [Testing](https://github.com/software-engineering-amsterdam/endless-ql/tree/master/Jordy_Dennis/Testing)
- [GUI](https://github.com/software-engineering-amsterdam/endless-ql/tree/master/Jordy_Dennis/GUI)
