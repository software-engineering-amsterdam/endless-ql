import sys
import os
import subprocess
import pytest
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)) + '/../')


def test_antlr_dependency():
    if sys.platform == 'win32':
        # todo: write antlr4 jar test, used in def run_antlr_parse_gen
        assert True
    else:
        if os.path.isfile('/usr/local/lib/antlr-4.7.1-complete.jar'):
            assert True
        else:
            print('antlr4-4.7.1 jar not found. Expected it to be in: /usr/local/lib/')
            assert False


def test_java_dependency():
    """ Tests java dependency, used to generate antlr4 parser"""
    sp = subprocess.Popen(["java", "-version"], stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    if 'java version' in str(sp.communicate()[1]):
        assert True
    else:
        print('java not found. Install java. (cmd used to check: java -version')
        assert False


def test_javac_dependency():
    """ Tests javac dependency, used to generate antlr4 parser"""
    sp = subprocess.Popen(["javac", "-version"], stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    if 'javac 1' in str(sp.communicate()[1]):
        assert True
    else:
        print('javac not found. Install javac. (cmd used to check: javac -version')
        assert False
